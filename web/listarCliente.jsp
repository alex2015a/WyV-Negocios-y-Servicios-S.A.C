<%@page import="wyv.persistencia.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <% Salida salLisClie=(Salida)session.getAttribute("salLisClie"); %>  
                <div class="col-md-12">
                    <h3 class="text-center d-block mb-5">LISTADO DE USUARIOS</h3>
                    <div id="tbl">
                        <div class="table-responsive table-centered">                        
                            <table id="datos" class="table table-hover">
                                <thead>
                                <tr class="">
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Dni</th> 
                                    <th>Telefono</th>
                                    <th>Dirección</th>
                                    <th>Email</th>
                                    <th></th>
                                    <th></th>
                                </tr> 
                                </thead>
                                <tbody>
                                 <% for(int i=0;i<salLisClie.getClientes().size();i++){ %>
                                <% Cliente cliente=(Cliente)salLisClie.getClientes().get(i); %>
                                <tr>     
                                    <form action="BuscaClie" method="GET">
                                    <td><%=String.valueOf(cliente.getIdcli())%></td>
                                    <input type="hidden" id="id" name="id" value="<%= String.valueOf(cliente.getIdcli()) %>"/> 
                                    <td><%=String.valueOf(cliente.getNomCli())%></td>
                                    <td><%=String.valueOf(cliente.getDni())%></td>
                                    <td><%=String.valueOf(cliente.getTeleCli())%></td> 
                                    <td><%=String.valueOf(cliente.getDireCli()) %></td>
                                    <td><%=String.valueOf(cliente.getEmail())%></td>
                                    <td><button class="fa fa-edit form-control btn-primary"/></td>
                                    </form>
                                    <html:form action="/EliminarCliente" styleId="eCliente" method="POST">
                                   
                                    <html:hidden property="id" value="<%= String.valueOf(cliente.getIdcli()) %>"/> 
                                    <html:hidden property="nom" value="<%=String.valueOf(cliente.getNomCli())%>"/>
                                    <html:hidden property="telf"  value="<%=String.valueOf(cliente.getTeleCli())%>"/>
                                    <html:hidden property="direcc"  value="<%=String.valueOf(cliente.getDireCli()) %>"/>
                                    <html:hidden property="email"  value="<%=String.valueOf(cliente.getEmail())%>"/>
                                    <html:hidden property="dni" value="<%=String.valueOf(cliente.getDni())%>" />
                                     <td><html:submit property="eliminar" styleId="btnEliClie" styleClass="form-control btn btn-danger" value="X" /></td>
                                </html:form>
                                    </tr>
                                <% } %>
                                </tbody>
                                <tfoot>
                                <tr class="">
                                   <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Dni</th> 
                                    <th>Telefono</th>
                                    <th>Dirección</th>
                                    <th>Email</th>
                                    <th></th>
                                    <th></th>
                                </tr> 
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
<script src="js/validarcionCliente.js" type="text/javascript"></script>
