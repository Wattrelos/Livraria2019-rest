var userName = window.sessionStorage.getItem("user");
if(userName !=""){	
	document.getElementById("login-button").innerHTML =
		`<div class="dropdown show">
			  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">` +
			    userName.split("@")[0] +
		     `</a>		
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
			    <a class="dropdown-item" href="#">Meus pedidos</a>
			    <a class="dropdown-item" href="#">Minha conta</a>
			    <button class="dropdown-item" type="submit" onclick="logoff()">Sair</button></div>
			    <a class="dropdown-item" href="#" onclick="logoff()">Sair</a>
			  </div>
			</div>`;
}

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
			console.log(xhr.getAllResponseHeaders());
			window.sessionStorage.setItem("token", xhr.getResponseHeader('Authorization'));
			window.sessionStorage.setItem("user", xhr.getResponseHeader('user'));			
			document.getElementById('id01').style.display='none';
			
			// Teste de redirecionamento de páginas, antes de escapsular.
			if(xhr.getResponseHeader('authorities') == "[ROLE_ADMIN]"){
				window.location = '/gestao/listar';
			}else{
				window.location.reload();
			}
        },
        error: function (xhr, status) {
        	alert("Erro: "+ xhr.status +"," + status + " Falha ao tentar autenticar.");
        }
	})
};
function logoff(){
	window.sessionStorage.removeItem("token");
	window.sessionStorage.removeItem("user");
	window.location.href = "/index";
}
