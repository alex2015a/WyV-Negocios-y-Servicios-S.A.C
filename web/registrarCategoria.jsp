
<%@page import="wyv.persistencia.Categoria"%>
<%@page import="wyv.presentacion.Salida"%>
<%@ include file="templaste/cabecera.jsp" %>  
<%@ include file="templaste/sidebar.jsp" %>  
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container">            
            <div class="row">
                  <div class="col-sm-12 mb-5">
                    <ol class="breadcrumb float-sm-right">
                      <li class="breadcrumb-item"><a href="#">Home</a></li>
                      <li class="breadcrumb-item active">Pagina Inicio</li>
                    </ol>
                  </div>
                <div class="col-md-8">                    
                    <h3 align="center"><p class="text-muted">REGISTRAR CATEGORIA</p></h3>               
                    <html:form action="/RegistrarCategoria" method="POST">
                        
                        <div class="form-group">
                            <form:label path="nombre">Nombre:</form:label>
                           <html:text  property="nombre" styleClass="form-control" />
                            <form:errors path="nombre"/>
                        </div>
                        <div class="form-group">
                            <form:label path="descripcion">Descripción</form:label> 
                            <html:textarea  property="descripcion" styleClass="form-control" />
                            <form:errors path="descripcion"/>
                        </div>  
                     <html:submit  property="reg" value="Registrar" styleClass="btn btn-primary" />      
                     <br> 
                     <div style="color: red">
                        <html:messages id="id" >
                            ${id}<br>
                        </html:messages>
                     </div>
                            </html:form>
                </div>
                <div class="col-md-4">                    
                    <h3 align="center"><p class="text-muted">CATEGORIAS</p></h3>  
                    <% Salida salLisCate=(Salida)session.getAttribute("salLisCate"); %>  
                    <div class="table-responsive table-centered">
                        <table class="table table-hover">
                            <tr class="">
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                            </tr>
                              <% for(int i=0;i<salLisCate.getCategorias().size();i++){ %>
                              <% Categoria categoria=(Categoria)salLisCate.getCategorias().get(i); %>
                                <tr class="">
                                    <td><%=categoria.getCodigo()%> </td>
                                    <td><%=categoria.getNombre()%> </td>
                                    <td><%=categoria.getDescripcion()%></td>
                                </tr>
                            <% } %>
                        </table>
                    </div>
                </div>
            </div>     
      </div> 
    </section>
</div>
<%@ include file="templaste/footer.jsp" %>