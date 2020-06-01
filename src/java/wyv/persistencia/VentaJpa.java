/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.persistencia;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import wyv.persistencia.exceptions.IllegalOrphanException;
import wyv.persistencia.exceptions.NonexistentEntityException;
import wyv.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author AUTONOMA
 */
public class VentaJpa implements Serializable {

    public VentaJpa() {
        this.emf= Persistence.createEntityManagerFactory("W_VPU");
    }
    
    

    public VentaJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) throws PreexistingEntityException, Exception {
        if (venta.getDetalleList() == null) {
            venta.setDetalleList(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idcli = venta.getIdcli();
            if (idcli != null) {
                idcli = em.getReference(idcli.getClass(), idcli.getIdcli());
                venta.setIdcli(idcli);
            }
            Usuario dniUsu = venta.getDniUsu();
            if (dniUsu != null) {
                dniUsu = em.getReference(dniUsu.getClass(), dniUsu.getDni());
                venta.setDniUsu(dniUsu);
            }
            List<Detalle> attachedDetalleList = new ArrayList<Detalle>();
            for (Detalle detalleListDetalleToAttach : venta.getDetalleList()) {
                detalleListDetalleToAttach = em.getReference(detalleListDetalleToAttach.getClass(), detalleListDetalleToAttach.getDetallePK());
                attachedDetalleList.add(detalleListDetalleToAttach);
            }
            venta.setDetalleList(attachedDetalleList);
            em.persist(venta);
            if (idcli != null) {
                idcli.getVentaList().add(venta);
                idcli = em.merge(idcli);
            }
            if (dniUsu != null) {
                dniUsu.getVentaList().add(venta);
                dniUsu = em.merge(dniUsu);
            }
            for (Detalle detalleListDetalle : venta.getDetalleList()) {
                Venta oldVentaOfDetalleListDetalle = detalleListDetalle.getVenta();
                detalleListDetalle.setVenta(venta);
                detalleListDetalle = em.merge(detalleListDetalle);
                if (oldVentaOfDetalleListDetalle != null) {
                    oldVentaOfDetalleListDetalle.getDetalleList().remove(detalleListDetalle);
                    oldVentaOfDetalleListDetalle = em.merge(oldVentaOfDetalleListDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenta(venta.getNum()) != null) {
                throw new PreexistingEntityException("Venta " + venta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta)  {
        Util mysql = new Util();
        Connection cn = mysql.conectar();
        
       

        try {
        PreparedStatement pt=(PreparedStatement) cn.prepareStatement(""
        + "UPDATE venta set num=?, fec=?, pago=?, total=?, estado=?, idcli=?, dni_usu=?"
                + "WHERE num=?");
        pt.setString(1, venta.getNum());
        pt.setString(2, venta.getFec());
        pt.setDouble(3, venta.getPago());
        pt.setDouble(4, venta.getTotal());
        pt.setInt(5, venta.getEstado());
        pt.setInt(6,venta.getIdcli().getIdcli());
        pt.setString(7, venta.getDniUsu().getDni());
        pt.setString(8, venta.getNum());
        pt.executeUpdate();
           pt.close();
           cn.close();

        } catch (Exception e) {
        }
        
 
        
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalle> detalleListOrphanCheck = venta.getDetalleList();
            for (Detalle detalleListOrphanCheckDetalle : detalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the Detalle " + detalleListOrphanCheckDetalle + " in its detalleList field has a non-nullable venta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idcli = venta.getIdcli();
            if (idcli != null) {
                idcli.getVentaList().remove(venta);
                idcli = em.merge(idcli);
            }
            Usuario dniUsu = venta.getDniUsu();
            if (dniUsu != null) {
                dniUsu.getVentaList().remove(venta);
                dniUsu = em.merge(dniUsu);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
       return findVentaEntities(true, -1, -1);

    }
    
    
    public List<Venta> ListarVenta() {
        Util mysql = new Util();
        Connection cn = mysql.conectar();
        ArrayList<Venta> lista=new ArrayList<Venta>();
        try {
            PreparedStatement pt=(PreparedStatement) cn.prepareStatement("SELECT v.*, c.idcli,c.nomCli nombreClie, u.dni, u.nom nombreUsu FROM VENTA v INNER JOIN cliente c on c.idcli=v.idcli\n" +
            "INNER JOIN usuario u on u.dni=v.dni_usu; ");
            ResultSet rs= pt.executeQuery();
            
            

            while(rs.next())
            {
               Venta venta= new Venta();
               venta.setNum(rs.getString(1));
               venta.setFec(rs.getString(2));
               venta.setPago(rs.getDouble(3));
               venta.setTotal(rs.getDouble(4));
               venta.setEstado(rs.getInt(5));
               
               Cliente clie=new Cliente();
               clie.setIdcli(rs.getInt(6));
               clie.setNomCli(rs.getString("nombreClie"));
            
               
               venta.setIdcli(clie);
               
               Usuario usu=new Usuario();
               usu.setDni(rs.getString(7));
               usu.setNom(rs.getString("nombreUsu"));
               
               
               venta.setDniUsu(usu);
               lista.add(venta);
              
            }
                
            
              
        } catch (Exception e) {
        }
        
        return lista;
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
