package wyv.servicios;

import java.util.List;
import wyv.negocio.ClienteObj;
import wyv.negocio.UsuarioObj;
import wyv.negocio.VentaObj;
import wyv.persistencia.Venta;


public interface ServicioVenta {
    public VentaObj nuevaVenta();
    public void agregarProducto(String codPro,String nombre,int can,double precioven, int descuento);
    public String registrarVenta(UsuarioObj u, ClienteObj clie,  VentaObj ven, double pago);
    public List listarVenta();
    public List listarDetalle();
    public String AnularVenta(Venta v);
}
