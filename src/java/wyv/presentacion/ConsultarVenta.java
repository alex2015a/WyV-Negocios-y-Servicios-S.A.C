/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyv.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import wyv.persistencia.Cliente;
import wyv.persistencia.Usuario;
import wyv.persistencia.Util;
import wyv.persistencia.Venta;
import wyv.servicios.ServicioVenta;

/**
 *
 * @author OSCAR
 */
@WebServlet(name = "ConsultarVenta", urlPatterns = {"/ConsultarVenta"})
public class ConsultarVenta extends HttpServlet {

  
    private Util mysql = new Util();
    private Connection cn = mysql.conectar();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    
        
        int opv= Integer.parseInt(request.getParameter("op"));
        
        switch (opv){
            
            case 1: {
                
                String numv= request.getParameter("numV");
                
                if(numv!=null)  {
                    try{
                      
                          
                        JasperReport jr = (JasperReport) JRLoader.loadObject(VistaComprobante.class.getResource("/wyv/presentacion/RptFactura.jasper"));

                        Map parametro = new HashMap<String, String>();
                        parametro.put("num", numv);

                        JasperPrint jp = JasperFillManager.fillReport(jr, parametro,cn);
                        JasperPrintManager.printReport( jp, true);
                        
                        response.sendRedirect("ListarVenta.jsp"); 
                        
                    }catch (JRException e) {
                    e.getMessage();
                    }
               }else{
                    
                     response.sendRedirect("mensaje.jsp");   
               }
                
            break;}
            
            case 2:
            {
                
                ServicioVenta servVenta=(ServicioVenta)request.getSession().getAttribute("servVenta");
                String numV= request.getParameter("numV");
                String dniU= request.getParameter("dniU");
                String idClie= request.getParameter("idClie");
                int idC=Integer.parseInt(idClie);
                
                String fecha= request.getParameter("fecha");
                Double total = Double.parseDouble(request.getParameter("total"));
                Double Pago = Double.parseDouble(request.getParameter("pago"));
                int estado = Integer.parseInt(request.getParameter("estado"));
              
                
                Usuario u=new Usuario();
                u.setDni(dniU);
                Cliente cli=new Cliente();
                cli.setIdcli(idC);
                
                Venta venta=new Venta();
                
                venta.setNum(numV);
                venta.setDniUsu(u);
                venta.setIdcli(cli);
                venta.setFec(fecha);
                venta.setTotal(total);
                venta.setPago(Pago);
                venta.setEstado(estado);

                servVenta.AnularVenta(venta);
                
                Salida sal=(Salida)request.getSession().getAttribute("sal");
                List ventaL= servVenta.listarVenta();
                sal.setVentas(ventaL);
                  request.getSession().setAttribute("Listventa", sal);
                  response.sendRedirect("ListarVenta.jsp");
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
