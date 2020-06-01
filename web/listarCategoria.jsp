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
                    
                <div class="col-md-12">
                    <h3 class="text-center text-uppercase">LISTADO DE CATEGORIAS</h3> 
                       
                          
                              <% Salida salLisCate=(Salida)session.getAttribute("salLisCate"); %> 

                    <div id="tbl">
                        <div class="table-responsive table-centered">
                            <table id="datos" class="table table-hover">
                                <thead>
                                   <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Descripcion</th>
                                    <th></th>
                                    <th></th>
                                </thead>
                                <tbody>
                                <% for(int i=0;i<salLisCate.getCategorias().size();i++){ %>
                                <% Categoria categoria=(Categoria)salLisCate.getCategorias().get(i); %>
                            <tr>
                                <form action="BuscarCategoria" method="POST">
                                    <td><%= String.valueOf(categoria.getCodigo())%></td>
                                    <input  type="hidden" name="codigo"  value="<%= String.valueOf(categoria.getCodigo())%>"/> 
                                    <td><%= String.valueOf(categoria.getNombre())%></td>
                                    <td><%= String.valueOf(categoria.getDescripcion())%></td>
                                    <td><button class="fa fa-edit form-control btn-primary"/></td>
                                </form>
                                    
                                <html:form action="/EliminarCategoria" method="POST">
                                    <td><html:submit property="eliminar" styleClass="form-control btn-danger" value="X" /></td>
                                    <html:hidden property="codigo"  value="<%= String.valueOf(categoria.getCodigo())%>"/> 
                                    <html:hidden property="nombre"  value="<%= String.valueOf(categoria.getNombre())%>"/>
                                    <html:hidden property="descripcion"  value="<%= String.valueOf(categoria.getDescripcion())%>"/>
                                </html:form> 
                            </tr>
                                 <% } %>
                                </tbody>
                                <tfoot>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Descripcion</th>
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