<%@page import="wyv.persistencia.Categoria"%>
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

                <div class="col-md-8 offset-2">  


                    <center><h3 class="text-muted">Editar Productos</h3> </center> <br>
                        <% Salida pro = (Salida) session.getAttribute("producto");%>


                    <div class="table-responsive table-centered">   

                        <table class="table table-hover">
                            <html:form action="/EditarProducto" method="POST">
                                <tr>
                                    <td>Nombre</td>
                                    <td>
                                        <html:hidden property="cod" value="<%= String.valueOf(pro.getProducto().getCodPro())%>"/>
                                        <html:text property="nom" styleClass="form-control" value="<%= pro.getProducto().getNombre()%>" />
                                    </td>

                                </tr> 
                                <tr>
                                    <td>Stock</td>
                                    <td>
                                        <html:text property="cantidad" styleClass="form-control" value="<%= String.valueOf(pro.getProducto().getCantidad())%>" />
                                    </td>

                                </tr> 
                                <tr>
                                    <td>Proveedor</td>
                                    <td>
                                        <html:text property="proveedor" styleClass="form-control" value="<%= String.valueOf(pro.getProducto().getProveedor())%>" />
                                    </td>

                                </tr> 
                                <tr>
                                    <td>Precio Compra </td>
                                    <td>
                                        <html:text property="preco" styleClass="form-control" value="<%= String.valueOf(pro.getProducto().getPreciocom())%>" />
                                    </td>

                                </tr> 
                                <tr>
                                    <td>Precio Venta </td>
                                    <td>
                                        <html:text property="preven" styleClass="form-control" value="<%= String.valueOf(pro.getProducto().getPrecioven())%>" />
                                    </td>

                                </tr> 

                                <tr>
                                    <td>Descuento </td>



                                    <td>

                                        <html:text property="descuento" styleClass="form-control" value="<%= String.valueOf(pro.getProducto().getDescuento()) %>" />
                                    </td>

                                </tr> 

                                <tr>
                                    <td>Categoria </td>
                                    <td><% Salida salLisCate=(Salida)session.getAttribute("salLisCate"); %>
                                        <html:select property="codigocat" styleClass="form-control" value="<%=String.valueOf(pro.getProducto().getCodigocat().getCodigo())%>">
                                            <% for(int i=0;i<salLisCate.getCategorias().size();i++){ %>
                                            <% Categoria categoria=(Categoria)salLisCate.getCategorias().get(i); %>
                                            <html:option value="<%= String.valueOf(categoria.getCodigo())%>"><%= String.valueOf(categoria.getNombre())%></html:option>
                                            <% } %>
                                        </html:select>
                                    </td>
                                </tr> 
                                <tr>
                                    <td colspan="3"><html:submit value="Modificar" styleClass="form-control btn-primary" /></td>

                                </tr>

                            </html:form>   
                        </table>     
                    </div>
                </div>
            </div>     
        </div> 
    </section>
</div>

<%@ include file="templaste/footer.jsp" %>