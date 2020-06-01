package wyv.servicios;

import java.util.List;
import wyv.presentacion.EntradaUsuario;
import wyv.persistencia.DaoUsuario;
import wyv.persistencia.Usuario;
import wyv.persistencia.Venta;

public class ServicioUsuarioImp implements ServicioUsuario{
    
    
     DaoUsuario daoUsu;
     Usuario usu;

    public void setDaoUsu(DaoUsuario daoUsu) {
        this.daoUsu = daoUsu;
    }

    @Override
    public Usuario Acceso(EntradaUsuario ent) {
        usu = daoUsu.buscar(ent.getDni());
        if (usu != null) {
            if (usu.getPass().equals(ent.getPass())) {
                return usu;
            }
        }
        return null;
    }

 @Override
    public Usuario buscarUsu(String dni) {
         return daoUsu.buscar(dni);
    }

    @Override
    public List listaUsu() {
        return daoUsu.listaUsu();
    }

    @Override
    public String EliminarU(String dni) {
       return daoUsu.EliminarU(dni);
    }

    @Override
    public String Registrarusu(EntradaUsuario ent) {
       
        usu=new Usuario();
        
        usu.setDni(ent.getDni());
        usu.setNom(ent.getNom());
        usu.setPass(ent.getPass());
        usu.setTipo(ent.getTipo());
        
        return daoUsu.Registrarusu(usu);
    }

    @Override
    public String editarUsu(EntradaUsuario ent, List<Venta> ventas) {
        usu=new Usuario();
        usu.setDni(ent.getDni());
        usu.setNom(ent.getNom());
        usu.setPass(ent.getPass());
        usu.setTipo(ent.getTipo());
        usu.setVentaList(ventas);
        return daoUsu.editarUsu(usu);
    }
    @Override
    public void nuevoUsuario() {
       usu=new Usuario();
    }
}
