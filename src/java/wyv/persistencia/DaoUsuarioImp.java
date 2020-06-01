
package wyv.persistencia;

import java.util.List;

public class DaoUsuarioImp implements DaoUsuario{
    UsuarioJpa UsuJpa=new UsuarioJpa();
    String msg;
    
    @Override
    public Usuario buscar(String dni) {
       Usuario usu=new Usuario();
        try {
          usu= UsuJpa.findUsuario(dni);
        } catch (Exception e) {
          e.getMessage();
        }
     return usu;
    }

    @Override
    public List listaUsu() {
        return UsuJpa.findUsuarioEntities();
    }

    @Override
    public String EliminarU(String dni) {
        
        try {
            UsuJpa.destroy(dni);
            msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String Registrarusu(Usuario usu) {
      
        try {
            UsuJpa.create(usu);
            msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String editarUsu(Usuario usu) {
        try {
            UsuJpa.edit(usu);
            msg="ok";
        } catch (Exception ex) {
            msg=ex.getMessage();
        }
        return msg;
    }
    
}
