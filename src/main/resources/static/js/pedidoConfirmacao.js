var carrinho;
var idQuantidadeCesta = document.getElementById("quantidadeCarrinho");
var precoTotalHtml = document.getElementById("precoTotal");
var subtotalHtml = document.getElementById("subtotal");
var freteHtml = document.getElementById("frete");
var quantidadeCesta = 0;
var subtotal = 0;
var total = 0;
var frete = 55;
if(localStorage.getItem("carrinho")){
	carrinho = JSON.parse(localStorage.getItem("carrinho"));
	pedidoConfirmacao(carrinho);
	enderecoConfirmacao();
	formaPagamento();
}
function pedidoConfirmacao(carrinho){	
	var listaitens = document.getElementById("listaitens");
	console.log(carrinho);
	// Limpa a lista de produtos:
	listaitens.innerHTML = "";	
	carrinho.forEach(function(item, indice){
		var url = "http://localhost:8080/livro/" + item.id;
		$.getJSON(url, function (result){
			subtotal += result.custo * item.quantidade;
			total = (subtotal + frete).toFixed(2)
			var _itens = `
			<tr>				
				<td>${result.titulo}</td>
				<td>Em estoque</td>
				<td>${item.quantidade}</td>
				<td class="text-right">R$ ${result.custo.toFixed(2)}</td>
				<td class="text-right">R$ ${(result.custo * item.quantidade).toFixed(2)}</td>				
			</tr>			
			`;						
			listaitens.innerHTML += _itens;			
			subtotalHtml.innerHTML = subtotal.toFixed(2);
			freteHtml.innerHTML = frete.toFixed(2);
			precoTotalHtml.innerHTML = total;
		})
		.done(function() {
			var parcela = "<select id='parcelamento'>";
			for(i=1;i<=5; i++){
				parcela += `<option value="${i}">${i} x ${(total / i).toFixed(2)}</option>`;
			}
			parcela += `</select>`;
			document.getElementById("formaPagamento").innerHTML = parcela;
		});
	});
};
//Endereço de entrega
function enderecoConfirmacao(){
	$.ajax({
		type : "GET",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "http://localhost:8080/endereco/" + window.localStorage.getItem('endereco'),
		// dataType: 'json', // tipo de dados da requisição.
		contentType: "application/json; charset=utf-8",		
		success: function (result) {
			console.log(result);
			document.getElementById("enderecoEntrega").innerHTML =
			`<tr>
				<th><label>Endereço de entrega:</th>
			 </tr>
		     <tr>				 
		        <td>${result.logradouro.tipoLogradouro} ${result.logradouro.logradouro}</td>
		     </tr>
		     <tr><th>Número:</th><td>${result.numero}</td></tr>
		     <tr><th>Complemento:</th><td>${result.complemento}</td></tr>		    
	         <tr><th>Bairro:</th><td>${result.logradouro.bairro.bairro}</td></tr>
	         <tr><th>Cidade:</th><td>${result.logradouro.bairro.cidade.cidade}</td></tr>
	         <tr><th>Estado:</th><td>${result.logradouro.bairro.cidade.estado.estado}</td></tr>
	         <tr><th>CEP</th><td>${result.logradouro.cep}</td></tr>
		     `;
	    },
	    error: function (erro, textStatus, xhr) {
	    	document.getElementById("logradouro").value = "Endereço não encontrado!";
	    }
	});
};

function formaPagamento(){
	
}

document.getElementById("confirmacao").addEventListener("submit", function(evento){
	evento.preventDefault(); // Evita que submit faça sair da página.
	//Transformar itens escolhidos em itens do carrinho, se houver:
	var pedido = new Object();
	var estoque = new Object();
	var estoqueArray = new Array();
	var livro = new Object();
	var cliente = new Object();
	var endereco = new Object();
	var cartoCredito = new Object();
	var pagamentoComCartao = new Object();
	var pagamento = new Object();
	var carrinho = JSON.parse(localStorage.getItem("carrinho"));
	cliente["id"] = sessionStorage.getItem("number");
	endereco["id"] = localStorage.getItem("endereco");
	cartoCredito["id"] = localStorage.getItem("cartao");
	pedido["observacao"] = "preparando";
	pedido["cliente"] = cliente;
	pedido["endereco"] = endereco;
	pagamentoComCartao["numeroDeParcelas"] = document.getElementById("parcelamento").selectedIndex + 1;
	pagamentoComCartao["cartoCredito"] = cartoCredito;	
	pagamento["pagamentoComCartao"] = pagamentoComCartao;
	pedido["pagamento"] = pagamento;
	
	var livroArray = new Array();
	carrinho.forEach((estoque, index)=>{
		var livro = new Object();
		livro["id"] = estoque.id;
		var item = new Object();
		// Estatus ------------------------------------
		var estatusArray = new Object();
		estatusArray["id"] = 11;
		item["estatus"] = estatusArray;
		// Livros
		item["quantidade"] = estoque.quantidade;
		item["preco"] = estoque.preco;
		item["livro"] = livro;
		livroArray.push(item);
	});	
	
	pedido["estoque"] = livroArray;
	console.log(JSON.stringify(pedido));
	
	$.ajax({				
		type : "POST",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "http://localhost:8080/pedido",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(pedido),		
		success: function () {
			localStorage.clear();
			window.location.href = "/index";			
        },
        error: function (erro, textStatus, xhr) {
        	alert("Erro: " + erro.status + textStatus.value + "Falha ao tentar gravar pedido!");
        }
	})
});