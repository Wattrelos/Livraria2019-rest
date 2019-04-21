

// Gravar endereco
document.getElementById("pagamento-cartao").addEventListener("submit", function(evento){
	var pagamento = new Object();	
	evento.preventDefault() // Evita que submit faça sair da página.
	var pagamentoComCartao = new Object();
	var cartaoCredito = new Object();
	cartaoCredito["nome"] = document.getElementById('nome').value;
	cartaoCredito["numero"] = document.getElementById('numero').value;
	cartaoCredito["dataValidade"] = document.getElementById('dataValidade').value;
	cartaoCredito["cvv"] = document.getElementById('cvv').value;
	pagamentoComCartao["cartaoCredito"] = cartaoCredito;
	pagamento["pagamentoComCartao"] = pagamentoComCartao;
	window.sessionStorage.getItem('number')	
	console.log(JSON.stringify(pagamento));
	// Gravar forma de pagamento
	
	$.ajax({				
		type : "POST",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "http://localhost:8080/pagamento",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(pagamento),		
		success: function (cartao) {			
			window.location.href = "/pedido/confirmacao?cliente=" + window.sessionStorage.getItem('number');			
        },
        error: function (erro, textStatus, xhr) {
        	alert("Erro: " + erro.status + textStatus.value + "Falha ao tentar gravar pagamento!");
        }
	})
});

