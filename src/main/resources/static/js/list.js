
var pagination = function (page, taglabe2){
	var taglabe3 = taglabe2.toLowerCase();
	var url = "http://localhost:8080/" + taglabe3 + "/page?page=" + page;
		// Listar dados-----------------------------------------
		$.getJSON(url, function (result){
			var formList = document.getElementById("list");
			var _text = ``;
			// Verificar se é apenas uma página e fazer paginação:
			if(result.pageable.paged){
				if(!result.first){
					_text = `<div><button onclick="pagination(${result.number - 1}, '${taglabe3}')" type="button" name="" value="">◀◀◀</button>`;
				}
				this._text += `Página ${result.number + 1}`;
				if(!result.last){
					_text += `<button onclick="pagination(${result.number + 1}, '${taglabe3}')" type="button">▶▶▶</button></div>`;
				}
			}
			// Nome das colunas
			_text += `<div class="form-each">			
			<label>${taglabe2}</label><label>Cadastro</label><label></label><label></label>`;
			result.content.forEach(function(field, index){
				_text +=
			   `<div>${field.nome}</div>
				<div>${field.dataCadastro.slice(0, 10)}</div>
				<div><button type="submit" onclick="confirmUpdate('${taglabe3}','${field.id}','${field.nome}')">Editar</button></div>
				<div><button type="submit" onclick="confirmDelete('${taglabe3}','${field.id}','${field.nome}')">Excluir</button></div>`;
			});
			_text += `</div>
			<form method="post" enctype="application/json" action="/${taglabe3}" onsubmit="confirmCreate(this); return false;">
				<input type="text" id="nome" name="nome" class="form-data" placeholder="${taglabe3}">
				<button type="submit" name="entity" value="${taglabe3}">Inserir ${taglabe3}</button>
			</form>`;
			formList.innerHTML = _text;	
		});
	};
	
	
	function paginationLivros (page, taglabe3){
		var formList = document.getElementById("list");
		
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
				<div>${field.titulo.slice(0, 50)}</div>
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
					<form method="post" enctype="application/json" action="${taglabe3}/update=${field.id}">		
						<button type="submit">editar</button>
					</form>
				</div>
				<div>
					<form method="post" enctype="application/json" action="${taglabe3}/delete=${field.id}">
						<button type="submit" onclick="confirmDelete('${taglabe3}',${field.id},'${field.titulo}')" value="delete">Excluir</button>				
					</form>
				</div>	
				`;
			});
			
			_text += `</div>
			<form method="get" enctype="application/json"  action="/gestao/${taglabe3}">
				<button type="submit" name="action" value="inserir">Inserir ${taglabe3}</button>
			</form>`;
			formList.innerHTML = _text;	
		});	
	};


		

