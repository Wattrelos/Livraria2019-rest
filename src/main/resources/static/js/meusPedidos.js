var pedidosHTML = document.getElementById("listaPedidos");
var pedidos = "";
$.ajax({				
	type : "GET",
	headers: {"Authorization": window.sessionStorage.getItem('token')},
	url : "/cliente/" + window.sessionStorage.getItem('number'),
	contentType: "application/json; charset=utf-8",
	success: function (result) {
		result.pedido.forEach((field, index)=>{
			pedidos += `<tr>
							<td><a href="/pedido/meuPedido?pedido=${field.id}">${field.id}</a></td>							
							<td>${field.observacao}</td>
							<td>${field.dataCadastro.slice(0, 10)} - ${field.dataCadastro.slice(11, 16)}</td>
						</tr>`;
			pedidosHTML.innerHTML = pedidos;
		});		
    },
    error: function (erro) {        	
    	alert("Erro: " + erro.status + " Falha ao tentar adicionar livro!" + name);
    }
});