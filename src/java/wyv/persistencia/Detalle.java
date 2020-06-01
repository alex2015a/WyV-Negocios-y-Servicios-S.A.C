/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AUTONOMA
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d")
    , @NamedQuery(name = "Detalle.findByCan", query = "SELECT d FROM Detalle d WHERE d.can = :can")
    , @NamedQuery(name = "Detalle.findByPrecio", query = "SELECT d FROM Detalle d WHERE d.precio = :precio")
    , @NamedQuery(name = "Detalle.findByImporte", query = "SELECT d FROM Detalle d WHERE d.importe = :importe")
    , @NamedQuery(name = "Detalle.findByNumVenta", query = "SELECT d FROM Detalle d WHERE d.detallePK.numVenta = :numVenta")
    , @NamedQuery(name = "Detalle.findByCodPro", query = "SELECT d FROM Detalle d WHERE d.detallePK.codPro = :codPro")})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallePK detallePK;
    @Basic(optional = false)
    @Column(name = "can")
    private int can;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "importe")
    private double importe;
    @JoinColumn(name = "num_venta", referencedColumnName = "num", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venta venta;
    @JoinColumn(name = "codPro", referencedColumnName = "codPro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public Detalle() {
    }

    public Detalle(DetallePK detallePK) {
        this.detallePK = detallePK;
    }

    public Detalle(DetallePK detallePK, int can, double precio, double importe) {
        this.detallePK = detallePK;
        this.can = can;
        this.precio = precio;
        this.importe = importe;
    }

    public Detalle(String numVenta, int codPro) {
        this.detallePK = new DetallePK(numVenta, codPro);
    }

    public DetallePK getDetallePK() {
        return detallePK;
    }

    public void setDetallePK(DetallePK detallePK) {
        this.detallePK = detallePK;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallePK != null ? detallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.detallePK == null && other.detallePK != null) || (this.detallePK != null && !this.detallePK.equals(other.detallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wyv.persistencia.Detalle[ detallePK=" + detallePK + " ]";
    }
    
}
