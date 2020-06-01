
<%@page import="wyv.persistencia.Cliente"%>
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
                    <h3 align="center"><p class="text-muted">REGISTRAR CLIENTE</p></h3>                
                    
                     <html:form action="/RegistrarCliente" method="POST">       
 
                        <div class="form-group">
                            <form:label path="nom">Nombre:</form:label>
                           <html:text  property="nom" styleClass="form-control" />
                            <form:errors path="nom"/>
                        </div>
                        <div class="form-group">
                            <form:label path="dni">Dni</form:label> 
                            <html:text  property="dni" styleClass="form-control" />
                            <form:errors path="dni"/>
                        </div>
                        <div class="form-group">
                            <form:label path="telf">Telefono:</form:label> 
                            <html:text  property="telf" styleClass="form-control" />
                            <form:errors path="telf"/>
                        </div>
                        <div class="form-group">
                            <form:label path="direcc">Dirección:</form:label> 
                            <html:text  property="direcc" styleClass="form-control" />
                            <form:errors path="direcc"/>
                        </div>
                        <div class="form-group">
                            <form:label path="email">Email</form:label> 
                            <html:text  property="email" styleClass="form-control" />
                            <form:errors path="email"/>
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
                    <h3 align="center"><p class="text-muted">CLIENTES</p></h3>  
                    <% Salida salLisClie=(Salida)session.getAttribute("salLisClie"); %>  
                    <div class="table-responsive table-centered">
                        <table class="table table-hover">
                            <tr class="">
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Dni</th>
                            </tr>
                              <% for(int i=0;i<salLisClie.getClientes().size();i++){ %>
                              <% Cliente cliente=(Cliente)salLisClie.getClientes().get(i); %>
                                <tr class="">
                                    <td><%=cliente.getIdcli()%> </td>
                                    <td><%=cliente.getNomCli()%> </td>
                                    <td><%=cliente.getDni()%></td>
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