package wyv.presentacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.negocio.VentaObj;

public class RedireccionVenta extends org.apache.struts.action.Action {

 
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         VentaObj venObj=new VentaObj(); 
        List cesta=(List)venObj.getCesta();
        cesta.clear();
        return mapping.findForward("RegistrarVenta");
    }
}
