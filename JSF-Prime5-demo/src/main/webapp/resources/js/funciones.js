var lLetras=' ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyzáéûRúÁÉÍÓÚ';
var lLetrasSinEspacio='ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyzáéûRúÁÉÍÓÚ';
var lExtraCorreo='@_-.';
var lExtraTelefono='#*';
var lExtraDireccion = '.#\'';
var lExtraBusqueda = '*-';
var lExtraNombre = '\'-&';
var lNumeros='1234567890';
var lExtraDecimales='-.'
var lSigns=',.:;@-\'';
var lMathsigns='+-=()*/';
var lCustom='<>#$%&?¿';
var lMantenimientoSituacion='<>%';

function validaNumero(e, ent, obj, fn) {

	// Se captura la tecla presionada
	var tecla = (document.all) ? e.keyCode : e.which;
	var patron;
	var te;
	var result;
	// Tecla de retroceso (para poder borrar)
	if (tecla == 8) {
		return true;
	}
	if (ent == '0') {
		if (fn == '1') {
			if (obj.value == '') {
				return true;
			}
			var puntos = obj.value.split('.');
			if (puntos.length > 2) {
				obj.value = '';
				obj.focus();
			}
		}
		if (tecla == 46) {
			if (obj.value.indexOf('.') != -1) {
				return true;
			}
			return false;
		}
	} else {
		if (fn == '1') {
			if (obj.value.indexOf('.') != -1) {
				obj.value = '';
				obj.focus();
			}
		}
	}
	// Dejar la linea de patron que se necesite y borrar el resto
	// patron =/[A-Za-z]/; // Solo acepta letras
	patron = /\d/; // Solo acepta numeros
	// patron = /\w/; // Acepta numeros y letras
	// patron = /\D/; // No acepta numeros
	//
	te = String.fromCharCode(tecla);
	if (fn == '1') {
		te = obj.value;
	}
	result = patron.test(te);
	if (result == false) {
		if (fn == '1') {
			if (obj.value == '') {
				return true;
			}
			obj.value = '';
			obj.focus();
		}
	}
	return result;
}

function NumCheck(e, field) {
	  key = e.keyCode ? e.keyCode : e.which;
	  // backspace
	  if (key == 8) return true;
	  // 0-9				  
	  
	  if ((key > 47) && (key < 58)) {
	    if (field.value == "") return true;
	    regexp = /.[0-9]{2}$/;
	    return !(regexp.test(field.value));
	  }
	  // .
	  if (key == 46) {
	    if (field.value == "") return false;
	    regexp = /^[0-9]+$/;
	    return regexp.test(field.value);
	  }
	  // other key
	  return false;
	
}		

function dataTableSelectOneRadio(radio) {
	var id = radio.name.substring(radio.name.lastIndexOf(':'));
	var elements = radio.form.elements;
	for ( var i = 0; i < elements.length; i++) {
		if (elements[i].name.substring(elements[i].name.lastIndexOf(':')) == id) {
			elements[i].checked = false;
		}
	}
	radio.checked = true;
}

function seleccionarDataTable(valor, nombreGroup,nombreForm) {
	if(valor != null ){
	var elements = document.forms[nombreForm].elements;
	for ( var i = 0; i < elements.length; i++) {
		if (elements[i].name.substring(elements[i].name.lastIndexOf(':')+1) == nombreGroup) {
			elements[i].checked = false;
			if(elements[i].value == valor){
				elements[i].checked=true;
			}
		}
	}
}
}

function NumCheck(e, field) { 
	key = e.keyCode ? e.keyCode : e.which; // backspace 
	if (key == 8){
		return true; // 0-9 
	}
	if (key > 47 && key < 58) { 
		if (field.value == ""){
			return true; 
		}
		regexp = /\.[0-9]{2}$/ ;
		return !(regexp.test(field.value)); 
	} // . 
	if (key == 46) { 
		if (field.value == "") {
			return false ;
		}
		regexp = /^[0-9]+$/ ;
		return regexp.test(field.value); 
	} // other key 
	return false;
}

function isNumberKey(e){
	var key;
	var valid = '' + lNumeros + lExtraDecimales;
	
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == "-1")
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == "-1")
			e.keyCode = 0;
	}
	
	/** Codigo Edinson Nunez**/
	
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
 
	return true;
}

function isNumberKeyExt(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57) && (charCode != 188) && (charCode != 190))
      return false;

   return true;
}

function hora(){
	var fecha = new Date();
	var hora = fecha.getHours();
	var minuto = fecha.getMinutes();
	var segundo = fecha.getSeconds();
	if (hora < 10) {hora = "0" + hora;}
	if (minuto < 10) {minuto = "0" + minuto;}
	if (segundo < 10) {segundo = "0" + segundo;}
	var horita = hora + ":" + minuto + ":" + segundo;
	document.getElementById('hora').firstChild.nodeValue = horita;
	//tiempo = setTimeout('hora()',1000);
}
function inicio(){
	document.write('<span id="hora" style="color:black">');
	document.write ('000000</span>');
	hora();
}

function CatchNonNumbers_(e, negative, decimals, decimalSeparator, txt, cantdec, cantent, obj){	
    if (e.keyCode == 8 || e.keyCode == 46 || e.keyCode == 37 || e.keyCode == 39) {
        return true;
    }
    tecla = e.keyCode? e.keyCode : e.charCode;
	var sKey = String.fromCharCode(tecla);
	if((sKey == '|') || (sKey == '�E½') || (sKey == '`') || (sKey == '~') ||(sKey == '"') || (sKey == '&') || (sKey == ' ') || (sKey == '�E½') || (sKey == '!') || (sKey == '�E½') || (sKey == '#') || (sKey == '$') || (sKey == '%') || (sKey == '/') || (sKey == '(') || (sKey == ')') || (sKey == '=') || (sKey == '?') || (sKey == '�E½') || (sKey == '@') || (sKey == '*') || (sKey == '+') || (sKey == '[') || (sKey == ']') || (sKey == '{') || (sKey == '}') || (sKey == ';') || (sKey == ',') || (sKey == ':') || (sKey == '_') )
	{	
	 return false;
	}
	return CatchNonNumbers(e, negative, decimals, decimalSeparator, txt, cantdec, cantent);
}

function CatchNonNumbers(e, negative, decimals, decimalSeparator, txt, cantdec, cantent){
	var uni = e.keyCode? e.keyCode : e.charCode; // get the keyCode
	var decUni = getDecimalSeparatorCode(decimalSeparator); // get the decimal separator keyCode	
	var evt = window.event? event : e; // event for crtl, alt and shift key
	if(evt.ctrlKey || evt.altKey || evt.shiftKey) 
		return true;
	else 
	{
	    var ubidec = txt.value.indexOf(decimalSeparator);
	    var ubientxt = getSelectionStart(txt);
	    ubidec +=1;
        if (cantdec <1)
	    {
    	    decimals=false;
	    }
	    if (ubidec > 0 ) //Existe signo Decimal
	    {
	        if (ubientxt >= ubidec) //Ubicado en los decimales
	        {
	            if ((txt.value.length - ubidec) == cantdec) 
	            {	        
        	        return false;
	            }
	        }
	        else //ubicado en los enteros
	        {
	            if ( ubientxt <= ( ubidec - 1 ) )
	            {
	                if ((ubidec-1) >= cantent) 
	                {	        
    	                return false;
	                }
	            }
	        }
	    }
	    else
	    {
	        if ( ubidec == 0 )
	        {
	            if ( (txt.value.length >= cantent) && ( uni != decUni ) )
	            {	        
    	            return false;
	            }
	        }
	        else
	        {
	            if ((ubidec-1) >= cantent) 
	            {	        
    	            return false;
	            }
	        }
	    }
	    switch(uni)
	    {
	        case 0:
	            // safari
	            return true;
	        case decUni:
	            if(txt.value.replace("-", "").length > 0 && ((getCursorPosition(txt) > 0 ) || (getCursorPosition(txt) > 1 && txt.value.indexOf("-") > -1)) && txt.value.indexOf(decimalSeparator) == -1 && decimals)
	                return true;
	            else
	                return false;
	            break;
	        case 45: // minus sign
	        	if(negative=='0'){
	        		return false;
	        	}
	            if(getCursorPosition(txt) == 0 && negative)
	                return true;
	            else
	                return false;
	            break;
	        default:
				return IsAllowed(uni);
				break;
	    }
	} 
}


function getSelectionStart(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}

function getDecimalSeparatorCode(separator)
{
    if(separator == ".")
        return 46;
    else
        return 44;
}


function IsAllowed(s) // Which characters are allowed
{ 
	var arr = new Array(8, 9, 13, 17, 35, 36, 37, 38, 
	    39, 40, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
	for(i = 0; i < arr.length; i++)
	{
	    if(arr[i] == s)
	        return true;
	}
	return false;
}

function GetURLParameter(sParam){
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++){
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam){
            return sParameterName[1];
        }
    }
}

function submitRegistroSolicitud(){
    if(window.event.keyCode=='13'){
    	document.getElementById('frmRegSolicitud:btnBuscar').click();
    }
}
function checkMaxLength(length, maxLength){return length<=maxLength;}

function tamMax(obj,t) {
    n = obj.value.length;     
    if (n > t)
        obj.value = obj.value.substring(0, t);
    /*else
       obj.value = t - n;*/
}