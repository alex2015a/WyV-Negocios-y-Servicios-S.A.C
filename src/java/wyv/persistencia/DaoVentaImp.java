package wyv.persistencia;

import java.util.List;

import javax.persistence.Persistence;
import wyv.negocio.Linea;
import wyv.negocio.VentaObj;

public class DaoVentaImp implements DaoVenta{
    private final VentaJpa venJpa=new VentaJpa();
    private final DetalleJpa detJpa=new DetalleJpa();

    @Override
    public String registrar(VentaObj venObj) {
        String msg=null;
        Venta vent=new Venta();
        Usuario usu=new Usuario();
        usu.setDni(venObj.getUsu().getDni());
        Cliente clie=new Cliente();
        clie.setIdcli(Integer.parseInt(venObj.getCli().getIdCli()));
        
        vent.setDniUsu(usu);
        vent.setFec(venObj.getFec());
        vent.setPago(venObj.getPago());
        vent.setIdcli(clie);
        vent.setNum(venObj.getNum());
        vent.setTotal(venObj.getTot());
        vent.setEstado(1);
        
        try {
            venJpa.create(vent);
            List cesta=(List)venObj.getCesta();
            for(int i=0;i<cesta.size();i++)
            {
               Linea lin = (Linea)venObj.getCesta().get(i);
                Detalle det=new Detalle();
  
                det.setCan(lin.getCan());
                det.setImporte(lin.getImporte());
                det.setPrecio(lin.getProObj().getPrecioven());
                Producto pro=new Producto();
                pro.setCodPro(Integer.parseInt(lin.getProObj().getCodPro()));
                pro.setNombre(lin.getProObj().getNombre());
                pro.setCantidad(lin.getProObj().getStock());
                pro.setPreciocom(lin.getProObj().getPreciocom());
                pro.setPrecioven(lin.getProObj().getPrecioven());
         
                det.setProducto(pro);
                det.setVenta(vent);
                DetallePK detP=new DetallePK();
                detP.setCodPro(Integer.parseInt(lin.getProObj().getCodPro()));
                detP.setNumVenta(vent.getNum());
                det.setDetallePK(detP);
                detJpa.create(det);
            }
             msg=vent.getNum();
        } catch (Exception e) {
            msg=e.getMessage();
        }
        
        return msg;
    }

    @Override
    public String generaNumero() {
        
        String numObt=null;
        String numGen=null;
        List lis=venJpa.findVentaEntities();
        if(lis.size()>0){
        for(int i=0;i<lis.size();i++){
            Venta vent=(Venta)lis.get(i);
            numObt=vent.getNum();
        }
        int parInt=Integer.parseInt(numObt.substring(2));//obtenemos la parte entera
        String parStr=numObt.substring(0,1);//obtenermos la parte cadena
        String incParInt=String.valueOf(parInt+1);//incrementamos +1 la parte entera y lo convertimos a cadena
        while(incParInt.length()<5){
            incParInt="0"+incParInt;
        }
        numGen=parStr+incParInt;
        }else{
            numGen="V00001";
        }
        return numGen;
    }

    @Override
    public List litarVenta() {
        return venJpa.ListarVenta();
    }

    @Override
    public List listarDetalle() {
       return detJpa.findDetalleEntities();
    }

    @Override
    public String Anular(Venta v) {
     String resultado;
        try {
      
           venJpa.edit(v);
           
           Detalle det=new Detalle();
                
                detJpa.edit(det);
           
            resultado="ok";
        } catch (Exception e) {
             resultado=e.getMessage();
        }
        return resultado;
    }

  
    
    
}
