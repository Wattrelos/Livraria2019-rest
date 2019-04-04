function confirmCreate(frm){
	
	let campos =  frm.querySelectorAll("input");
	var EntidadeArray = new Object();
	campos.forEach((field, index)=>{
		EntidadeArray[`${field.name}`] = field.value;
		
	});	
	// console.log(JSON.stringify(EntidadeArray));
    $.ajax({				
		type : "POST",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "/" + frm.entity.value,
		dataType: 'json', // tipo de dados da requisição.
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(EntidadeArray),		
		success: function () {
			alert( frm.nome.value + " adicionado com sucesso!");
        },
        error: function (erro, textStatus, xhr) {
        	console.log(erro);        	
        	alert("Erro: "+ erro.status + status + "Falha ao tentar adicionar "+ frm.nome.value + "!");
        },
        complete: function () {
        	$(document).ready(function(){
				pagination(0 , frm.entity.value);
			});
        }
	});
    
};
// Delete =====================================================================================
function confirmDelete(entity, id, name){
	if (confirm("Ter cerceza que quer exluir '" + name + "' em " + entity + " ?")){
		$.ajax({				
			type : "DELETE",
			headers: {"Authorization": window.sessionStorage.getItem('token')},
			url : "/" + entity,
			data : {id : id},
			success: function () {
				alert(name + " excluído com sucesso!");
				$(document).ready(function(){
					pagination(0 , entity);
				});
	        },
	        error: function (erro) {
	        	alert("Falha ao tentar excluir " + name);
	        }
		});
	}
};
function confirmUpdate(entity, id, name){
	if (confirm("Ter cerceza que quer alterar '" + name + "' em " + entity + " ?")){
		$.ajax({				
			type : "PUT",
			headers: {"Authorization": window.sessionStorage.getItem('token')},
			url : "/" + entity,
			data : {id : id},
			success: function () {
				alert(name +  "alterado com sucesso!");
				$(document).ready(function(){
					pagination(0 , entity);
				});
	        },
	        error: function (erro) {
	        	alert("Falha ao tentar alterar!" + name);
	        }
		});
	}
};
//=============================== Cadastrar livro via AJAX======================================================
function confirmCreateLivro(frm){
	let campos =  frm.querySelectorAll("input");
	// let textarea = frm.querySelector("textarea").value;
	
	// console.log(document.querySelector("textarea").value);
	// document.querySelector("textarea").value = "Your text";
	// console.log(textarea);
	var LivroArray = new Object();
	campos.forEach((field, index)=>{
		
		switch(field.getAttribute("type")) {
		  case "text": 
			  	LivroArray[`${field.name}`] = field.value;
			  break;
		  case "number":			 
			    LivroArray[`${field.name}`] = field.value;
			  break;
		  case "file":			 
			    LivroArray[`${field.name}`] = field.value.replace(/^.*\\/, "");
			  break;
		  default:
		}
		
	});
	// Sinopse----------------------------------------------------
	LivroArray["sinopse"] = frm.querySelector("textarea").value;
	
	//Editora ----------------------------------------------
	var pluginArrayArg = new Array();
	jsonEditora = new Object();
	var e = document.getElementById("selecteditora"); // Pega os elementos de uma lista SELECT
	if((e != null) && (e.selectedIndex != -1)){ // Verifica se existe elemento foi selecionado
			jsonEditora['id'] = e.options[e.selectedIndex].value;
			jsonEditora["editora"] = e.options[e.selectedIndex].text		
		
	}
	
	pluginArrayArg.push(jsonEditora);
	
	LivroArray["editora"] = jsonEditora;
	// console.log(JSON.stringify(LivroArrayArg));
	
	
	///Pegar os valores so multi selec autor, categoria, subcategoria....
	
	var entidades = ["autor","categoria","subcategoria"];
	$.each(entidades,function(column, value){
		let entidade = document.getElementById("select" + value);
		  
		  var options = entidade && entidade.options;
		  var opt;
		  var CategoriaArrayArg = new Array();
		  var CategoriaArray = new Array();
		  
		  if(options != null){
		 
			  for (var i=0, iLen=options.length; i<iLen; i++) {
				  
				opt = options[i];
			    if (opt.selected) {
			    	// Injeta em um objeto JSON.
			    	jsonCategoria = new Object(); 
			    	jsonCategoria['id'] = opt.value;
			    	jsonCategoria[`${value}`] = opt.text;		    	
			    	CategoriaArray.push(jsonCategoria);
			    }
			  }
		  }
		  LivroArray[`${value}`] = CategoriaArray;
	});
	// console.log(JSON.stringify(LivroArray));
	// console.log(LivroArray);
// -------------------------Fim do conversor JSON ------------------------
	//console.log(LivroArray);
	$.ajax({				
		type : "POST",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "/livro",
		dataType: 'json', // tipo de dados da requisição.
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(LivroArray),
		success: function () {
			alert(" Livro adicionado com sucesso!");
			$(document).ready(function(){
				pagination(0 , entity);
			});
        },
        error: function (erro) {        	
        	alert("Erro: " + erro.status + " Falha ao tentar adicionar livro!" + name);
        }
	});
	
// =================================================================================================================
};
		
		


