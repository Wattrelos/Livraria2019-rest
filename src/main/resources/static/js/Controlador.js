class Controlador {
	constructor(){
		this._local = 'pt-BR';		
        this.initialize();
	}
	initialize(){
		var formlistaCategorias = document.getElementById("listarcategorias");
		var pagecategoria = `Lista de categorias2: <br>`;
		var _text = `<div class="listagem">`;
		var getData = $.getJSON("http://localhost:8080/categorias", function(result){
			
			
			result.forEach(function(field, index){
				formlistaCategorias.appen
				this._text += '<form method="post" action="categoria">';
				this._text += "<div>Categoria: " + field.categoria + "</div>";
				this._text += "<div>Cadastro: " + field.dataCadastro + "</div>";
				this._text += "</form>";
				
			});	
			this._text += "</div>jjjjjjjjjjjjjjjjjjjjjjj";
			formlistaCategorias.innerHTML = _text;	
		});

	}
};