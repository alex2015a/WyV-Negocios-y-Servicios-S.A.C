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
import wyv.servicios.ServicioCategoria;
import wyv.servicios.ServicioProducto;

/**
 *
 * @author Data
 */
public class RegistrarProducto extends org.apache.struts.action.Action {

        String pagina;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        EntradaProducto entpro=(EntradaProducto)form;
        ServicioProducto servPro=(ServicioProducto)request.getSession().getAttribute("servProd");
        Salida sal=new Salida();
        
        String msg=servPro.RegistrarPro(entpro);
        
        if(msg.equals("ok")){
            List productos=servPro.listarPro();
            sal.setProductos(productos);
            request.getSession().setAttribute("salLisP", sal);
            pagina="ListarProducto";
        }else{
            sal.setMsg(msg);
            request.getSession().setAttribute("mensaje", sal);
            pagina="Mensaje";
        }
        
        request.getSession().setAttribute("salPro", sal);

        return mapping.findForward(pagina);
    }
}
