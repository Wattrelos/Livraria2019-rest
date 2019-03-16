function confirmDelete(nome,id){	
	
	var message;
	var url = "http://localhost:8080/" + nome + "/delete";
	console.log(url);
	if (confirm("Ter cerceza que quer exluir '" + nome + "?")){
		$.post(url, { "nome" : nome })
		.done(function() {
		    window.location.replace("http://localhost:8080/" + nome);
		}).fail(function() {
			message = "Erro ao tentar excluir " + nome;
		});
		message = nome + " excluído com sucesso!";
	} else {		
		message = "Operação cancelada!";		
	}
	alert(message)
};
