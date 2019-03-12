var formlistaCategorias = document.getElementById("listarcategorias");
var pagecategoria = `Lista de categorias2: <br>`;
var _text = `<div class="listagem">`;
var getData = $.getJSON("http://localhost:8080/categorias", function(result){
	
	_text += `<div class="listar">
		<label>Categoria</label><label>Data de cadastro</label><label></label><label></label>`;
	result.forEach(function(field, index){		
		_text += `<div>${field.nome}</div>
		<div>${field.dataCadastro.slice(0, 10)}</div>		
		<div>
			<form method="update" enctype="application/json" action="categorias/${field.id}">		
				<button type="submit">editar</button>
			</form>
		</div>
		<div>
			<form method="delete" enctype="application/json" action="categorias/${field.id}">		
				<button type="submit">excluir</button>
			</form>
		</div>	
		`;
	});
	_text += `</div>`;
	formlistaCategorias.innerHTML = _text;	
});

