var formList = document.getElementById("list");
var url = "http://localhost:8080/livro/page";
var taglabel1 = "livro";
var taglabel2 = "Livro";
var _text = ``;
var getData = $.getJSON(url, function(result){	
	console.log(result);
	result.content.forEach(function(field, index){
		_text += `
		<form method="post" enctype="application/json" class="listbook" action="${taglabel2}">`;
			console.log(field);
			// Nome das colunas
			if(index == 0){
				$.each(field,function(column, value){
					_text += `<label>${column}</label>`;
				});
				// Duas colunas a mais para os botões.
				_text += `<label></label><label></label>`;
			}			
			
			// dados das linhas
			$.each(field,function(column, value2){
				if(column == "dataCadastro"){
					_text += `<div>${field.dataCadastro.slice(0,10)}</div>`;
				}else{
					_text += `<div>${value2}</div>`;
				}
			});			
			_text += 		
			 `<div>
				<button type="submit"  value="update">editar</button>				
			 </div>
			 <div>
				<button type="submit" onclick="confirmDelete('${field.nome}')" value="delete">Excluir</button>	
			 </div>
		</form>`;
	});	
	_text += `
	<form method="post" enctype="application/json"  action="${taglabel2}">               		
		<input type="text"   name="nome" class="form-data" placeholder="${taglabel1}">
		<button type="submit" name="id" value="">Inserir ${taglabel2}</button>
	</form>`;	
	formList.innerHTML = _text;	
});
