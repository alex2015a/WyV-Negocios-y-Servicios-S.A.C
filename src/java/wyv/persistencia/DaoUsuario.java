package wyv.persistencia;

import java.util.List;

public interface DaoUsuario {
    
      public Usuario buscar(String dni);
      public List listaUsu();
      public String EliminarU(String dni);
      public String Registrarusu(Usuario usu);
      public String editarUsu(Usuario usu);
      
}
