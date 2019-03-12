$('form.formulario').on('submit',function(formEvent){
	formEvent.preventDefault(); //removendo ação submit;
	// var categoria = { name: "Literatura3"};
	var categoria = {}
	categoria["name"] = $("#nome").val();
	console.log(JSON.stringify(categoria));
	$.ajax({
		method: "POST",
		url: "categorias",
		dataType: "json",
		contentType: "application/json", //Headers da pagina que é feita a requisição        
		data: JSON.stringify(categoria)
           
	})
	.done(function() {
		alert("Criando nova categoria");
	})
	.fail(function() {
		alert( "Erro ao tentar criar categoria" );
	})
	.always(function() {
		alert( "Finalizado" );
	})
});




