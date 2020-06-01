<%@page import="wyv.persistencia.Usuario"%>
<%@page import="wyv.presentacion.Salida"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="templaste/cabecera.jsp"%>
<%@ include file="templaste/sidebar.jsp" %>

 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container">
        <div class="row mb-2">
             <div class="col-sm-12">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Pagina Inicio</li>
            </ol>
          </div>
          <div class="col-sm-12 mt-5 ">
              <h3 class="text-center d-block">LISTADO DE USUARIOS</h3>  
               <div class="mt-5 ">      
                <% Salida salLis=(Salida)session.getAttribute("salLis"); %>   
                <table id="datos" class="table w-100">
                                <thead>
                                <tr class="">
                                    <th>DNI</th>
                                    <th>Nombre</th>
                                    <th>Password</th>
                                    <th>Tipo</th>
                                    <th></th>
                                    <th></th>
                                </tr> 
                                </thead>     
                                <tbody>
                                    
                                  <% for(int i=0;i<salLis.getUsuarios().size();i++){ %>
                                  <% Usuario usu=(Usuario)salLis.getUsuarios().get(i); %>
                                
                                <tr>
                                
                                <form action="BuscarUsu" method="GET">  
                                  <td><%=usu.getDni()%></td>
                                  <input type="hidden" id="dniU" name="dniU" value="<%=usu.getDni()%>" />
                                  <td><%=usu.getNom()%></td>
                                  <td><%=usu.getPass()%></td>
                                  <td><%= usu.getTipo()%></td>
                                  <td><button class="fa fa-edit form-control btn-primary"/></td>
                                </form>
                                <html:form action="/EliminarUsuario"  styleId="eliu" method="POST">
                                <html:hidden property="dni"  value="<%=usu.getDni()%>"/>
                                <html:hidden property="nom"  value="0" />         
                                <html:hidden property="pass"  value="0" />
                                <html:hidden property="tipo"  value="0" />
                                <td><html:submit property="eliminar" styleId="eli"  styleClass="form-control btn-danger" value="X" /></td>
                                 </html:form>
                                 </tr>
                               <% } %>
                                     
                                </tbody>
                                    
                                <tfoot>
                                    <th>DNI</th>
                                    <th>Nombre</th>
                                    <th>Password</th>
                                    <th>Tipo</th>
                                    <th></th>
                                    <th></th>
                                </tfoot>
                                
                            </table>
                        </div>
            </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    
    <!-- /.content -->
  </div>



<%@include file="templaste/footer.jsp"%>
<%@ include file="/script.jsp"%>

<script src="js/validacionUsuario.js" type="text/javascript"></script>
