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
                    <center><h3 class="text-muted">Editar Categoria</h3> </center> <br>
                        <% Salida cate=(Salida)session.getAttribute("categoria"); %>
                    <div class="table-responsive table-centered">   
                        <html:form action="/EditarCategoria" method="POST">
                            <table class="table table-hover">
                                <tr>
                                    <td>Nombre</td>
                                    <td>
                                        <html:hidden property="codigo" value="<%=String.valueOf(cate.getCategoria().getCodigo())%>"/>
                                        <html:text property="nombre" styleClass="form-control" value="<%= cate.getCategoria().getNombre() %>" />
                                    </td>
                                <tr> 
                                <tr>
                                    <td>Descripcion</td>
                                    <td>
                                        <html:textarea property="descripcion" styleClass="form-control" value="<%= cate.getCategoria().getDescripcion()%>"></html:textarea>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td colspan="3"><html:submit value="Editar" styleClass="form-control btn-primary" /></td>                                  
                                </tr>
                                <br>         
                                <div style="color: red">
                                    <html:messages id="id" >
                                        ${id}<br>
                                    </html:messages>
                                </div>
                            </html:form>
                        </table>  
                    </div>
                </div>
            </div>     
        </div> 
    </section>
</div>

<%@ include file="templaste/footer.jsp" %>