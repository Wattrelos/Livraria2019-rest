var listaitens = document.getElementById("galeria");
var quantidadeCarrinho = document.getElementById("quantidadeCarrinho");
var galeria = "";
var url = "http://localhost:8080/livro/";

if(localStorage.getItem("carrinho")){
	quantidadeCarrinho.innerHTML = (JSON.parse(localStorage.getItem("carrinho"))).length;
}

$.getJSON(url, function (result){
	// console.log(result);
	
	result.forEach(function(field, index){
		// console.log(field);
		var autores = [];
		field.autores.forEach(function(autor, indice){
			autores.push(autor.autor);
		});
		
		galeria += `
			<div class="galeria">
			    <a href="#">
			        <img src="img\\${field.imagem}" alt="Capa do livro" width="300" height="400">
			    </a>
			    <h4>${field.titulo.slice(0, 48)}</h4>
			    <a class="btn btn-success btn-sm ml-3" onclick="adicionarAoCarrinho(${field.id})">
			        <i class="fa fa-shopping-cart"></i>Adicionar
			        <span class="badge badge-light">R$ ${field.custo}</span>
			    </a><br>			    
			    <b>Autor(es):</b>${autores}<br>
			    <b>Editora:</b>${field.editora.editora}<br>
			    <b>Edição:</b>${field.edicao}<br>
			    <b>Dimensão:</b>${field.dimensao}<br>
			    <b>ISBN:</b> ${field.isbn}<br>
			    
			</div>
			`;		
		
	});
	// console.log(galeria);
	listaitens.innerHTML = galeria;
});

function adicionarAoCarrinho(livroId){
	var quantidade = 1;
	let carrinho = [];
	var estaNaLista = false;
	//Verifica se já tem o mesmo livro no carrinho 
	if(localStorage.getItem("carrinho")){
		carrinho = JSON.parse(localStorage.getItem("carrinho"));
		carrinho.forEach(function(item, indice){
			console.log(item);
			if(livroId == item.id){
				// se tiver, apenas aumenta a quantidade.
				item.quantidade += 1;
				estaNaLista = true;
			}
		});
	}
	if(!estaNaLista){
		jsonLivro = new Object(); 
		jsonLivro['id'] = livroId;
		jsonLivro['quantidade'] = quantidade;		    	
		carrinho.push(jsonLivro);
	}
	
	localStorage.setItem("carrinho", JSON.stringify(carrinho));
	console.log(carrinho);
	quantidadeCarrinho.innerHTML = carrinho.length;
}

