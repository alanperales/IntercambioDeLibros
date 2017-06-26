/* <![CDATA[ */

function formatoNumero(e) {
	tecla = (document.all) ? e.keyCode : e.which; // 2
    if (tecla==8) return true; // backspace   
    if (tecla==9) return true; // tab   
    if(e.shiftKey & (tecla>=48 && tecla<=57)) { return false;} //No permitir caracteres especiales
    if(e.shiftKey & (tecla>144 && tecla<=222)) { return false;} //No permitir caracteres especiales
    if((tecla>=49 && tecla<=57)) { return true;}//Solo numeros
    if (e.ctrlKey && tecla==86) { return true;} //Ctrl v
    if (e.ctrlKey && tecla==67) { return true;} //Ctrl c
    if (e.ctrlKey && tecla==88) { return true;} //Ctrl x
    if (tecla>=96 && tecla<=105) { return true;} //numpad
    if (tecla>=35 && tecla<=46) { return true;} //home,end,teclas de direccion
    if (e.shiftKey && tecla==35) { return true;} //Shift end
    if (e.shiftKey && tecla==36) { return true;} //Shift home
   
    patron = /[0-9]/; // patron
 
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba
    }

function formatoFechaCalendario(e) {
	tecla = (document.all) ? e.keyCode : e.which; // 2
    if (tecla==8) return false; // backspace
    if (tecla==32) return true; // space
    if (tecla==9) return true; // tab   
    if (e.ctrlKey && tecla==86) { return true}; //Ctrl v
    if (e.ctrlKey && tecla==67) { return true}; //Ctrl c
    if (e.ctrlKey && tecla==88) { return true}; //Ctrl x
    if (tecla>=96 && tecla<=111) { return false;} //numpad
 
    patron = /[a-zA-Z]/; // patron
 
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba
    }

function isNumberOrLetter(e) {
	 var key;
	    if (e.keyCode) key = e.keyCode;
	    else if (e.which) key = e.which;
	    if (/[^A-Za-z0-9 ]/.test(String.fromCharCode(key))) {
	        return false;

	    }else {return true;}
}



function formatoLetra(e) {
	tecla = (document.all) ? e.keyCode : e.which; // 2
    if (tecla==8) return true; // backspace
    if (tecla==32) return true; // space
    if (tecla==9) return true; // tab   
    if (e.ctrlKey && tecla==86) { return true;} //Ctrl v
    if (e.ctrlKey && tecla==67) { return true;} //Ctrl c
    if (e.ctrlKey && tecla==88) { return true;} //Ctrl x
    if (tecla>=96 && tecla<=111) { return false;} //numpad
    if (tecla>=35 && tecla<=46) { return true;}
    if ( tecla==222) { return true;}
    patron = /[a-zA-Z]/; // patron
 
    te = String.fromCharCode(tecla); 
    return patron.test(te); // prueba
  }
function formatoNombresDescripciones(e) {
	 	var key;
	    if (e.keyCode) key = e.keyCode;
	    else if (e.which) key = e.which;
	    if (key == 95  || (key == 8)) return true;
	    if (key>=96 && key<=105) { return true;} //numpad
	    if (/[^A-Za-z0-9 ]/.test(String.fromCharCode(key))) {
	        return false;
	    }else {
	    	return true;
	    }
}

function formatoTexto(e) {
	var key;
	 if (e.keyCode) key = e.keyCode;
	    else if (e.which) key = e.which;
	    if ((key == 95) || (key == 44) || (key == 59) ||(key == 46) || (key == 8) || (key == 190) || (key == 110)) return true;
	    if (/[^A-Za-z0-9 ]/.test(String.fromCharCode(key))) {

	        return false;

	    }else {return true;}
}

function formatoTextoCaracteresEspeciales(e) {
	var key;
	 if (e.keyCode) key = e.keyCode;
	    else if (e.which) key = e.which;
	    if ((key == 95) || (key == 44) || (key == 59) ||(key == 46) || (key == 16)) return true;
	    if (/w/.test(String.fromCharCode(key))) {

	        return false;

	    }else {return true;}
}

function formatoConsultaSql(e) {
	 var key;
	 if (e.keyCode) key = e.keyCode;
	 else if (e.which) key = e.which;
	 if ((key == 95) || (key == 42) || (key == 44) || (key == 59) ||(key == 46)) return true;
	 if (/[^A-Za-z0-9 ]/.test(String.fromCharCode(key))) {
		 return false;
	 }else {
		 return true;
	 }
}
function formatoRuta(e) {
	 var key;
	 if (e.keyCode) key = e.keyCode;
	 else if (e.which) key = e.which;
	 if ((key == 95) ||  (key == 58) || (key == 42) || (key == 44) || (key == 59) ||(key == 46)||(key == 47)||(key == 92)) return true;
	 if (/[^A-Za-z0-9 ]/.test(String.fromCharCode(key))) {
		 return false;
	 }else {
		 return true;
	 }
}
function formatoJndi(e) {
	 var key;
	 if (e.keyCode) key = e.keyCode;
	 else if (e.which) key = e.which;
	 if ((key == 47)||(key == 92)) return true;
	 if (/[^A-Za-z0-9 ]/.test(String.fromCharCode(key))) {
		 return false;
	    }else {
	    	return true;
	    }
}

function validarFechas(objetoHtml){
	  fecha = objetoHtml.val();
	  var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
   if ((fecha.match(RegExPattern)) && (fecha!='')) {
   
 	var dia = fecha.substring(0,2);
	  	var mes = fecha.substring(3,5);
	  	var ano = fecha.substring(6,10);
	  	var maxDias = 31;
	  	if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
	  		maxDias = 30;
	  	if (mes == 2){
	  		if (ano % 4 == 0)
	  				maxDias = 29;
	  			else
	  				maxDias = 28;
	  	}
	  	if (mes>12 || mes<1 || dia>31 || dia<1 || ano<1980>2200 ||  dia>maxDias) {
	  	objetoHtml.val(null);
	  	}
 	  
   } else {
 	
 	  objetoHtml.val(null);
        
   }	
	
}
function readComponents() {
	/*
    var coms = "";
    var path = document.URL;
    path = path.split("BBVAPCT");
    $("*").each(function(index, row){
        if($(row).attr("id") != undefined && $(row).attr("id").length > 0 && ($(row).prop("tagName").toLowerCase()=='button' || $(row).prop("tagName").toLowerCase()=='a' || $(row).prop("tagName").toLowerCase()=='fieldset')) {
            coms += path[1] + "|" + $(row).prop("tagName") + "|" + $(row).attr("id") +  "|" + $.trim($(row).text()).substring(0, 25) + "\n";
        }
    });
    
    var ctrl = $(".panelComponents");
    if(ctrl.length > 0) {
        ctrl.html("<textarea>" + coms + "</textarea>");
    } else {
        $("body").append("<br><br><br><br><div class='panelComponents' style=\"margin-left: 20px;\"><textarea>" + coms + "</textarea></div>");
    }
    //*/
function verObjeto( objeto){
		var ob =  objeto;
		alert(objeto);
		
	}
}

function actualizarArchivo(){
	var parentBody = window.parent.document.body;
	 $(PrimeFaces.escapeClientId('formularioRegistroReferido:tabs:prueba'), parentBody).click();
}

function actualizarArchivoReferidoAdicional(){
	var parentBody = window.parent.document.body;
	$(PrimeFaces.escapeClientId('formRegistroReferidoAdicional:tabs:prueba'), parentBody).click();
}

function actualizarArchivo2(){
	var parentBody = window.parent.document.body;
	 $(PrimeFaces.escapeClientId('formularioAgenda:prueba'), parentBody).click();
}

function actualizarArchivoAgenda(){
	var parentBody = window.parent.document.body;
	 $(PrimeFaces.escapeClientId('formularioAgenda:prueba'), parentBody).click();
}

function actualizarArchivoFichaCliente(){
	var parentBody = window.parent.document.body;
	 $(PrimeFaces.escapeClientId('formularioFichaClientes:prueba'), parentBody).click();
}

function actualizarArchivoFichaProfAdicional(){
	var parentBody = window.parent.document.body;
	 $(PrimeFaces.escapeClientId('formularioFichaClientes:idButtonFTidButtonFT'), parentBody).click();
}

/* esto es javascript */
function validarFechaHora(objetoHtml){
	fecha = objetoHtml.val();
	re=/^[0-3][0-9]\/[0-1][0-9]\/[0-9][0-9][0-9][0-9] 0[1-9]|1\d|2[0-3]:[0-5]\d$/;
if(!re.exec(fecha) ){
 //alert("La fecha no tiene formato AAAA-MM-DD HH:MM:SS");
	//var fechaFinal = objetoHtml.val().substring(0, 10);
	objetoHtml.focus();
	 //objetoHtml.val(null);
}
else{
 //alert("Fecha en formato correcto");
}
}

function validarDiferenciaFechaHora(){
	var controlFechaIncicio = jQuery("[id$='from']");
	var controlFechaFin = jQuery("[id$='final']");
	var fechaInicio = jQuery("[id$='from']").val();
	var fechaFin = jQuery("[id$='final']").val();
	var horaInicio = fechaInicio.substring(11,13);
	var minutoInicio = fechaInicio.substring(14,16);
	var horaFin = fechaFin.substring(11,13);
	var minutoFin = fechaFin.substring(14,16);
	var controlBotonGuardar= $(PrimeFaces.escapeClientId('formularioAgenda:addButton'));	
	//var exito = true;
	
	if(horaInicio=="00"){
		//alert("La hora de inicio debe ser mayor a cero");
		//exito = false;
		controlFechaIncicio.focus();
		controlBotonGuardar.attr('disabled',true);
		controlBotonGuardar.addClass('ui-state-disable');
		controlBotonGuardar.addClass('ui-button-disable');
		return;
		
	}
	if(horaFin=="00"){
		//alert("La hora fin debe ser mayor a cero");
		//exito = false;
		controlFechaFin.focus();
		controlBotonGuardar.attr('disabled',true);
		controlBotonGuardar.addClass('ui-state-disable');
		controlBotonGuardar.addClass('ui-button-disable');
		controlBotonGuardar.removeClass('ui-state-default');
		return;
	}
	
	if(parseInt(horaInicio)>23 || parseInt(horaInicio)<0
			|| parseInt(minutoInicio)<0 || parseInt(minutoInicio)>59){
		//alert("Por favor ingrese una hora correcta para la fecha inicial");
		exito = false;
		controlFechaIncicio.focus();
		controlBotonGuardar.attr('disabled',true);
		controlBotonGuardar.addClass('ui-state-disable');
		
		controlBotonGuardar.removeClass('ui-state-default');
		return;
	}
	if(parseInt(horaFin)>23 || parseInt(horaFin)<0
			|| parseInt(minutoFin)<0 || parseInt(minutoFin)>59){
		//alert("Por favor ingrese una hora correcta para la fecha fin");
		exito = false;
		controlFechaFin.focus();
		controlBotonGuardar.attr('disabled',true);
		controlBotonGuardar.addClass('ui-state-disable');
		
		controlBotonGuardar.removeClass('ui-state-default');

		return;
	}
	controlBotonGuardar.attr('disabled',false);
	controlBotonGuardar.removeClass('ui-state-disable');
	
	controlBotonGuardar.addClass('ui-state-default');


}
/*
$(document).ready(function(){
	$(".formatoLetra").autoText({caracteres: " abcdefghijklmnopqrstuvwxyz\u00ED\u00FA\u00F3\u00E9\u00E1\u00F1\u0008"});
	$(".formatoLetraNumero").autoText({caracteres: " 0123456789abcdefghijklmnopqrstuvwxyz\u00ED\u00FA\u00F3\u00E9\u00E1\u00F1\u0008"});
	$(".formatoNumero").autoText({caracteres: "0123456789"});
	readComponents();
});*/


/* ]]> */