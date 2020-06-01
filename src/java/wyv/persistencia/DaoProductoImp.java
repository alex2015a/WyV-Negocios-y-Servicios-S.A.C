
package wyv.persistencia;

import java.util.List;

public class DaoProductoImp implements DaoProducto {

    ProductoJpa proJpa=new ProductoJpa();;
    String msg;
    
    @Override
    public String grabarpro(Producto pro) {
        try {
        proJpa.create(pro);
        msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listaPro() {
         return proJpa.findProductoEntities();
        
    }

    @Override
    public Producto buscarpro(int id) {
        Producto pro=new Producto();
        try {
            pro= proJpa.findProducto(id);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        return pro; 
    }

    @Override
    public String Eliminar(int id) {
        try {
            proJpa.destroy(id);
            msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
       return msg;
    }

    @Override
    public String editar(Producto pro) {
        try {
            proJpa.edit(pro);
             msg="ok";
        } catch (Exception e) {
             msg=e.getMessage();
        }
        return msg;
    }
    
}
