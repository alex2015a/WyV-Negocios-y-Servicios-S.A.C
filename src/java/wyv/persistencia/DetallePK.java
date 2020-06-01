/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author AUTONOMA
 */
@Embeddable
public class DetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "num_venta")
    private String numVenta;
    @Basic(optional = false)
    @Column(name = "codPro")
    private int codPro;

    public DetallePK() {
    }

    public DetallePK(String numVenta, int codPro) {
        this.numVenta = numVenta;
        this.codPro = codPro;
    }

    public String getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(String numVenta) {
        this.numVenta = numVenta;
    }

    public int getCodPro() {
        return codPro;
    }

    public void setCodPro(int codPro) {
        this.codPro = codPro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numVenta != null ? numVenta.hashCode() : 0);
        hash += (int) codPro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePK)) {
            return false;
        }
        DetallePK other = (DetallePK) object;
        if ((this.numVenta == null && other.numVenta != null) || (this.numVenta != null && !this.numVenta.equals(other.numVenta))) {
            return false;
        }
        if (this.codPro != other.codPro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wyv.persistencia.DetallePK[ numVenta=" + numVenta + ", codPro=" + codPro + " ]";
    }
    
}
