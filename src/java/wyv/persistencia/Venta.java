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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByNum", query = "SELECT v FROM Venta v WHERE v.num = :num")
    , @NamedQuery(name = "Venta.findByFec", query = "SELECT v FROM Venta v WHERE v.fec = :fec")
    , @NamedQuery(name = "Venta.findByPago", query = "SELECT v FROM Venta v WHERE v.pago = :pago")
    , @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total")
    , @NamedQuery(name = "Venta.findByEstado", query = "SELECT v FROM Venta v WHERE v.estado = :estado")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "num")
    private String num;
    @Basic(optional = false)
    @Column(name = "fec")
    private String fec;
    @Basic(optional = false)
    @Column(name = "pago")
    private double pago;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idcli", referencedColumnName = "idcli")
    @ManyToOne(optional = false)
    private Cliente idcli;
    @JoinColumn(name = "dni_usu", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Usuario dniUsu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<Detalle> detalleList;

    public Venta() {
    }

    public Venta(String num) {
        this.num = num;
    }

    public Venta(String num, String fec, double pago, double total, int estado) {
        this.num = num;
        this.fec = fec;
        this.pago = pago;
        this.total = total;
        this.estado = estado;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFec() {
        return fec;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente getIdcli() {
        return idcli;
    }

    public void setIdcli(Cliente idcli) {
        this.idcli = idcli;
    }

    public Usuario getDniUsu() {
        return dniUsu;
    }

    public void setDniUsu(Usuario dniUsu) {
        this.dniUsu = dniUsu;
    }

    @XmlTransient
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  num ;
    }
    
}
