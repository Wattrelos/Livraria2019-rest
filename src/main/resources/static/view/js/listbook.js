var formList = document.getElementById("list");
var taglabel = document.title.toLowerCase();
var taglabe2 = $("#taglabel2").text();
var taglabe3 = taglabe2.toLowerCase();
var url = "http://localhost:8080/" + taglabe3 + "/page";
var _text = ``;
//-------------------------------------

var getData = $.getJSON("http://localhost:8080/livro/page", function(result){	
	result.content.forEach(function(field, index){
		_text +=	`<form class="listbook" method="update" enctype="application/json" action="categorias/${field.id}">`;
		if(index == 0){
			_text += `
			<label>ISBN</label>
			<label>Título</label>
			<label>Ano</label>
			<label>Dimensão</label>
			<label>Custo</label>
			<label>Quantidade</label>
			<label>Ativo?</label>
			<label>Edição</label>
			<label>Páginas</label>
			<label>Peso</label>
			<label>Sinopse</label>
			<label>Data de cadastro</label>
			<label></label>
			<label></label>`;
		}
		_text +=
		   `<div>${field.isbn}</div>
			<div>${field.livro}</div>
			<div>${field.ano}</div>
			<div>${field.dimensao}</div>
			<div>${field.custo}</div>
			<div>${field.quantidade}</div>
			<div>${field.ativo}</div>
			<div>${field.edicao}</div>
			<div>${field.paginas}</div>
			<div>${field.peso}</div>
			<div>${field.sinopse.slice(0, 20)}...</div>				
			<div>${field.dataCadastro.slice(0, 10)}</div>
			<div><button type="submit">editar</button></div>
			<div><button type="submit">excluir</button></div>
			</form>`;
	});
	
	_text += `</div>
	<form method="post" enctype="application/json"  action="${taglabe3}">               		
		<input type="text"   name="nome" class="form-data" placeholder="${taglabe3}">
		<button type="submit" name="id" value="">Inserir ${taglabe3}</button>
	</form>`;
	
	// Verificar se é apenas uma página e fazer paginação:
	if(result.pageable.paged){
		if(!result.first){
			_text += `<div><button type="submit" name="" value="">◀◀◀</button>`;
		}
		_text += `Página ${result.number + 1}`;
		if(!result.last){
			_text += `<button type="submit" name="" value="">▶▶▶</button></div>`;
		}
	}
	formList.innerHTML = _text;	
});
	
