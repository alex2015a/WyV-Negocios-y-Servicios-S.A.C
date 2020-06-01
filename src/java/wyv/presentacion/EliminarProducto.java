package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.servicios.ServicioProducto;

public class EliminarProducto extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

 String pagina;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EntradaProducto entPro=(EntradaProducto)form;
        ServicioProducto servProd=(ServicioProducto)request.getSession().getAttribute("servProd");
        Salida sal=(Salida)request.getSession().getAttribute("sal");
        String msg=servProd.EliminarPro(entPro.getCod());
        if(msg.equals("ok")){
            List productos=servProd.listarPro();
            sal.setProductos(productos);
            request.getSession().setAttribute("salLisP", sal);
        
          pagina="ListarProducto";
        }else{
           JOptionPane.showMessageDialog(null,"Error, el producto que desea eliminar esta siendo utilizado");
           
      
            pagina="ListarProducto";
        }
        return mapping.findForward(pagina);
    }
}
