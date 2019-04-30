var cartao = "";
$.ajax({	
	type : "GET",
	headers: {"Authorization": window.sessionStorage.getItem('token')},
	url : "http://localhost:8080/cliente/" + window.sessionStorage.getItem('number'),
	// dataType: 'json', // tipo de dados da requisição.
	contentType: "application/json; charset=utf-8",
	success: function (result) {		
		result.cartaoCredito.forEach(function(field, index){
			console.log(field);
			cartao += `
				<div>
					<button class="btn btn-sm btn-block btn-primary text-uppercase" onclick="cartaoCredito(${field.id})">Pagar com cartão final:</b> ${field.numero.toString().slice(-4)}</button>				    
				</div>`;
		});
	},
	complete: function () {
		document.getElementById('pagamentoCartao').innerHTML = cartao;
	}
});
function cartaoCredito(id){
	localStorage.setItem("cartao", JSON.stringify(id));
	window.location.href = "/pedido/confirmacao?cliente=" + window.sessionStorage.getItem('number');
}

document.getElementById("pagamento-cartao").addEventListener("submit", function(evento){		
	evento.preventDefault() // Evita que submit faça sair da página.	
	var cartaoCredito = new Object();
	cartaoCredito["nome"] = document.getElementById('nome').value;
	cartaoCredito["numero"] = document.getElementById('numero').value;
	cartaoCredito["dataValidade"] = document.getElementById('dataValidade').value;
	cartaoCredito["cvv"] = document.getElementById('cvv').value;
	var cliente = new Object()
	cliente["id"] = window.sessionStorage.getItem('number');
	cartaoCredito["cliente"] = cliente;
	
	console.log(JSON.stringify(cartaoCredito));
	// Gravar forma de pagamento
	
	$.ajax({				
		type : "POST",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "http://localhost:8080/cartaocredito",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(cartaoCredito),		
		success: function (cartao) {			
			window.location.href = "/pagamento/pagamento?cliente=" + window.sessionStorage.getItem('number');			
        },
        error: function (erro, textStatus, xhr) {
        	alert("Erro: " + erro.status + textStatus.value + "Falha ao tentar gravar pagamento!");
        }
	})
	
});