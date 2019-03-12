var formlistaCategorias = document.getElementById("listarcategorias");
var pagecategoria = `Lista de categorias2: <br>`;
var _text = `<div class="listagem">`;
var getData = $.getJSON("http://localhost:8080/categorias", function(result){
	
	_text += `<form method="post" action="categoria" class="listar">
		<label>Categoria</label><label>Data de cadastro</label><label></label><label></label>`;
	result.forEach(function(field, index){		
		_text += `<div>${field.nome}</div>
		<div>${field.dataCadastro.slice(0, 10)}</div>
		<div><button type="submit" name="UPDATE" value="field.id">editar</button></div>
		<div><button type="submit" name="DELETE" value="field.id">excluir</button></div>
		`;
	});
	_text += `</form>
	<div><button type="submit" name="CREATE" value="field.id">Inserir</button></div>
	`;
	
	formlistaCategorias.innerHTML = _text;	
});

