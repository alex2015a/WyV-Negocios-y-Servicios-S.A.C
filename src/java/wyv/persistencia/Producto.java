/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AUTONOMA
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByCodPro", query = "SELECT p FROM Producto p WHERE p.codPro = :codPro")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByCantidad", query = "SELECT p FROM Producto p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "Producto.findByDescuento", query = "SELECT p FROM Producto p WHERE p.descuento = :descuento")
    , @NamedQuery(name = "Producto.findByProveedor", query = "SELECT p FROM Producto p WHERE p.proveedor = :proveedor")
    , @NamedQuery(name = "Producto.findByPreciocom", query = "SELECT p FROM Producto p WHERE p.preciocom = :preciocom")
    , @NamedQuery(name = "Producto.findByPrecioven", query = "SELECT p FROM Producto p WHERE p.precioven = :precioven")})
public class Producto implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Detalle> detalleList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codPro")
    private Integer codPro;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "descuento")
    private int descuento;
    @Basic(optional = false)
    @Column(name = "proveedor")
    private String proveedor;
    @Basic(optional = false)
    @Column(name = "preciocom")
    private double preciocom;
    @Basic(optional = false)
    @Column(name = "precioven")
    private double precioven;
    @JoinColumn(name = "codigocat", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Categoria codigocat;

    public Producto() {
    }

    public Producto(Integer codPro) {
        this.codPro = codPro;
    }

    public Producto(Integer codPro, String nombre, int cantidad, int descuento, String proveedor, double preciocom, double precioven) {
        this.codPro = codPro;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.proveedor = proveedor;
        this.preciocom = preciocom;
        this.precioven = precioven;
    }

    public Integer getCodPro() {
        return codPro;
    }

    public void setCodPro(Integer codPro) {
        this.codPro = codPro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getPreciocom() {
        return preciocom;
    }

    public void setPreciocom(double preciocom) {
        this.preciocom = preciocom;
    }

    public double getPrecioven() {
        return precioven;
    }

    public void setPrecioven(double precioven) {
        this.precioven = precioven;
    }

    public Categoria getCodigocat() {
        return codigocat;
    }

    public void setCodigocat(Categoria codigocat) {
        this.codigocat = codigocat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPro != null ? codPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.codPro == null && other.codPro != null) || (this.codPro != null && !this.codPro.equals(other.codPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wyv.persistencia.Producto[ codPro=" + codPro + " ]";
    }

    @XmlTransient
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }
    
}
