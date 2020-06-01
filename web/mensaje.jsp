<%@page import="wyv.presentacion.Salida"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <a href="resources/css/bootstrap.css.map"></a>
        <title>JSP Page</title>
    </head>
    
    <body>
    <center>
    <% Salida salida=(Salida)session.getAttribute("mensaje");%>
        <h1>Mensaje!</h1>
        <h2><%= salida.getMsg() %></h2>
        <a href="registrarVenta.jsp" class="btn btn-primary">Aceptar</a>
        
        </center>
    </body>
</html>
