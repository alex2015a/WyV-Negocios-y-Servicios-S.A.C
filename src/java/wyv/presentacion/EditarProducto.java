/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.persistencia.Producto;
import wyv.servicios.ServicioProducto;
import wyv.servicios.ServicioVenta;

/**
 *
 * @author AUTONOMA
 */
public class EditarProducto extends org.apache.struts.action.Action {

    String pagina;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         EntradaProducto entProd=(EntradaProducto)form;
        ServicioProducto servProd=(ServicioProducto)request.getSession().getAttribute("servProd");
        ServicioVenta servVenta=(ServicioVenta)request.getSession().getAttribute("servVenta");
        List detalles=servVenta.listarDetalle();
        Salida sal=(Salida)request.getSession().getAttribute("sal");
        
        String msg=servProd.editarProducto(entProd, detalles);
        if (msg.equals("ok")) {
            List productos=servProd.listarPro();
            sal.setProductos(productos);
            request.getSession().setAttribute("salLisP", sal);
            pagina = "ListarProducto";
        } else {
            sal.setMsg(msg);
            request.getSession().setAttribute("mensaje", sal);
            pagina = "Mensaje";
        }
        
        return mapping.findForward(pagina);
    }
}
