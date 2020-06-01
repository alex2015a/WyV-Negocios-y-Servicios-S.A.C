<%@page import="wyv.persistencia.Producto"%>
<%@page import="wyv.presentacion.Salida"%>

<%@ include file="templaste/cabecera.jsp"%>
<%@ include file="templaste/sidebar.jsp" %>

<div class="content-wrapper">
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
                <div class="col-md-12">
                    <h3 class="text-center d-block mb-5">LISTADO DE PRODUCTOS</h3>
                    <% Salida salLisP=(Salida)session.getAttribute("salLisP"); %> 
                    <div id="tbl">
                        <div class="table-responsive table-centered">
                            <table id="datos" class="table table-hover">
                                <thead>
                                    <tr class="">
                                        <th>Código</th>
                                        <th>Nombre</th>
                                        <th>Cantidad</th>
                                        <th>Proveedor</th>
                                        <th>Precio V.</th>
                                        <th>Precio C.</th>
                                        <th>Descuento</th>
                                        <th>Categoria</th>
                                        <th></th>
                                        <th></th>
                                    </tr> 
                                </thead>
                                <tbody>
                                    <% for(int i=0;i<salLisP.getProductos().size();i++){ %>
                                    <% Producto producto=(Producto)salLisP.getProductos().get(i); %>

                                    <tr>
                                <form action="BuscarProducto" method="POST">
                                    <td><%= String.valueOf(producto.getCodPro())%></td>
                                    <input  type="hidden" name="cod"  value="<%= String.valueOf(producto.getCodPro())%>"/> 
                                    <td><%= String.valueOf(producto.getNombre())%></td>
                                    <td><%= String.valueOf(producto.getCantidad())%></td>
                                    <td><%= String.valueOf(producto.getProveedor())%></td>
                                    <td><%= String.valueOf(producto.getPreciocom())%></td>
                                    <td><%= String.valueOf(producto.getPrecioven())%></td>
                                    <td><%= String.valueOf(producto.getDescuento())%></td>
                                    <td><%= String.valueOf(producto.getCodigocat().getNombre())%></td>
                                    <td><button class="fa fa-edit form-control btn-primary"/></td>
                                </form>
                                <html:form action="/EliminarProducto" method="POST">
                                    <td><html:submit property="eliminar" styleClass="form-control btn-danger" value="X" /></td>
                                    <html:hidden property="cod"  value="<%= String.valueOf(producto.getCodPro())%>"/> 
                                    <html:hidden property="nom"  value="<%= String.valueOf(producto.getNombre())%>"/>
                                    <html:hidden property="cantidad"  value="<%= String.valueOf(producto.getCantidad())%>"/>
                                    <html:hidden property="proveedor"  value="<%= String.valueOf(producto.getProveedor())%>"/>
                                    <html:hidden property="preco"  value="<%= String.valueOf(producto.getPreciocom())%>"/>
                                    <html:hidden property="preven"  value="<%= String.valueOf(producto.getPrecioven())%>"/>
                                    <html:hidden property="descuento"  value="<%= String.valueOf(producto.getDescuento())%>"/>
                                    <html:hidden property="codigocat"  value="<%= String.valueOf(producto.getCodigocat())%>"/>
                                </html:form> 
                                </tr>

                                <% } %>
                                </tbody>
                                <tfoot>
                                <th>Código</th>
                                <th>Nombre</th>
                                <th>Cantidad</th>
                                <th>Proveedor</th>
                                <th>Precio V.</th>
                                <th>Precio C.</th>
                                <th>Descuento</th>
                                <th>Categoria</th>
                                <th></th>
                                <th></th>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>     
        </div> 
    </section>
</div>
<%@include file="templaste/footer.jsp"%>
<%@ include file="/script.jsp"%>
