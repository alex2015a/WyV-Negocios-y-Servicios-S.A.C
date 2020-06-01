package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioCategoria;
import wyv.servicios.ServicioProducto;

public class EditarCategoria extends org.apache.struts.action.Action {

   String pagina;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        EntradaCategoria entCate=(EntradaCategoria)form;
        ServicioCategoria servCate=(ServicioCategoria)request.getSession().getAttribute("servCate");
        ServicioProducto servProd=(ServicioProducto)request.getSession().getAttribute("servProd");
        List productos=servProd.listarPro();
        Salida sal=(Salida)request.getSession().getAttribute("sal");
        String msg = servCate.editarCategoria(entCate, productos);
           
        if (msg.equals("ok")) {
            List CateList = servCate.listarCategoria();
            sal.setCategorias(CateList);
            request.getSession().setAttribute("salLisCate", sal);
            pagina = "ListarCategoria";
        } else {
            sal.setMsg(msg);
            request.getSession().setAttribute("mensaje", sal);
            pagina = "Mensaje";
        }

        return mapping.findForward(pagina);
    }
}
