/**
 * Description:
 * Scripts responsáveis pelo controle dos inputs do sistema.
 * 
 * Date: 2017-09-25
 */


function removeLastSpace(str){
	var lastChar = str.charAt(str.length -1);
	
	if (lastChar == ' ') { 
		str = str.substring(0, (str.length -1));
	}
	
	return str;
}

function setInputOnlyNumber() {
	$(document).on('keypress', '.soNumeros', function(e) {
		var $this = $(this);
		var key = (window.event)?event.keyCode:e.which;

		if((key > 47 && key < 58) || (key == 46 && key == 44)) {
			return true;
		} else {
			return (key == 8 || key == 0)?true:false;
		}
	});
}

function setInputMoeda() {
	$('.moeda').priceFormat({
		prefix : '',
		centsSeparator : ',',
		thousandsSeparator : '.'
	});
}

function setInputName(){
	$('.name').keyup(function () { 
	    this.value = this.value.replace(/[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ. ]/g,'');
	});
		
	$('.name').keyup(function () { 
		this.value = this.value.trimLeft();
	});
		
	$('.name').keyup(function (){
		this.value = this.value.replace(/\s{2,}/g, ' ');
	});
	
	$('.name').blur( function(){
		var str = $('.name').val();
		this.value = removeLastSpace(str);
	});
	
}

function setInputLastName(){
	$('.lastName').keyup(function () { 
	    this.value = this.value.replace(/[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ. ]/g,'');
	});
		
	$('.lastName').keyup(function () { 
		this.value = this.value.trimLeft();
	});
		
	$('.lastName').keyup(function (){
		this.value = this.value.replace(/\s{2,}/g, ' ');
	});
	
	$('.lastName').blur( function(){
		var str = $('.lastName').val();
		this.value = removeLastSpace(str);
	});
	
}

function setInputModelo(){
	$('.modelo').keyup(function () { 
	    this.value = this.value.replace(/[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ0-9. ]/g,'');
	});
	
	$('.modelo').keyup(function () { 
		this.value = this.value.trimLeft();
	});
	
	$('.modelo').keyup(function (){
		this.value = this.value.replace(/\s{2,}/g, ' ');
	});	
	
	$('.modelo').blur( function(){
		var str = $('.modelo').val();
		this.value = removeLastSpace(str);
	});
	
}

$(document).ready(function() {
	setInputMoeda();
	setInputOnlyNumber();
	setInputName();
	setInputLastName();
	setInputModelo();
});


