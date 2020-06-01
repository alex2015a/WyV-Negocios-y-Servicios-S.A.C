package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioCliente;
import wyv.servicios.ServicioVenta;

public class EditarCliente extends org.apache.struts.action.Action {

    String pagina;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EntradaCliente entClie=(EntradaCliente)form;
        ServicioCliente servClie=(ServicioCliente)request.getSession().getAttribute("servClie");
        ServicioVenta servVenta=(ServicioVenta)request.getSession().getAttribute("servVenta");
        List ventas=servVenta.listarVenta();
        String msg=servClie.editarCliente(entClie, ventas);
        Salida sal=(Salida)request.getSession().getAttribute("sal");
        
        if(msg.equals("ok")){
            List ClieList=servClie.listarCliente();
            sal.setClientes(ClieList);
            request.getSession().setAttribute("salLisClie", sal);
            pagina="ListarCliente";
        }else{
            sal.setMsg(msg);
            request.getSession().setAttribute("mensaje", sal);
            pagina="Mensaje";
        }

        return mapping.findForward(pagina);
    }
}
