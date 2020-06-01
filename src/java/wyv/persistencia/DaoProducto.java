
package wyv.persistencia;

import java.util.List;


public interface DaoProducto {
    public String grabarpro(Producto pro);
    public List listaPro();
    public Producto buscarpro(int id);
    public String Eliminar(int id);
    public String editar(Producto pro);
    
}
