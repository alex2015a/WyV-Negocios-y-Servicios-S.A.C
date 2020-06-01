    (function(){
"use strict";


document.addEventListener('DOMContentLoaded', function ()
{
    
    var actionCliente = document.getElementById("eCliente");
    var btnEliminarCliente= document.getElementById('btnEliClie');
    btnEliminarCliente.addEventListener('click', eliminarCliente);
    
    
     function eliminarCliente(event)
    {
        
        event.preventDefault();
          
        
        console.log("Eliminar");
                    
    }
    
    
    
    });// DOM CONTENT LOADED
})();

