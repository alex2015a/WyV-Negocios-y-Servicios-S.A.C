/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import wyv.persistencia.exceptions.NonexistentEntityException;
import wyv.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author AUTONOMA
 */
public class DetalleJpa implements Serializable {

    public DetalleJpa() {
        this.emf= Persistence.createEntityManagerFactory("W_VPU");
    }
    
    

    public DetalleJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalle detalle) throws PreexistingEntityException, Exception {
        if (detalle.getDetallePK() == null) {
            detalle.setDetallePK(new DetallePK());
        }
        detalle.getDetallePK().setNumVenta(detalle.getVenta().getNum());
        detalle.getDetallePK().setCodPro(detalle.getProducto().getCodPro());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta = detalle.getVenta();
            if (venta != null) {
                venta = em.getReference(venta.getClass(), venta.getNum());
                detalle.setVenta(venta);
            }
            Producto producto = detalle.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getCodPro());
                detalle.setProducto(producto);
            }
            em.persist(detalle);
            if (venta != null) {
                venta.getDetalleList().add(detalle);
                venta = em.merge(venta);
            }
            if (producto != null) {
                producto.getDetalleList().add(detalle);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalle(detalle.getDetallePK()) != null) {
                throw new PreexistingEntityException("Detalle " + detalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalle detalle) throws NonexistentEntityException, Exception {
        detalle.getDetallePK().setNumVenta(detalle.getVenta().getNum());
        detalle.getDetallePK().setCodPro(detalle.getProducto().getCodPro());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalle persistentDetalle = em.find(Detalle.class, detalle.getDetallePK());
            Venta ventaOld = persistentDetalle.getVenta();
            Venta ventaNew = detalle.getVenta();
            Producto productoOld = persistentDetalle.getProducto();
            Producto productoNew = detalle.getProducto();
            if (ventaNew != null) {
                ventaNew = em.getReference(ventaNew.getClass(), ventaNew.getNum());
                detalle.setVenta(ventaNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getCodPro());
                detalle.setProducto(productoNew);
            }
            detalle = em.merge(detalle);
            if (ventaOld != null && !ventaOld.equals(ventaNew)) {
                ventaOld.getDetalleList().remove(detalle);
                ventaOld = em.merge(ventaOld);
            }
            if (ventaNew != null && !ventaNew.equals(ventaOld)) {
                ventaNew.getDetalleList().add(detalle);
                ventaNew = em.merge(ventaNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetalleList().remove(detalle);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetalleList().add(detalle);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallePK id = detalle.getDetallePK();
                if (findDetalle(id) == null) {
                    throw new NonexistentEntityException("The detalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalle detalle;
            try {
                detalle = em.getReference(Detalle.class, id);
                detalle.getDetallePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalle with id " + id + " no longer exists.", enfe);
            }
            Venta venta = detalle.getVenta();
            if (venta != null) {
                venta.getDetalleList().remove(detalle);
                venta = em.merge(venta);
            }
            Producto producto = detalle.getProducto();
            if (producto != null) {
                producto.getDetalleList().remove(detalle);
                producto = em.merge(producto);
            }
            em.remove(detalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalle> findDetalleEntities() {
        return findDetalleEntities(true, -1, -1);
    }

    public List<Detalle> findDetalleEntities(int maxResults, int firstResult) {
        return findDetalleEntities(false, maxResults, firstResult);
    }

    private List<Detalle> findDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalle.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detalle findDetalle(DetallePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalle> rt = cq.from(Detalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
