var listaitens = document.getElementById("listaitens");
var idQuantidadeCesta = document.getElementById("quantidadeCarrinho");
var quantidadeCesta = 0;
var itens = "";
var url = "http://localhost:8080/pedido/1";

$.getJSON(url, function (result){
	
	result.itemPedido.forEach(function(field, index){
		console.log(field);
		quantidadeCesta += field.quantidadeItens;
		this.itens += `
			<tr>
				<td><img src="/img/${field.livro.imagem}" alt="Capa do livro" width="60" height="80"></td>
				<td>${field.livro.titulo}</td>
				<td>Em estoque</td>
				<td><input class="form-control" type="number" value="${field.quantidadeItens}" /></td>
				<td class="text-right">${field.livro.custo}</td>
				<td class="text-right">${field.livro.custo * field.quantidadeItens}</td>
				<td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
			</tr>
			`;
		
	});	
	listaitens.innerHTML = itens;
	idQuantidadeCesta.innerHTML = `${quantidadeCesta}`;
});
   