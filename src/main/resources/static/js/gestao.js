var pedidosHTML = document.getElementById("listaPedidos");
var pedidos = "";
var estatus = new Object();
$.ajax({	// Não se preocupe com a redundância. Tudo isso irá para NodeJS.			
	type : "GET",
	headers: {"Authorization": window.sessionStorage.getItem('token')},
	url : "/estatus",
	contentType: "application/json; charset=utf-8",		
	success: function (result) {
		estatus = result;
		// console.log(estatus);		
    },
    error: function (erro, textStatus, xhr) {
    	alert("Erro: "+ erro.status + textStatus + "Falha ao tentar ler o tipo de status!");
    }
});

function gestaoPedidos(){
	
	// paginaLista.innerHTML = "<div>Teste de página!</div>";
	
    $.ajax({				
		type : "GET",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "/pedido/page",
		// dataType: 'json', // tipo de dados da requisição.
		contentType: "application/json; charset=utf-8",		
		success: function (result) {
			// console.log(result);
			pedidos = `
				<table class="table table-striped"> 
	           		<thead>	
	                     <tr>
	                         <th scope="col">Número do pedido</th>
	                         <th scope="col">Observação</th>
	                         <th scope="col">Data do pedido</th>
	                     </tr>
	               </thead>	
	               <thead>`;
			result.content.forEach((field, index)=>{
				var corDoBotao = "btn-warning";
				if(field.observacao == "preparando")
					corDoBotao = "btn-primary";
				pedidos += `
				<tr>
					<td><button class="btn btn-sm btn-block ${corDoBotao}" onclick="gestaoPedido(${field.id})">Pedido número ${field.id}</button></td>				
					<td>${field.observacao}</td>
					<td>${field.dataCadastro.slice(0, 10)} - ${field.dataCadastro.slice(11, 16)}</td>
				</tr>`;			
				pedidosHTML.innerHTML = pedidos;
			});			
        },
        error: function (erro, textStatus, xhr) {
        	alert("Erro: "+ erro.status + textStatus + "Falha ao tentar ler dados "+ frm.nome.value + "!");
        }
	});    
};

function gestaoPedido(numeroPedido){ // Gestão de um pedido específico
	 $.ajax({				
			type : "GET",
			headers: {"Authorization": window.sessionStorage.getItem('token')},
			url : "/pedido/" + numeroPedido,
			// dataType: 'json', // tipo de dados da requisição.
			contentType: "application/json; charset=utf-8",		
			success: function (result) {
				let total = 0;
				pedidos = `
					<h1>Gestão de pedido número ${numeroPedido}</h1>
					<div class="meuPedido">
						<label>Editar situação</label>
			            <label>Livro</label>
			            <label>Título</label>
			            <label>Quantidade</label>
			            <label>preco</label>
			            <label>Subtotal</label>
			            <label></label>`;
				result.estoque.forEach((field, index)=>{
					pedidos += `				
							<div>
						   	<select id="${field.id}" onchange="alterarPedido(${field.id},${numeroPedido})">
						   		<option value="${field.estatus.descricao}" disabled selected>${field.estatus.descricao}</option>
						   	`; // Alterar pelo número do item no inventário (gestão de estoque) respeitando a unicidade o objeto.
					estatus.forEach((estatus, indice)=>{
						pedidos += `<option value="${estatus.id}">${estatus.descricao}</option>`;
					});
					pedidos += `
							</select>
							</div>
							<div><img src="\\img\\${field.livro.imagem}" alt="Capa do livro" width="30" height="40"></div>
							<div>${field.livro.titulo}</div>
							<div>${field.quantidade}</div>
							<div>R$ ${field.preco.toFixed(2)}</div>
							<div>R$ ${(field.subtotal).toFixed(2)}</div>
							<div>`;
					pedidos +=
					   `<div></div>
					</div>`;
					total += field.subtotal;
				});		 
				pedidos += `</thead>
					<div></div><div></div><div></div><div></div>
					<label><b>Total: </b></label><div>R$ ${(total).toFixed(2)}</div>										
					`;
				// Se o pedido ainda não foi enviado dá tempo de cancelar.
		
				pedidos += `</div>
					</div>
				</div>`;
				pedidosHTML.innerHTML = pedidos;
	        },
	        error: function (erro, textStatus, xhr) {
	        	alert("Erro: "+ erro.status + textStatus + "Falha ao tentar ler dados "+ frm.nome.value + "!");
	        }			 
	});	
}
function alterarPedido(estoqueId, numeroPedido){
	var estatus = document.getElementById(estoqueId).value;	
	// Estatus ------------------------------------
	var itemEstoque = new Object();
	var estatusArray = new Object();
	estatusArray["id"] = estatus;
	itemEstoque["estatus"] = estatusArray;
	console.log(JSON.stringify(itemEstoque));
	// Enviar para alteração do item do pedido:
	var url = "/estoque/estatus/" + estoqueId;
	alterarStatusPedido(itemEstoque, url); // Alterar status do item no inventário
	// Enviar para alteração o status do pedido:
	var url = "/pedido/estatus/" + numeroPedido;
	var pedido = new Object(); 
	pedido["observacao"] = "Pedido modificado";	
	alterarStatusPedido(pedido, url);
	gestaoPedido(numeroPedido);
}

function alterarStatusPedido(dados, url){
	console.log("Dados: " + JSON.stringify(dados) + " url: " + url);
	$.ajax({				
		type : "PUT",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : url,
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(dados),
		success: function () {
			alert("Estatus do item alterado!");			
	    },
	    error: function (erro) {
	    	alert(erro.status + " Falha ao tentar atualizar!");	    	
	    }
	});     
}



