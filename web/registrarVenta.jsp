<%@page import="wyv.negocio.Linea"%>
<%@page import="wyv.negocio.VentaObj"%>
<%@page import="wyv.servicios.ServicioProductoImp"%>
<%@page import="wyv.presentacion.EntradaProducto"%>
<%@page import="java.util.List"%>
<%@page import="wyv.persistencia.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="wyv.persistencia.Producto"%>

<%@ include file="templaste/cabecera.jsp"%>
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
            <form  method="GET" id="form" name="form" class="w-100" style="display: contents">
            <input type="hidden" name="op">
            <input type="hidden" name="usua" value="<%=sal.getDniusu()%>" />
            
            <div class="col-md-8">
                 <h2 class="text-center">Registrar Venta</h2>
                 <div class="table-responsive table-centered" >
                        <%-- <form action="AgregarCarro" method="GET"> --%>
                        <table class="table table-hover">

                            <tr class="">
                                <th>Producto</th> 
                                <th>Nombre</th>
                                <th>Cant.</th>
                                <th>Precio</th>   
                                <th></th>
                            </tr>
                            <tr>
                                <td>
                                    <input  type="hidden" name="codPro" id="codPro"  class="codPro" />
                                    <span class="fa fa-search" data-toggle="modal" data-target="#miventana"></span>
                                </td> 
                                <td>
                                    <input  title="Seleccione un producto, por favor." type="text" required  name="nom" id="nom" disabled class="form-control_nom" />
                                </td> 
                                <td>
                                    <input   title="Ingrese una cantidad, por favor." type="text" name="can" id="can" class="form-control" /></td>
                                <div id="error">
                            
                                </div>
                                <td>
                                    <input  disabled required="true"  type="text" name="preven" id="preven" class="form-control_preven"/>                          
                                </td>
                                
                                <input  type="hidden" class="desc" name="desc" id="desc"  />
                                <td colspan="4" style="text-align: center">
                                    <button class="btn btn-primary" id="agregar" onload="AgregarCarro()" >Agregar</button>
                                </td> 
                            </tr>
                        </table> 
                        <%--  </form> --%>
                    </div>
            </div>
             <% VentaObj venObj = (VentaObj) session.getAttribute("venObj"); %>
                    

            <div class="col-md-4 ">
                <div class="form-group">                                                      
                        <label path="num">Factura Nro:</label>
                        <input path="num" value="<%= venObj.getNum() %>" class="form-control" disabled />

                    </div>
                    <div class="form-group">
                        <label path="cc">Cliente: </label>

                        <span class="fa fa-search" data-toggle="modal" data-target="#miventana2"></span>
                        <input class="form-control_clie" disabled type="text"  name="nomClie" style="width: 100%"/>
                        <input type="hidden" id="cliente" name="clie" id="clie" class="clie"/>        

                    </div> 

                    <div class="form-group">
                        <input name="fecha" value="<%= venObj.getFec() %>" class="form-control" disabled  required="true"/>
                    </div>  
                    
                    <div class="form-group">
                        <label path="fpago">Pago: </label> 
                        <input type="text" name="pago" id="pago" class="form-control"/>
                        <div id="error">
                            
                        </div>
                    </div>  

                    <button type="submit" class="btn btn-primary" id="btnGrabar" onclick="RegistrarVenta()">Registrar</button>   

                </div>
                    
                    <div class="table-responsive table-centered col-md-8" style="margin-top: -130px">   

                        <table class="table table-hover">
                            <tr class="">

                                <th>Nombre</th> 
                                <th>Cant.</th>
                                <th>Precio</th>   
                                <th>Importe</th> 
                                <td>.</td>
                            </tr>
                            <% List ces = (List) venObj.getCesta(); %>
                            <% for (int i = 0; i < ces.size(); i++) { %>
                            <% Linea lin = (Linea) venObj.getCesta().get(i);%>
                            <tr>                                    


                                <td><label class="form-control" size="15"><%= lin.getProObj().getNombre()%></label></td>
                                <td><label class="form-control" size="15"><%= lin.getCan()%></label></td>
                                <td><label class="form-control" size="5"><%= String.format("%.2f", lin.getProObj().getPrecioven())%></label></td>        
                                <td><label class="form-control" size="15"><%= String.format("%.2f", lin.getImporte())%></label></td>

                            <input  type="hidden"  name="cod"  value='<%=lin.getProObj().getCodPro()%>'/>
                            <td><button onclick="QuitarCarro()" class="btn btn-danger fa fa-remove"></button></td>
                            </tr>                                    
                            <% }%>
                        </table>  
                        
                    </div>
                    
                    <div class="col-md-8 derecha">
                                
                                  <div class="form-group">
                                    <label path="cc">Sub Total </label>
                                    <input class="form-control" disabled type="text"  name="subtotal" value="<%= String.format("%.2f", venObj.getSubTot())%>"/>
                                </div>
                                
                                <div class="form-group">
                                    <label path="cc">Descuentos Totales </label>
                                    <input class="form-control" disabled type="text"   value="<%= String.format("%.2f", venObj.getDescTotales())%>"/>
                                </div>

                              
                                <div class="form-group">
                                    <label path="cc">IGV 18% </label>
                                    <input class="form-control" disabled type="text"  name="igv" value="<%= String.format("%.2f", venObj.getValorIgv())%>"/>
                                </div>

                                <div class="form-group">
                                    <label path="cc">Total a pagar </label>
                                    <input class="form-control" disabled type="text" id="total" value="<%= String.format("%.2f", venObj.getTot())%>" />
                                </div>
                    </div> 
                    
                     <%--     Modal Producto                       --%>
            <div class="modal fade" id="miventana" tabindex="-1" role="dialog" aria-labellebdy="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="text-center"><b>Seleccione Producto</b></h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                        </div>
                        <div class="modal-body">
                            <div id="msg"></div>
                            <div class="table-responsive table-centered">
                                <div class="listastb" id="clss">
                                    <% Salida salLisP = (Salida) session.getAttribute("salLisP"); %> 
                                    <table id= "datos" class="table table-hover" >
                                        <thead>
                                            <tr>                                
                                                <th>Nro</th>
                                                <th>Nombre</th>
                                                <th>Stock</th>
                                                <th>Precio V.</th>    
                                                <th>Seleccionar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (int i = 0; i < salLisP.getProductos().size(); i++) { %>
                                            <% Producto producto = (Producto) salLisP.getProductos().get(i);%>
                                            <tr>   
                                                <td><%= producto.getCodPro()%></td>
                                                <td><%= producto.getNombre()%></td>
                                                <td><%= producto.getCantidad()%></td>
                                                <td><%= producto.getPrecioven()%></td>
                                               
                                                <td > <button class='btn btn-info fa fa-plus' type="button" onclick="enviarProd('<%= producto.getPrecioven()%>','<%= producto.getNombre()%>',
                                                    '<%= producto.getCodPro()%>','<%= producto.getDescuento()%>')"></button></td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                        <tfoot>
                                        <th>Nro</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>Stock</th>
                                        <th>Precio V.</th>    
                                        <th>Seleccionar</th>
                                        </tfoot>
                                    </table>

                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
                  
                                        <%--     Buscar Cliente                       --%>

            <div class="modal fade" id="miventana2" tabindex="-2" role="dialog" aria-labellebdy="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">                        
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="text-center"><b>Seleccione Cliente</b></h4>

                        </div>                    
                        <div class="modal-body"> 
                            <div id="msgcli"></div>
                            <div class="table-responsive table-centered">
                                <div class="listastb">
                                    <% Salida salLisC = (Salida) session.getAttribute("salLisClie"); %> 
                                    <table id="data" class="table table-hover">
                                        <thead>
                                            <tr>                                
                                                <th>Codigo</th>
                                                <th>Nombre</th>
                                                <th>Telefono</th>
                                                <th>Saldo</th>                                    
                                                <th>Seleccionar.?</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (int i = 0; i < salLisC.getClientes().size(); i++) {
                                                    Cliente cliente = (Cliente) salLisC.getClientes().get(i);
                                            %>

                                            <tr>   
                                                <td><%= String.valueOf(cliente.getIdcli())%></td>
                                                <td><%= cliente.getDni()%></td>
                                                <td><%= cliente.getNomCli()%></td>
                                                <td><%= cliente.getTeleCli()%></td>
                                                <td><button class='btn btn-info fa fa-plus' type="button" onclick="enviarcliente('<%= cliente.getNomCli()%>','<%=  String.valueOf(cliente.getIdcli())%>')"/></td>
                                            </tr>
                                            <% }%>
                                        </tbody>

                                        <tfoot>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Telefono</th>
                                        <th>Saldo</th>                                    
                                        <th>Seleccionar.?</th>
                                        </tfoot>
                                    </table>   
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
                  
           
                                        
         </form>  
      
        </div>
       </div>
    </section>
   </div>     
        
<%@ include file="templaste/footer.jsp"%>
<%@ include file="/script.jsp"%>
<script src="resources/js/ValidarVenta.js" type="text/javascript"></script>
<script src="resources/js/RegistrarVenta.js" type="text/javascript"></script>
<script src="resources/js/func.js" type="text/javascript"></script>