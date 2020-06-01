/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.servicios;

import java.util.List;
import wyv.presentacion.EntradaUsuario;
import wyv.persistencia.Usuario;
import wyv.persistencia.Venta;

/**
 *
 * @author Data
 */
public interface ServicioUsuario {
      public Usuario Acceso(EntradaUsuario ent);
      public Usuario buscarUsu(String dni);
      public List listaUsu();
      public String EliminarU(String dni);
      public void nuevoUsuario();
      public String Registrarusu(EntradaUsuario ent);
      public String editarUsu(EntradaUsuario ent,List<Venta> ventas);
}
