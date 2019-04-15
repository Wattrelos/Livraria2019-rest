var endereço = new Object();
document.getElementById("cep").addEventListener("change", function(evento){	
	var cep = this.value.replace(/[^\d]+/g,'');
	console.log(cep);
	if(cep.length == 8){	
		$.ajax({				
			type : "GET",
			headers: {"Authorization": window.sessionStorage.getItem('token')},
			url : "http://localhost:8080/cep/" + cep,
			// dataType: 'json', // tipo de dados da requisição.
			contentType: "application/json; charset=utf-8",
			success: function (result) {
				// console.log(result);
				document.getElementById("logradouro").value = result.enderecoCompleto;
				document.getElementById("bairro").value = result.bairro.bairro;
				document.getElementById("cidade").value = result.bairro.cidade.cidade;
				document.getElementById("estado").value = result.bairro.cidade.tendEstado.estado;
				endereço = result;
	        },
	        error: function (erro, textStatus, xhr) {
	        	alert("Erro: "+ erro.status + textStatus + "Falha ao tentar buscar CEP!");
	        }
		});
	}
});
// Gravar endereço
document.getElementById("formulario-endereco").addEventListener("submit", function(evento){
	evento.preventDefault() // Evita que submit faça sair da página.
	endereço["numero"] = document.getElementById("numero").value;
	endereço["complemento"] = document.getElementById("complemento").value;
	console.log(endereço);
});

