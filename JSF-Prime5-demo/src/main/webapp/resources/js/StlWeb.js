function validaNumero(e, ent, obj, fn) {
	//Se captura la tecla presionada             
	var tecla = (document.all) ? e.keyCode : e.which;
	var patron;
	var te;
	var result;
	
	//Tecla de retroceso (para poder borrar)       
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
				return false;
			}
			return true;
		}
	} else {
		if (fn == '1') {
			if (obj.value.indexOf('.') != -1) {
				obj.value = '';
				obj.focus();
			}
		}
	}
		//Dejar la línea de patron que se necesite y borrar el resto             
	//patron =/[A-Za-z]/; // Solo acepta letras             
	patron = /\d/; // Solo acepta números       //estaba activa      
	//patron = /\w/; // Acepta números y letras             
	//patron = /\D/; // No acepta números             
	//
	te = String.fromCharCode(tecla);
	
	if(isTecla(tecla)){
		return true;
	}
	
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
};