/**
 * Description:
 * Scripts responsáveis pelo controle dos inputs do sistema.
 * 
 * Date: 2017-09-25
 */

function capitalizeFirstLetters(str){
	//Padronizamos a string recebida deixando todas as lestras em minúsculas.
	str = str.toLowerCase();
	
	//Separamos as palavras em um array, usando como separador um espaço em branco.
	var words = str.split(" ");
	
	//Com a ajuda de um For percorremos o array mudando o a primeira letra de cada palavra.
	for (var i = 0; i < words.length; i++) {
	       words[i] = words[i].charAt(0).toUpperCase() + words[i].substring(1);     
	   }	
	
	//Agora devemos unir todas as palavras novamente com a ajuda do join.
	str = words.join(" ");
	
	return str;
	
}

function removeLastSpace(str){
	var lastChar = str.charAt(str.length -1);
	
	if (lastChar === " ") { 
		str = str.substring(0, (str.length -1));
	}
	
	return str;
}

function setInputOnlyNumber() {
	$(document).on("keypress", ".soNumeros", function(e) {
		var $this = $(this);
		var key = (window.event)?event.keyCode:e.which;

		if((key > 47 && key < 58) || (key === 46 && key === 44)) {
			return true;
		} else {
			return (key == 8 || key == 0)?true:false;
		}
	});
}

function setInputMoeda() {
	$(".moeda").priceFormat({
		prefix : "",
		centsSeparator : ",",
		thousandsSeparator : "."
	});
}

function setInputName(){
	$(".name").keyup(function () { 
	    this.value = this.value.replace(/[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ. ]/g,"");
	});
		
	$(".name").keyup(function () { 
		this.value = this.value.trimLeft();
	});
		
	$(".name").keyup(function (){
		this.value = this.value.replace(/\s{2,}/g, " ");
	});
	
	$(".name").blur( function(){
		var str = $(".name").val();
		this.value = removeLastSpace(str);
	});
	
}

function setInputLastName(){
	$(".lastName").keyup(function () { 
	    this.value = this.value.replace(/[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ. ]/g,"");
	});
		
	$(".lastName").keyup(function () { 
		this.value = this.value.trimLeft();
	});
		
	$(".lastName").keyup(function (){
		this.value = this.value.replace(/\s{2,}/g, ' ');
	});
	
	$(".lastName").blur( function(){
		var str = $(".lastName").val();
		this.value = removeLastSpace(str);
	});
	
}

function setInputModelo(){
	$(".modelo").keyup(function () { 
	    this.value = this.value.replace(/[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ0-9. ]/g,"");
	});
	
	$(".modelo").keyup(function () { 
		this.value = this.value.trimLeft();
	});
	
	$(".modelo").keyup(function (){
		this.value = this.value.replace(/\s{2,}/g, " ");
	});	
	
	$(".modelo").blur( function(){
		var str = $(".modelo").val();
		this.value = removeLastSpace(str);
	});
	
}

function fmtTextToShow(){
	$(document).ready(function(){
		if ($(".modelo")[0]) {
			var modelo = $(".modelo").val();
			modelo = modelo.toUpperCase();
			$(".modelo").val(modelo);
			
			var placa = $(".placa").val();
			placa = placa.toUpperCase();
			$(".placa").val(placa);
		}
		
		if ($(".name")[0]) {
			var lastName = $(".lastName").val();
			lastName = capitalizeFirstLetters(lastName);
			$(".lastName").val(lastName);
			
			var name = $(".name").val();
			name = capitalizeFirstLetters(name);
			$(".name").val(name);
			
		}
		
		
	});
	
}

$(document).ready(function() {
	setInputMoeda();
	setInputOnlyNumber();
	setInputName();
	setInputLastName();
	setInputModelo();
	fmtTextToShow();
});


