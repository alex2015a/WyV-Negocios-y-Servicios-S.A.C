/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.presentacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioUsuario;
import java.util.List;

public class RegistrarUsuario extends org.apache.struts.action.Action {

    String pagina;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        
        EntradaUsuario ent=(EntradaUsuario)form; 
        ServicioUsuario serUsu=(ServicioUsuario)request.getSession().getAttribute("serUsu");
        Salida sal=new Salida();
        String msg=serUsu.Registrarusu(ent);
         if(msg.equals("ok")){
            List usuarios=serUsu.listaUsu();
            sal.setUsuarios(usuarios);
            request.getSession().setAttribute("salLis", sal);
            pagina="ListarUsuario";
        }else{
            sal.setMsg(msg);
            request.getSession().setAttribute("mensaje", sal);
            pagina="Mensaje";
        }
        return mapping.findForward(pagina);
    }
}
