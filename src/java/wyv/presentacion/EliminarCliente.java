package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioCliente;

public class EliminarCliente extends org.apache.struts.action.Action {

    String pagina;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EntradaCliente entCli=(EntradaCliente)form;
        ServicioCliente servClie=(ServicioCliente)request.getSession().getAttribute("servClie");
        Salida sal=(Salida)request.getSession().getAttribute("sal");
        String msg=servClie.eliminarCliente(entCli.getId());
        if(msg.equals("ok")){
            List clientes=servClie.listarCliente();
            sal.setClientes(clientes);
            request.getSession().setAttribute("salLisClie", sal);
        
          pagina="ListarCliente";
        }else{
           sal.setMsg(msg);
           JOptionPane.showMessageDialog(null,"Error, el cliente que desea eliminar esta siendo utilizado");
           pagina="Mensaje";
        }
        
        return mapping.findForward(pagina);
    }
}
