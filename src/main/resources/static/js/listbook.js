
	
var formList = document.getElementById("list");
var taglabe2 = $("#taglabel2").text();
var page = 0;
var taglabe3 = taglabe2.toLowerCase();
console.log(taglabe3 + " " + page);
//-------------------------------------
let url = "http://localhost:8080/" + taglabe3 + "/page?page=" + page;
pagination(0,taglabe3);	
		
function pagination (page, taglabe3){
	
	let url = "http://localhost:8080/" + taglabe3 + "/page?page=" + page;
	// Listar dados-----------------------------------------
	$.getJSON(url, function (result){
		let _text = ``;
		// Verificar se é apenas uma página e fazer paginação:
		if(result.pageable.paged){
			if(!result.first){
				_text = `<div><button onclick="pagination(${result.number - 1}, taglabe3)" type="button" name="" value="">◀◀◀</button>`;
			}
			_text += `Página ${result.number + 1}`;
			if(!result.last){
				_text += `<button onclick="pagination(${result.number + 1}, taglabe3)" type="button">▶▶▶</button></div>`;
			}
		}
		// Nome das colunas
		_text +=
`<div class="listbook">
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
		// Linhas de dados
		result.content.forEach(function(field, index){
			_text += `<div>${field.isbn}</div>
			<div>${field.titulo}</div>
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
			<div>
				<form method="post" enctype="application/json" action="${taglabe2}/update=${field.id}">		
					<button type="submit">editar</button>
				</form>
			</div>
			<div>
				<form method="post" enctype="application/json" action="${taglabe3}/delete=${field.id}">
					<button type="submit" onclick="confirmDelete('${field.nome}')" value="delete">Excluir</button>				
				</form>
			</div>	
			`;
		});
		
		_text += `</div>
		<form method="get" enctype="application/json"  action="/gestao/${taglabe3}">
			<button type="submit" name="action" value="insert">Inserir ${taglabe3}</button>
		</form>`;
		formList.innerHTML = _text;	
	});	
};

		

