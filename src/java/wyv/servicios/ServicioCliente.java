package wyv.servicios;

import java.util.List;
import wyv.persistencia.Cliente;
import wyv.persistencia.Venta;
import wyv.presentacion.EntradaCliente;

public interface ServicioCliente {
    public String registrarCliente(EntradaCliente entCli);
    public List listarCliente();
    public void nuevoCliente();
    public String eliminarCliente(int id);
    public Cliente buscarCliente(int id);
    public String editarCliente(EntradaCliente ent, List<Venta> ventas);
}
