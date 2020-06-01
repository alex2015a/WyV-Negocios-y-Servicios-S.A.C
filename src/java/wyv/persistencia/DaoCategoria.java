package wyv.persistencia;

import java.util.List;

public interface DaoCategoria {
    
  public String grabarCategoria(Categoria cate);  
  public List listarCategoria();
  public String eliminarCategoria(int id);
  public Categoria buscarCategoria(int id);
  public String editarCategoria(Categoria cate);
    
}
