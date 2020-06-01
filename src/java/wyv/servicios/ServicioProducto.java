/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.servicios;

import java.util.List;
import wyv.persistencia.Categoria;
import wyv.persistencia.Detalle;
import wyv.persistencia.Producto;
import wyv.presentacion.EntradaProducto;


/**
 *
 * @author Data
 */
public interface ServicioProducto {
    public String RegistrarPro(EntradaProducto pro);
    public List listarPro();
    public String EliminarPro(int id);
    public void nuevoProducto();
    public Producto buscarProd(int codpro); 
    public String editarProducto(EntradaProducto pro, List<Detalle> detalles);
     
   
}
