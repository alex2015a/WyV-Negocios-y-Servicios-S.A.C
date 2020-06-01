package wyv.servicios;

import java.util.List;
import wyv.negocio.ClienteObj;
import wyv.negocio.ProductoObj;
import wyv.negocio.UsuarioObj;
import wyv.negocio.VentaObj;
import wyv.persistencia.DaoVentaImp;
import wyv.persistencia.Venta;


public class ServicioVentaImp implements ServicioVenta {

    DaoVentaImp daoVen;
    VentaObj ventObj;

    public void setDaoVen(DaoVentaImp daoVen) {
        this.daoVen = daoVen;
    }

    @Override
    public String registrarVenta(UsuarioObj u, ClienteObj clie,  VentaObj ven, double pago) {

        ven.setUsu(u);
        ven.setPago(pago);
        ven.setCli(clie);
        ven.setNum(daoVen.generaNumero());
        ven.getFec();

        return daoVen.registrar(ven);
    }

    @Override
    public VentaObj nuevaVenta() {
        return ventObj=new VentaObj();
    }

    @Override
    public void agregarProducto(String codPro, String nombre, int can, double precioven, int descuento) {
        ProductoObj proObj=new ProductoObj();
        
        proObj.setCodPro(codPro);
        proObj.setNombre(nombre); 
        proObj.setPrecioven(precioven);
        proObj.setDescuento(descuento);
        
        ventObj.agregar(proObj,can);
       
    }

    @Override
    public List listarVenta() {
        return daoVen.litarVenta();
    }

    @Override
    public List listarDetalle() {
        return daoVen.listarDetalle();
    }

    @Override
    public String AnularVenta(Venta v) {
        Venta venta=new Venta();
        
        venta.setNum(v.getNum());
        venta.setDniUsu(v.getDniUsu());
        venta.setIdcli(v.getIdcli());
        venta.setFec(v.getFec());
        venta.setTotal(v.getTotal());
        venta.setPago(v.getPago());
        venta.setEstado(v.getEstado());
         
        return daoVen.Anular(venta);
    }

}
