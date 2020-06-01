<%@page import="wyv.persistencia.Usuario"%>
<%@page import="wyv.negocio.VentaObj"%>
<%@page import="wyv.persistencia.Venta"%>
<%@page import="wyv.persistencia.Producto"%>
<%@page import="wyv.presentacion.Salida"%>

<%@ include file="templaste/cabecera.jsp"%>
<link href="css/estilos.css" rel="stylesheet" type="text/css"/>
<%@ include file="templaste/sidebar.jsp" %>



<div class="content-wrapper" style="min-height: 738.392px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container">
            <div class="row mb-2">
          <div class="col-sm-12 mb-5">
                      <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Pagina Inicio</li>
                      </ol>
         </div>  
         <form id="Cventa" name="Cventa" method="GET">
                                <input type="hidden" name="op">
                                <input type="hidden" name="numV" id="numV" >
                                <input type="hidden" name="dniU" id="dniU" >
                                <input type="hidden" name="idClie" id="idClie" >
                                <input type="hidden" name="fecha" id="fecha" >
                                <input type="hidden" name="total" id="total">
                                <input type="hidden" name="pago" id="pago">
                                <input type="hidden" name="estado" id="estado">

        <div class="col-sm-12">
              <h3 class="text-center text-uppercase mb-5">LISTADO DE VENTAS</h3>  
                                 
                                 <% Salida Listventa=(Salida)session.getAttribute("Listventa"); %>  
                                 
                          <div id="tbl">
                        <div class="table-responsive table-centered">
                            <table id="datos" class="table table-hover">
                                <thead>
                                           <tr class="">
                                           <th>Codigo</th>  
                                           <th>Usuario</th>
                                           <th>DNI</th>
                                           <th>cliente</th>
                                           <th>Fecha</th>
                                           <th>Importe Total</th> 
                                           <th>Estado</th>
                                           <th></th>
                                           <th></th>
                                       </tr> 
                                       </thead>
                                <tbody>
                                <% for(int i=0;i<Listventa.getVentas().size();i++){ %>
                                       <% Venta venta=(Venta)Listventa.getVentas().get(i); %>
                            <tr>
                                
                                    <td><%= String.valueOf(venta.getNum())%></td>
                                           <td><%= String.valueOf(venta.getDniUsu().getNom() )%></td>
                                           <td><%= String.valueOf(venta.getDniUsu())%></td>
                                           <td><%= String.valueOf(venta.getIdcli().getNomCli())%></td>                                  
                                           <td><%= String.valueOf(venta.getFec())%></td>
                                           <td><%= String.valueOf(venta.getTotal())%></td>
                                           <td id="tdestado"><%= String.valueOf(venta.getEstado())%></td>
                                           <td><button class="fa fa-search btn  btn-info" id="btnConsultar" onclick="ConsultarVenta('<%= venta.getNum()%>')"/></td>

                                           <td><button class="fas fa-ban btn  btn-danger " id="btn-anular" name="btn-anular"
                                            onclick="Anularventa('<%= venta.getNum() %>','<%= venta.getDniUsu()%>',
                                                     '<%= String.valueOf(venta.getIdcli()) %>','<%= venta.getFec() %>','<%= venta.getTotal()%>',
                                                     '<%= venta.getPago()%>')"/></td>
                                           
                          
                              
                            </tr>
                                 <% } %>
                                </tbody>
                                <tfoot>
                                    <th>Codigo</th>  
                                           <th>Usuario</th>
                                           <th>DNI</th>
                                           <th>cliente</th>
                                           <th>Fecha</th>
                                           <th>Importe Total</th> 
                                           <th>Estado</th>
                                           <th></th>
                                           <th></th>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                                
                              
        </div>
        </form> 
         </div>     
      </div> 
    </section>
</div>
<%@include file="templaste/footer.jsp"%>
<%@ include file="/script.jsp"%>
<script src="resources/js/ConsultarVenta.js" type="text/javascript"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
                                

