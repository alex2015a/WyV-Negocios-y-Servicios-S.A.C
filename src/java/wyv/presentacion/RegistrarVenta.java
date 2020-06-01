package wyv.presentacion;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wyv.persistencia.Util;
import wyv.negocio.ClienteObj;
import wyv.negocio.UsuarioObj;
import wyv.negocio.VentaObj;
import wyv.servicios.ServicioVenta;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


@WebServlet(name = "RegistrarVenta", urlPatterns = {"/RegistrarVenta"})
public class RegistrarVenta extends HttpServlet {

    private Util mysql = new Util();
    private Connection cn = mysql.conectar();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcad= request.getParameter("op");
        int op=Integer.parseInt(opcad);
        
        switch(op)
        {
            case 1: {
               
                String cod= request.getParameter("codPro");
                String nom= request.getParameter("nom");
                int descuento= Integer.parseInt(request.getParameter("desc"));
                int can= Integer.parseInt(request.getParameter("can"));
                double preven= Double.parseDouble(request.getParameter("preven"));

                ServicioVenta servVenta=(ServicioVenta)request.getSession().getAttribute("servVenta");
                servVenta.agregarProducto(cod, nom, can, preven, descuento);

                response.sendRedirect("registrarVenta.jsp");
                break;
            }
           
           case 2:{

                String cod=request.getParameter("cod");
                System.out.println(cod);
                VentaObj ces=(VentaObj)request.getSession().getAttribute("venObj");

                ces.quitar(cod);
                response.sendRedirect("registrarVenta.jsp");

                break;
           }
           
           case 3:{

                UsuarioObj usu=new UsuarioObj();
                usu.setDni(request.getParameter("usua"));
                double pago=Double.parseDouble(request.getParameter("pago"));
                ClienteObj clie=new ClienteObj();
                clie.setIdCli(request.getParameter("clie"));
                VentaObj venta=(VentaObj)request.getSession().getAttribute("venObj");
                ServicioVenta servVenta=(ServicioVenta)request.getSession().getAttribute("servVenta");
                Salida salven=new Salida();
                
                String cod=servVenta.registrarVenta(usu, clie, venta, pago);
                if(cod!=null)  {
                    try{
                        float vuelto =(float)(venta.getPago()-venta.getTot());
                        salven.setMsg("Venta registrada correctamente!!\nVuelto: s/"+vuelto);
                        response.sendRedirect("mensaje.jsp");   
                        JasperReport jr = (JasperReport) JRLoader.loadObject(VistaComprobante.class.getResource("/wyv/presentacion/RptFactura.jasper"));

                        Map parametro = new HashMap<String, String>();
                        parametro.put("num", cod);

                        JasperPrint jp = JasperFillManager.fillReport(jr, parametro,cn);
                        JasperPrintManager.printReport( jp, true);
                        
                    }catch (JRException e) {
                    e.getMessage();
                    }
               }else{
                    salven.setMsg("Error en el codigo de la venta");
                     response.sendRedirect("mensaje.jsp");   
               }
  
               venta.getCesta().clear();//eliminamos la cesta
               request.getSession().setAttribute("venObj", venta);//mandamos a sesion la cesta vacía
               request.getSession().setAttribute("mensaje", salven);
              
               
               break;
           }
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
