var carrinho;
var idQuantidadeCesta = document.getElementById("quantidadeCarrinho");
var precoTotalHtml = document.getElementById("precoTotal");
var subtotalHtml = document.getElementById("subtotal");
var freteHtml = document.getElementById("frete");
var quantidadeCesta = 0;
var subtotal = 0;
var frete = 55;
if(localStorage.getItem("carrinho")){
	carrinho = JSON.parse(localStorage.getItem("carrinho"));
	pedidoConfirmacao(carrinho);
	enderecoConfirmacao();
}
function pedidoConfirmacao(carrinho){	
	var listaitens = document.getElementById("listaitens");
	console.log(carrinho);
	// Limpa a lista de produtos:
	listaitens.innerHTML = "";	
	carrinho.forEach(function(item, indice){
		var url = "http://localhost:8080/livro/" + item.id;
		// console.log(item);		
		$.getJSON(url, function (result){
			subtotal += result.custo * item.quantidade;
			var _itens = `
			<tr>				
				<td>${result.titulo}</td>
				<td>Em estoque</td>
				<td>${item.quantidade}</td>
				<td class="text-right">R$ ${result.custo}</td>
				<td class="text-right">R$ ${result.custo * item.quantidade}</td>				
			</tr>			
			`;						
			listaitens.innerHTML += _itens;
			
			subtotalHtml.innerHTML = subtotal.toFixed(2);
			freteHtml.innerHTML = frete.toFixed(2);
			precoTotalHtml.innerHTML = (subtotal + frete).toFixed(2);
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