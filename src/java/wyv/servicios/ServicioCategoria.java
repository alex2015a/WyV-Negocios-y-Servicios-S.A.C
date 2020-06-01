package wyv.servicios;

import java.util.List;
import wyv.persistencia.Categoria;
import wyv.persistencia.Producto;
import wyv.presentacion.EntradaCategoria;

public interface ServicioCategoria {
    
    public String registrarCategoria(EntradaCategoria entCate);
    public List listarCategoria();
    public String eliminarCategoria(int id);
    public void nuevaCategoria();
    public Categoria buscarCategoria(int id);
    public String editarCategoria(EntradaCategoria entCate, List<Producto> productos);

}
