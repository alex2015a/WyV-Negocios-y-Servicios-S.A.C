
package wyv.servicios;

import java.util.List;
import wyv.persistencia.Cliente;
import wyv.persistencia.DaoCliente;
import wyv.persistencia.Venta;
import wyv.presentacion.EntradaCliente;


public class ServicioClienteImp implements ServicioCliente {

    DaoCliente daoClie;
    Cliente clie;
    public void setDaoClie(DaoCliente daoClie) {
        this.daoClie = daoClie;
    }
    
    @Override
    public String registrarCliente(EntradaCliente entCli) {
        clie=new Cliente();
        
        clie.setIdcli(entCli.getId());
        clie.setNomCli(entCli.getNom());
        clie.setTeleCli(entCli.getTelf());
        clie.setDireCli(entCli.getDirecc());
        clie.setEmail(entCli.getEmail());
        clie.setDni(entCli.getDni());
        return daoClie.grabarCliente(clie);
        
    }

    @Override
    public List listarCliente() {
        return daoClie.listarCliente();
    }

    @Override
    public void nuevoCliente() {
      clie=new Cliente();
    }

    @Override
    public String eliminarCliente(int id) {
      return daoClie.eliminarCliente(id);
    }

    @Override
    public Cliente buscarCliente(int id) {
        return daoClie.buscarCliente(id);
    }

    @Override
    public String editarCliente(EntradaCliente ent, List<Venta> ventas) {
       clie=new Cliente();
        
      clie.setIdcli(ent.getId());
      clie.setNomCli(ent.getNom());
      clie.setTeleCli(ent.getTelf());
      clie.setDireCli(ent.getDirecc());
      clie.setEmail(ent.getEmail());
      clie.setDni(ent.getDni());
      clie.setVentaList(ventas);

      return daoClie.editarCliente(clie);
    }
    
}
