package wyv.persistencia;

import java.util.List;


public class DaoClienteImp implements DaoCliente{

        ClienteJpa clieJpa=new ClienteJpa();
        
       String msg;
       
    @Override
    public String grabarCliente(Cliente cli) {
       
        try {
            clieJpa.create(cli);
            msg="Cliente Registrado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        
       return msg;
    }

    @Override
    public List listarCliente() {
       return clieJpa.findClienteEntities();      
    }

    @Override
    public String eliminarCliente(int id) {
       
        try {
            clieJpa.destroy(id);
            msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public Cliente buscarCliente(int id) {
        Cliente clie=new Cliente();
        try {
           clie=clieJpa.findCliente(id);
        } catch (Exception e) {
        }
        
        return clie;
    }

    @Override
    public String editarCliente(Cliente clie) {
        try {
            clieJpa.edit(clie);
            msg="ok";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }
    
}
