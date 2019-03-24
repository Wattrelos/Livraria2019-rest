var listaitens = document.getElementById("galeria");
var galeria = "";
var url = "http://localhost:8080/livro/";

$.getJSON(url, function (result){
	console.log(result);
	
	result.forEach(function(field, index){
		// console.log(field);
		var autores = [];
		field.autores.forEach(function(autor, indice){
			autores.push(autor.autor);
		});
		
		galeria += `
			<div class="galeria">
			    <a target="_blank" href="#">
			        <img src="img\\${field.imagem}" alt="Capa do livro" width="300" height="400">
			    </a>
			    <h4>${field.titulo}</h4>
			    <a class="btn btn-success btn-sm ml-3" href="#">
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
		/*
		
		<a class="btn btn-success btn-sm ml-3" href="livraria/carrinho">
	        <i class="fa fa-shopping-cart"></i> Carrinho
	        <span class="badge badge-light">0</span>
	    </a>
    */
		
	});
	// console.log(galeria);
	listaitens.innerHTML = galeria;
});