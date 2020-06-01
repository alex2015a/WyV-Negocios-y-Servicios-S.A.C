package wyv.persistencia;

import java.util.List;
import wyv.persistencia.exceptions.IllegalOrphanException;
import wyv.persistencia.exceptions.NonexistentEntityException;

public class DaoCategoriaImp  implements  DaoCategoria{

    CategoriaJpa cateJpa=new CategoriaJpa();
    String msg;
    @Override
    public String grabarCategoria(Categoria cate) {
       
        try {
            cateJpa.create(cate);
            msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listarCategoria() {
       return cateJpa.findCategoriaEntities();
    }

    @Override
    public String eliminarCategoria(int id) {
        try {
            cateJpa.destroy(id);
            msg="ok";
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            msg=e.getMessage();
        }
       return msg;
    }

    @Override
    public Categoria buscarCategoria(int id) {
        Categoria cate=new Categoria();
        try {
           cate=cateJpa.findCategoria(id);
        } catch (Exception e) {
        }
        return cate;
    }

    @Override
    public String editarCategoria(Categoria cate) {

        try {
            cateJpa.edit(cate);
            msg="ok";
        } catch (Exception ex) {
            msg=ex.getLocalizedMessage();
        }
        return msg;
    }
}
