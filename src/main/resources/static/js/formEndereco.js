var endereco = new Object();
document.getElementById("cep").addEventListener("change", function(evento){	
	var cep = this.value.replace(/[^\d]+/g,'');
	console.log(cep);
	if(cep.length == 8){	
		$.ajax({				
			type : "GET",
			headers: {"Authorization": window.sessionStorage.getItem('token')},
			url : "http://localhost:8080/logradouro/" + cep,
			// dataType: 'json', // tipo de dados da requisição.
			contentType: "application/json; charset=utf-8",
			success: function (result) {
				// console.log(result);
				document.getElementById("tipoLogradouro").value = result.tipoLogradouro;
				document.getElementById("logradouro").value = result.logradouro;
				document.getElementById("bairro").value = result.bairro.bairro;
				document.getElementById("cidade").value = result.bairro.cidade.cidade;
				document.getElementById("estado").value = result.bairro.cidade.estado.estado;
				endereco["logradouro"] = result;
	        },
	        error: function (erro, textStatus, xhr) {
	        	document.getElementById("logradouro").value = "CEP não encontrado!";
	        }
		});
	}
});
// Gravar endereco
document.getElementById("formulario-endereco").addEventListener("submit", function(evento){
	
	evento.preventDefault() // Evita que submit faça sair da página.
	endereco["numero"] = document.getElementById("numero").value;
	endereco["complemento"] = document.getElementById("complemento").value;
	var cliente = new Object();
	cliente["id"] = window.sessionStorage.getItem('number');
	endereco["cliente"] = cliente;
	
	
	console.log(JSON.stringify(endereco));
	// Gravar endereço cadastrado
	$.ajax({				
		type : "POST",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "http://localhost:8080/endereco",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(endereco),		
		success: function () {			
			window.location.href = "/endereco/endereco?cliente=" + window.sessionStorage.getItem('number');
        },
        error: function (erro, textStatus, xhr) {
        	alert("Erro: " + erro.status + textStatus.value + "Falha ao tentar gravar endereço!");
        }
	});
	
});
function enderecoEntrega(endereco){
	localStorage.setItem("endereco", JSON.stringify(endereco));
	window.location.href = "/pagamento/pagamento?cliente=" + window.sessionStorage.getItem('number');
	
}

