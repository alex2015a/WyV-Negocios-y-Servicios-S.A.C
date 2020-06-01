package wyv.presentacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EntradaUsuario extends org.apache.struts.action.ActionForm {
    
      private String dni,nom,pass,tipo;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
      
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        
         if (nom == null || nom.length() < 1) {
            errors.add("nom", new ActionMessage("error.nom"));
        }
         if (dni == null || dni.length() < 1) {
            errors.add("dni", new ActionMessage("error.dni"));
        }
         if(dni.length() != 8){
            errors.add("dni", new ActionMessage("error.dni.cant"));
        }
         if (pass == null || pass.length() < 1) {
            errors.add("nom", new ActionMessage("error.pass"));
        }
         if (tipo == null || tipo.length() < 1) {
            errors.add("nom", new ActionMessage("error.tipo"));
        }
        
        return errors;
    }
}
