package wyv.presentacion;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EntradaCliente extends org.apache.struts.action.ActionForm {
    
    private String nom,telf,direcc,email,dni;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getDirecc() {
        return direcc;
    }

    public void setDirecc(String direcc) {
        this.direcc = direcc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

 
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        if (nom == null || nom.length() < 1) {
            errors.add("nom", new ActionMessage("error.nom"));
        }
        if (telf == null || telf.length() < 1) {
            errors.add("telf", new ActionMessage("error.teleclie"));
        }
        if (direcc == null || direcc.length() < 1) {
            errors.add("direcc", new ActionMessage("error.direclie"));
        }
        if (email == null || email.length() < 1) {
            errors.add("email", new ActionMessage("error.emailclie"));
        }
        if (dni == null || dni.length() < 1) {
            errors.add("dni", new ActionMessage("error.dni"));
        }
        if(dni.length() != 8){
            errors.add("dni", new ActionMessage("error.dni.cant"));
        }
        
        return errors;
    }
}
