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
				<td class="text-right">R$ ${result.custo.toFixed(2)}</td>
				<td class="text-right">R$ ${(result.custo * item.quantidade).toFixed(2)}</td>
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

document.getElementById("carrinho").addEventListener("submit", function(evento){
	evento.preventDefault() // Evita que submit faça sair da página.
	//Transformar itens escolhidos em itens do carrinho, se houver:
	var estoque = new Object();
	var carrinho = JSON.parse(localStorage.getItem("carrinho"));
	var cliente = sessionStorage.getItem("number");
	if(cliente == null){
		document.getElementById('id01').style.display='block';
	}else if(carrinho != null){
		estoque["estoque"] = carrinho;
		localStorage.setItem("estoque", JSON.stringify(estoque));
		console.log(JSON.stringify(estoque));
		window.location.href = "/endereco/endereco?cliente=" + window.sessionStorage.getItem('number');
	}
});