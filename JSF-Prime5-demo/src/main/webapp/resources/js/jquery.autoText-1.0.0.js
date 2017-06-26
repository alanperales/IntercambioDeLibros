(function($) {
	$.fn.autoText = function(options) {
		var opts = $.extend({}, $.fn.autoText.defaults, options); /* build main options before element iteration */
		return this.each(function() {/* iterate and reformat each matched element */
			var iv = $(this);/* check input value iv */
			var ii = this.id;/* input ID */
			var io = opts;
			$(this).keydown(function(e){/* start keyDown event */
				
			}).bind("keypress", function(e){
	  			var key = e.which,
					keye = e.keyCode,
					tecla = String.fromCharCode(key).toLowerCase(),
					letras = io.caracteres;
			    if(letras.indexOf(tecla)==-1 && keye!=9&& (key==37 || keye!=37)&& (keye!=39 || key==39) && keye!=8 && (keye!=46 || key==46) || key==161){
			    	e.preventDefault();
			    }
			}).bind("keyup", function(e){
				
			}).bind('change focusout', function(){
				if(autoId(ii) === "#") {
					oInput = $(this);
				} else {
					oInput = $(autoId(ii));
				}
				
				if ($.trim(oInput.val()).length > 0){
					autoCheck(iv, ii, io);
				}
			}).bind('paste', function(){
				setTimeout(function(){autoCheck(iv, ii, io);}, 0); 
			});
		});
		
		function autoId(myid) {/* thanks to Anthony & Evan C */
			myid = myid.replace(/\[/g, "\\[").replace(/\]/g, "\\]"); 
			return '#' + myid.replace(/(:|\.)/g,'\\$1');
		}
		
		function autoCheck(iv, ii, io){/*  private function that change event and pasted values  */
			oInput = iv;
			var rePaste = '',
				testPaste = iv.val(),
				s = testPaste.split('');/* split the sting into an array */
			
			for (var i=0; i<s.length; i++){/* for loop testing pasted value after non allowable characters have been deleted */
				if (io.caracteres.indexOf(s[i].toLowerCase()) == -1){/* allows negative symbol to be added if it is the first character */
					continue;
				} else {
					rePaste = rePaste + s[i];
				}
			}
						
			if(autoId(ii) === "#") {
				oInput.val($.trim(rePaste));
			} else {
				$(autoId(ii)).val(rePaste);
			}
			
			return false;
		}
	};
	$.fn.autoText.defaults = {
		caracteres: " 0123456789abcdefghijklmnopqrstuvwxyz\u00ED\u00FA\u00F3\u00E9\u00E1\u00F1"
	}; 
	
})(jQuery);