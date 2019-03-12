var formlistaCategorias = document.getElementById("listarcategorias")
var pagecategoria = `Lista de categorias2: <br>`;
var getData = $.getJSON("http://localhost:8080/categorias/1", function(result){
	
	console.log(result);
	
	formlistaCategorias.innerHTML = "<li><div>categoria: " + result.categoria + "</div>"
	+ "<div>Data: " + result.dataCadastro.slice(0, 10); + "</div></li>";
	
	//$.each(result, function(i, field){
		
		
		// pagecategoria += "campo: " + field;
		// formlistaCategorias.append("<br>" + field);
	//});
});

