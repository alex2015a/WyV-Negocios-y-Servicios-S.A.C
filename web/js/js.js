/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function modal(ruta,controlador)
{   var modal=ruta+"/"+controlador;  
    var xmlhttp;
    // en esta condicional Doble se valida la Version del Navegador
    if(window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();// creando un de Ajax Moderno
    }else
    {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");   
    }
    xmlhttp.onreadystatechange=function()
   {
       if(xmlhttp.readyState==4 && xmlhttp.status==200)
       {
   document.getElementById('contenido').innerHTML=xmlhttp.responseText;        
       }
   }
               xmlhttp.open("GET",modal,true);
               xmlhttp.send(null);
}