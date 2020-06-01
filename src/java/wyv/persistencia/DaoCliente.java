
package wyv.persistencia;

import java.util.List;

public interface DaoCliente {
  public String grabarCliente(Cliente cli);  
  public List listarCliente();
  public String eliminarCliente(int id);
  public Cliente buscarCliente(int id);
  public String editarCliente(Cliente clie);
    
}
