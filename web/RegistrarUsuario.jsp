<%@page import="wyv.persistencia.Usuario"%>
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
                <div class="col-sm-8">                    
                    <h3 align="center"><p class="text-muted">REGISTRAR EMPLEADO</p></h3>  
                    
                    <% Salida salLis=(Salida)session.getAttribute("salLis");%>
                    
                    <html:form action="/RegistrarUsuario" method="POST">

                        <div class="form-group">
                            <label for="name" >Dni</label>
                            <html:text  property="dni"   styleClass="form-control"  />
                        </div>

                        <div class="form-group">
                            <label for="name">Nombre</label>
                            <html:text property="nom"  styleClass="form-control"  />
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <html:text property="pass"  styleClass="form-control"  />
                        </div>

                        <div class="form-group">
                            <label for="tipo">Tipo</label>
                            <html:select property="tipo"  styleClass="form-control" >
                            <html:option value="0">-----------Seleccione----------</html:option>
                            <html:option value="1">Administrador</html:option>
                            <html:option value="2">Vendedor</html:option>
                            </html:select>
                        </div>
                        <html:submit property="reg"  styleClass="btn btn-primary"  value="Registrar"/>
                        <br> 
                        <div style="color: red" >
                        <html:messages id="dni" >
                            ${dni}<br>
                        </html:messages>
                        </div>

                       

                    </html:form>
                </div>
                <div class="col-md-4">                    
                    <h3 align="center"><p class="text-muted">Usuarios</p></h3>
                    
                   
                    
                    <div class="table-responsive table-centered">
                        <table class="table table-hover">
                            <tr>
                                <th>DNI</th>
                                <th>Nombre</th>
                                <th>Cargo</th>
                            </tr>
                         <% for(int i=0;i<salLis.getUsuarios().size();i++){ %>
                         <% Usuario usu=(Usuario)salLis.getUsuarios().get(i); %>
                                    <tr class="">
                                        
                                        <td><%= usu.getDni()%></td>
                                        <td><%= usu.getNom()%></td>
                                        <td ><%= usu.getTipo()%></td>
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
<script src="js/main.js" type="text/javascript"></script>


