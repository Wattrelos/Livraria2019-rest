// Multiple select
var entidades = ["editora","autor","categoria","subcategoria"];
var url = "";
var dataList = "";
var _text = "";
$.each(entidades,function(column, value){
	
	url = "http://localhost:8080/" + value + "/page?linesPerPage=60";
	$.getJSON(url, function (result){
		
		_text = 		
	`<label>${value.toUpperCase()}</label>
	<select multiple id="select${value}">`;
				dataList = document.getElementById(value);
				result.content.forEach(function(field, index){
		_text += 
				`<option value="${field.id}">${field.nome}</option>`;
				});
		_text +=
	`</select>
	`;
			dataList.innerHTML = _text;
	});
});
//Single select select
var entidades = ["editora"];
var url = "";
var dataList = "";
var _text = "";
$.each(entidades,function(column, value){	
	url = "http://localhost:8080/" + value + "/page?linesPerPage=60";
	$.getJSON(url, function (result){
		_text = 
			
	`<label>${value.toUpperCase()}</label>
	<select id="select${value}">`;
				dataList = document.getElementById(value);
				result.content.forEach(function(field, index){
					_text += 
				`<option value="${field.id}">${field.nome}</option>`;
				});
			_text +=
	`</select>
	`;
			dataList.innerHTML = _text;
	});
});

