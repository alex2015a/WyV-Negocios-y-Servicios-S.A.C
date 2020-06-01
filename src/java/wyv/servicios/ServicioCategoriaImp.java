package wyv.servicios;

import java.util.List;
import wyv.persistencia.Categoria;
import wyv.persistencia.DaoCategoria;
import wyv.persistencia.Producto;
import wyv.presentacion.EntradaCategoria;

public class ServicioCategoriaImp implements ServicioCategoria{

    DaoCategoria daoCate;
    Categoria cate;

    public void setDaoCate(DaoCategoria daoCate) {
        this.daoCate = daoCate;
    }

    @Override
    public String registrarCategoria(EntradaCategoria entCate) {
        cate=new Categoria();
        cate.setNombre(entCate.getNombre());
        cate.setDescripcion(entCate.getDescripcion());
        return daoCate.grabarCategoria(cate);
    }

    @Override
    public List listarCategoria() {
        return daoCate.listarCategoria();
    }
    
    @Override
    public String eliminarCategoria(int id) {
        return daoCate.eliminarCategoria(id);
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return daoCate.buscarCategoria(id);
    }
    @Override
    public void nuevaCategoria() {
       cate=new Categoria();
    }
    
    @Override
    public String editarCategoria(EntradaCategoria entCate, List<Producto> productos) {
        cate=new Categoria();
        cate.setCodigo(entCate.getCodigo());
        cate.setNombre(entCate.getNombre());
        cate.setDescripcion(entCate.getDescripcion());
        cate.setProductoList(productos);
        
        return daoCate.editarCategoria(cate);
    }
    
}
