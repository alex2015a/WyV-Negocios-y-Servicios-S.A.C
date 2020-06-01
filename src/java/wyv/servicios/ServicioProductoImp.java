
package wyv.servicios;

import java.util.List;
import wyv.persistencia.Categoria;
import wyv.persistencia.DaoProducto;
import wyv.persistencia.Detalle;
import wyv.persistencia.Producto;
import wyv.presentacion.EntradaProducto;


public class ServicioProductoImp implements ServicioProducto{

    DaoProducto daoProd;
    Producto prod;
    Categoria  cat;
    
    public void setDaoProd(DaoProducto daoProd) {
        this.daoProd = daoProd;
    }

    @Override
    public String RegistrarPro(EntradaProducto pro) {

        prod=new Producto();

        prod.setCodPro(pro.getCod());
        prod.setNombre(pro.getNom());
        prod.setCantidad(Integer.parseInt(pro.getCantidad()));
        prod.setProveedor(pro.getProveedor());
        prod.setPreciocom(Double.parseDouble(pro.getPreco()));
        prod.setPrecioven(Double.parseDouble(pro.getPreven()));
        prod.setDescuento(Integer.parseInt(pro.getDescuento()));
        cat=new Categoria();
        cat.setCodigo(Integer.parseInt(pro.getCodigocat()));
        prod.setCodigocat(cat);
        
      return  daoProd.grabarpro(prod);
 
    }
  
 
    @Override
    public List listarPro() {
        return daoProd.listaPro();
    }

    @Override
    public Producto buscarProd(int codPro) {    
       return  daoProd.buscarpro(codPro);
    }


    @Override
    public String EliminarPro(int id) {
        return daoProd.Eliminar(id);
    }

    @Override
    public String editarProducto(EntradaProducto pro, List<Detalle> detalles) {
       prod=new Producto();
       prod.setCodPro(pro.getCod());
       prod.setNombre(pro.getNom());
       prod.setProveedor(pro.getProveedor());
       prod.setCantidad(Integer.parseInt(pro.getCantidad()));
       prod.setPreciocom(Double.parseDouble(pro.getPreco()));
       prod.setPrecioven(Double.parseDouble(pro.getPreven()));
       prod.setDescuento(Integer.parseInt(pro.getDescuento()));
       cat=new Categoria();
       cat.setCodigo(Integer.parseInt(pro.getCodigocat()));
       prod.setCodigocat(cat);
       prod.setDetalleList(detalles);
       
        return daoProd.editar(prod);
    }

    @Override
    public void nuevoProducto() {
        prod=new Producto();
    }

}
