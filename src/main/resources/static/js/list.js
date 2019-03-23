
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
			<label>${taglabe2}</label><label>Data de cadastro</label><label></label><label></label>`;
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
	var taglabe2 = $("#taglabel2").text();
	var page = 0;	
	pagination(page, taglabe2);


		

