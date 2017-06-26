$.noConflict();
jQuery(document).ready(function( $ ) {
	$("input[name=rbCliente]").change(function (){
		$("#pnClienteNew").hide();
		$("#pnClienteExist").hide();
		$("#btnSearchCliente").hide();
		if ($("#rbCliente1").prop('checked'))
		{
	           $("#pnClienteNew").show();
	           $("#msgOptional").show();
	    }
	    if ($("#rbCliente2").prop('checked'))
		{
	           $("#pnClienteExist").show();
	           $("#btnSearchCliente").show();
	           $("#msgOptional").hide();
	    }
	    
	});
});