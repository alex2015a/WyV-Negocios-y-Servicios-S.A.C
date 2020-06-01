package wyv.presentacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wyv.negocio.VentaObj;
import wyv.persistencia.DaoVentaImp;
import wyv.servicios.ServicioUsuario;
import wyv.persistencia.Usuario;
import wyv.servicios.ServicioCategoria;
import wyv.servicios.ServicioCliente;
import wyv.servicios.ServicioProducto;
import wyv.servicios.ServicioVenta;

public class Acceso extends org.apache.struts.action.Action {

    ServicioUsuario serUsu;
    ServicioCliente servClie;
    ServicioProducto servProd;
    ServicioCategoria servCate;
    ServicioVenta servVenta;

    public void setServVenta(ServicioVenta servVenta) {
        this.servVenta = servVenta;
    }
    
    public void setServClie(ServicioCliente servClie) {
        this.servClie = servClie;
    }
    
    public void setSerUsu(ServicioUsuario serUsu) {
        this.serUsu = serUsu;
    }

    public void setServProd(ServicioProducto servProd) {
        this.servProd = servProd;
    }

    public void setServCate(ServicioCategoria servCate) {
        this.servCate = servCate;
    }


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
       EntradaUsuario ent=(EntradaUsuario)form;
        Usuario usu=serUsu.Acceso(ent);
        
        if(usu!= null)
        {
            servClie.nuevoCliente();
            serUsu.nuevoUsuario();
            servCate.nuevaCategoria();
            servProd.nuevoProducto();
            VentaObj venObj=servVenta.nuevaVenta();
            DaoVentaImp daoV=new DaoVentaImp();
            daoV.generaNumero();
            Salida sal=new Salida();
            sal.setClientes(servClie.listarCliente());
            sal.setUsuarios(serUsu.listaUsu());
            sal.setCategorias(servCate.listarCategoria());
            sal.setProductos(servProd.listarPro());
            sal.setVentas(servVenta.listarVenta());
            request.getSession().setAttribute("salLisClie", sal);
            request.getSession().setAttribute("salLis", sal);
            request.getSession().setAttribute("salLisP", sal);
            request.getSession().setAttribute("salLisCate", sal);
            request.getSession().setAttribute("salLisVen", sal);
            request.getSession().setAttribute("servClie", servClie);
            request.getSession().setAttribute("serUsu", serUsu);
            request.getSession().setAttribute("servProd", servProd);
            request.getSession().setAttribute("servCate", servCate);
            request.getSession().setAttribute("servVenta", servVenta);
            request.getSession().setAttribute("venObj", venObj);
            request.getSession().setAttribute("daoV", daoV);
            request.getSession().setAttribute("sal", sal);
            
            sal.setMsg("Usuario: "+usu.getNom());
            sal.setDniusu(usu.getDni());
            sal.setTipo(usu.getTipo());
            
            return mapping.findForward("Principal");
        }else
        {
            
            request.getSession().setAttribute("errorLogin", "error");
            return mapping.findForward("Login");
        }
       
    }
}
