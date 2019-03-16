
	
var formList = document.getElementById("list");
var taglabe2 = $("#taglabel2").text();
var page = 0;
var taglabe3 = taglabe2.toLowerCase();
console.log(taglabe3 + " " + page);
//-------------------------------------
var _text = `<div class="list"><label>${taglabe2}</label><label>Data de cadastro</label><label></label><label></label>`;
let url = "http://localhost:8080/" + taglabe3 + "/page?page=" + page;
pagination(0,taglabe3);	
		
function pagination (page, taglabe3){
	var _text = `<div class="list"><label>${taglabe2}</label><label>Data de cadastro</label><label></label><label></label>`;
	let url = "http://localhost:8080/" + taglabe3 + "/page?page=" + page;
	$.getJSON(url, function (result){		
		result.content.forEach(function(field, index){
			_text += `<div>${field.nome}</div>
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
		<form method="post" enctype="application/json"  action="${taglabe3}">               		
			<input type="text"   name="nome" class="form-data" placeholder="${taglabe3}">
			<button type="submit" name="id" value="">Inserir ${taglabe3}</button>
		</form>`;
		
		// Verificar se é apenas uma página e fazer paginação:
		if(result.pageable.paged){
			if(!result.first){
				_text += `<div><button onclick="pagination(${result.number - 1}, taglabe3)" type="button" name="" value="">◀◀◀</button>`;
			}
			_text += `Página ${result.number + 1}`;
			if(!result.last){
				_text += `<button onclick="pagination(${result.number + 1}, taglabe3)" type="button">▶▶▶</button></div>`;
			}
		}
		formList.innerHTML = _text;	
	});	
};

		

