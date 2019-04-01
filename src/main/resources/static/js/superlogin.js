function superlogin(form){
	let campos =  form.querySelectorAll("input");
	var EntidadeArray = new Object();
	campos.forEach((field, index)=>{
		EntidadeArray[`${field.name}`] = field.value;		
	});	
	// Enviar requisição
	$.ajax({				
		type : "POST",
		url : "/login",
		//dataType: 'json', // tipo de dados da requisição.
		// contentType: "application/json; charset=utf-8",
		contentType: "application/javascript",
		data : JSON.stringify(EntidadeArray),		
		success: function (data, textStatus, xhr) {			
			// console.log(xhr.getAllResponseHeaders());
			window.sessionStorage.setItem("token", xhr.getResponseHeader('Authorization'));
			document.getElementById("login-button").innerHTML =
				`<button class="btn btn-success btn-sm ml-3" >Minha conta</button>`;
			document.getElementById('id01').style.display='none'
			
			// Teste de redirecionamento de páginas, antes de escapsular.
			if(xhr.getResponseHeader('authorities') == "[ROLE_ADMIN]"){	
				window.location = '/gestao';
			}
        },
        error: function (xhr, status) {
        	console.log(xhr);        	
        	alert("Erro: "+ xhr.status +"," + status + " Falha ao tentar autenticar.");
        }
	})	
};