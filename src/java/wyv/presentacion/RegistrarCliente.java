package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioCliente;

public class RegistrarCliente extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        EntradaCliente entCli=(EntradaCliente)form;
        ServicioCliente servClie=(ServicioCliente)request.getSession().getAttribute("servClie");
        Salida salCli=new Salida();
        salCli.setMsg(servClie.registrarCliente(entCli));
        request.getSession().setAttribute("salCli", salCli);
        Salida sal=(Salida)request.getSession().getAttribute("sal");
        List CliList=servClie.listarCliente();
        sal.setClientes(CliList);
        request.getSession().setAttribute("salLisClie", sal);
        return mapping.findForward("ListarCliente");
    }
}
