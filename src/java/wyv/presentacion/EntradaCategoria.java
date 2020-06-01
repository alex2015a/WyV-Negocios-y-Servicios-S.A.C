package wyv.presentacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
public class EntradaCategoria extends org.apache.struts.action.ActionForm {
    
    private String nombre,descripcion;
    private int codigo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
  

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (nombre == null || nombre.length() < 1) {
            errors.add("nombreCate", new ActionMessage("error.nombreCate"));
        }
        if (descripcion == null || descripcion.length() < 1) {
            errors.add("descripcion", new ActionMessage("error.descripcion"));
        }
        return errors;
    }
}
