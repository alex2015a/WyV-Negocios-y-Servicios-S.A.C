package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioUsuario;
import wyv.servicios.ServicioVenta;

public class EditarUsuario extends org.apache.struts.action.Action {

   String pagina;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        EntradaUsuario entusu=(EntradaUsuario)form;
        ServicioUsuario serUsu=(ServicioUsuario)request.getSession().getAttribute("serUsu");
        ServicioVenta servVenta=(ServicioVenta)request.getSession().getAttribute("servVenta");
        List ventas=servVenta.listarVenta();
        
        Salida sal=(Salida)request.getSession().getAttribute("sal");
       
        String msg=serUsu.editarUsu(entusu, ventas);
        if(msg.equals("ok")){
            System.out.println(msg);
            List UsuList=serUsu.listaUsu();
            sal.setUsuarios(UsuList);
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
