<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 3 | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="resources/css/all.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="resources/css/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="resources/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
  <!-- sweetalert -->
  
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../../index2.html"><b>W&V</b>S.A.C</a>
  </div>
  <!-- /.login-logo -->
  <div class="card">
    <div class="card-body login-card-body">
      <p class="login-box-msg">Inicie Sesión</p>

       <html:form action="/Acceso" styleId="acc" method="POST">
        <div class="input-group mb-3">
          
          <html:text property="dni" styleId="dni" styleClass="form-control"   />
          <div class="input-group-append">
            <div class="input-group-text">
             <i class="fa fa-envelope"></i>
            </div>
          </div>
        </div>
          
           <html:hidden property="nom" value="0"/>
           <html:hidden property="tipo" value="0"/>

        <div class="input-group mb-3">
          
           <html:password property="pass" styleId="pass" styleClass="form-control"  />
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fa fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">
                Remember Me
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-12 my-4">
            
             <html:submit property="ini" styleId="btnreg" styleClass="btn btn-primary btn-block" 
                                                                             value="Iniciar Sesion">
										
                                                                </html:submit>
          </div>
          <!-- /.col -->
        </div>
        

      <p class="mb-1">
        <a href="forgot-password.html">Olvide mi contraseña</a>
      </p>
      <p class="mb-0">
        <a href="register.html" class="text-center">Registrarme</a>
      </p>
       </html:form>        
    </div>
    <!-- /.login-card-body -->
     <div style="color: red; position: relative; left: 4em">
                                         
                                         <% String msg = (String) session.getAttribute("errorLogin"); %>
                                         
                                         <p id="error_login"><%= msg %></p>
                                                                       
                                    </div>
  </div>
</div>
<!-- /.login-box -->

<!--mi script-->
<script src="js/main.js" type="text/javascript"></script>

<!-- jQuery -->
<script src="resources/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/js/adminlte.min.js"></script>

</body>
</html>
