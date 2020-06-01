package wyv.presentacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EntradaProducto extends org.apache.struts.action.ActionForm {
    
    String nom,cantidad,proveedor,preco,preven,codigocat, descuento;
    int cod;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
  
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPreven() {
        return preven;
    }

    public void setPreven(String preven) {
        this.preven = preven;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigocat() {
        return codigocat;
    }

    public void setCodigocat(String codigocat) {
        this.codigocat = codigocat;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
    
    

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (nom == null || nom.length() < 1) {
            errors.add("nom", new ActionMessage("error.nomPro"));
        }
        if (proveedor == null || proveedor.length() < 1) {
            errors.add("proveedor", new ActionMessage("error.prov"));
        }
        if (cantidad == null || cantidad.length() < 1) {
            errors.add("cantidad", new ActionMessage("error.cantidad"));
        }
        if (cantidad.equals("0")) {
            errors.add("cantidad", new ActionMessage("error.cantidad2"));
        }
        if (preco == null || preco.length() < 1) {
            errors.add("preco", new ActionMessage("error.preco"));
        }
        if (preven == null || preven.length() < 1) {
            errors.add("preven", new ActionMessage("error.preven"));
        }
        if (codigocat == null || codigocat.length() < 1) {
            errors.add("codigocat", new ActionMessage("error.codigocat"));
        }
        return errors;
    }
}
