// Inicialização da página e suas variáveis
var listaitens = document.getElementById("listaitens");
var idQuantidadeCesta = document.getElementById("quantidadeCarrinho");
var precoTotalHtml = document.getElementById("precoTotal");
var subtotalHtml = document.getElementById("subtotal");
var freteHtml = document.getElementById("frete");
var quantidadeCesta = 0;
var subtotal = 0;
var frete = 55;
// Fim declaração ------------------------------

var carrinho = new Object();

if(localStorage.getItem("carrinho")){
	carrinho = JSON.parse(localStorage.getItem("carrinho"));
	atualizaCarrinho();
}

// Ler os id dos livros escolhidos armazenados no LocalStorage

function atualizaCarrinho(){

	// console.log(carrinho);
	
	
	// Limpa a lista de produtos:
	listaitens.innerHTML = "";	
	carrinho.forEach(function(item, indice){
	//console.log(item);	
	var url = "http://localhost:8080/livro/" + item.id;
	$.getJSON(url, function (result){
		subtotal += result.custo * item.quantidade;
		var _itens = `
			<tr>
				<td><img src="/img/${result.imagem}" alt="Capa do livro" width="60" height="80"></td>
				<td>${result.titulo}</td>
				<td>Em estoque</td>
				<td><input class="form-control" type="number" value="${item.quantidade}" /></td>
				<td class="text-right">R$ ${result.custo}</td>
				<td class="text-right">R$ ${result.custo * item.quantidade}</td>
				<td class="text-right">
					<a class="btn btn-danger btn-sm ml-3" onclick="exluirItem(${result.id})">
						<i class="fa fa-trash">Excluir</i>
					</a>
				</td>
			</tr>
			`;			
			subtotalHtml.innerHTML = subtotal.toFixed(2);
			freteHtml.innerHTML = frete.toFixed(2);
			precoTotalHtml.innerHTML = (subtotal + frete).toFixed(2);
			listaitens.innerHTML += _itens;
		});
	});
};

function exluirItem(id){ // Excluir um item do carrinho
	carrinho = JSON.parse(localStorage.getItem("carrinho"));
	carrinho.forEach(function(item, indice){
		if(id == item.id){
			carrinho.splice(indice,1);
			// delete carrinho[indice]; //Exclui item do carrinho
		}
	});
	//Atualiza o LocalStorage
	localStorage.setItem("carrinho", JSON.stringify(carrinho));
	// Atualiza a página do carrinho de compras
	atualizaCarrinho();
}
function confirmPedido(){
	var carrinhoArray = new Array(); // Array para guardar livros no carrinho
	var pedidoObject = new Object(); // Array para armazenar o pedido
	//Transformar itens escolhidos em itens do carrinho, se houver:
	if(localStorage.getItem("carrinho")){		
		var carrinho = new Object();
		carrinho = JSON.parse(localStorage.getItem("carrinho"));
		// console.log(carrinho);
		carrinho.forEach(function(item, indice){			
			var url = "http://localhost:8080/livro/" + item.id;			
			$.getJSON(url, function (result){
				
				result.quantidade = item.quantidade;
				carrinhoArray.push(result);				
			});
		});		
		
		pedidoObject["pedido"]=carrinhoArray;
		// console.log(JSON.stringify(carrinhoArray));
		console.log(JSON.stringify(pedidoObject));
		// Fim da transição. ----------------------------------------
		
		
	    $.ajax({				
			type : "POST",
			headers: {"Authorization": window.sessionStorage.getItem('token')},
			url : "/carrinho",
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(JSON.stringify(carrinho)),		
			success: function () {
				alert("Carrinho adicionado com sucesso!");
	        },
	        error: function (erro, textStatus, xhr) {
	        	alert("Erro: "+ erro.status + erro.responseText + " Falha ao tentar adicionar carrinho!");
	        }	        
		});	
	}
}