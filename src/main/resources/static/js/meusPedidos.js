var pedidosHTML = document.getElementById("listaPedidos");
var pedidos = "";
var cliente = new Object();
var troca = new Object();
$.ajax({				// Carregar pedidos do cliente
	type : "GET",
	headers: {"Authorization": window.sessionStorage.getItem('token')},
	url : "/cliente/" + window.sessionStorage.getItem('number'),
	contentType: "application/json; charset=utf-8",
	success: function (result) {
		cliente = result;
		pedidos += `
			<table class="table table-striped"> 
           		<thead>	
                     <tr>
                         <th scope="col">Número do pedido</th>
                         <th scope="col">Observação</th>
                         <th scope="col">Data do pedido</th>
                     </tr>
               </thead>	
               <thead>`; 
		result.pedido.forEach((field, index)=>{
			var corDoBotao = "btn-warning";
			if(field.observacao == "preparando")
				corDoBotao = "btn-primary";
			pedidos += `
			<tr>
				<td><button class="btn btn-sm btn-block ${corDoBotao}" onclick="meuPedido(${field.id})">Pedido número ${field.id}</button></td>				
				<td>${field.observacao}</td>
				<td>${field.dataCadastro.slice(0, 10)} - ${field.dataCadastro.slice(11, 16)}</td>
			</tr>`;			
			pedidosHTML.innerHTML = pedidos;
		});	
		result.pedidoTroca.forEach((field2, index)=>{
			pedidos += `
			<tr>
				<td><button class="btn btn-sm btn-block btn-info" onclick="meuPedidoTroca(${field2.id})">Pedido de troca número ${field2.id}</button></td>
				<td>${field2.observacao}</td>
				<td>${field2.dataCadastro.slice(0, 10)} - ${field2.dataCadastro.slice(11, 16)}</td>
			</tr>`;			
			pedidosHTML.innerHTML = pedidos;
		});
		pedidos += `</thead>	                          
			</table>`;
    
    },
    error: function (erro) {        	
    	alert("Erro: " + erro.status + " Falha ao tentar ler pedidos!");
    }
});

function meuPedido(numeroPedido){ // Mostrar um pedido específico
	cliente.pedido.forEach((result, index)=>{
		if(result.id == numeroPedido){
			let total = 0;
			pedidos = `
				<div class="meuPedido">
					<label>Situação</label>
		            <label>Livro</label>
		            <label>Título</label>
		            <label>Quantidade</label>
		            <label>preco</label>
		            <label>Subtotal</label>
		            <label></label>
					<thead>`;
			result.estoque.forEach((field, index)=>{
				pedidos += `
					<div>${field.estatus.descricao}</div>													
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
				<div><button class="btn btn-lg btn-block btn-success text-uppercase" onclick="solicitarTroca('${result.id}')">Solicitar troca</button></div>
				`;
			// Se o pedido ainda não foi enviado dá tempo de cancelar.
	
			pedidos += `</div>
				</div>
			</div>`;
			pedidosHTML.innerHTML = pedidos;
		}
	});	
}

function solicitarTroca(numeroPedido){
	cliente.pedido.forEach((result, index)=>{
		if(result.id == numeroPedido){
			let total = 0;
			pedidos = `
				<h1>Selecionar livros para troca referente ao pedido número: ${numeroPedido}</h1>
				<form class="trocaDevolucao" onSubmit="confirmarTroca(this, ${numeroPedido});return false;">
					<label>Selecionar</label>
		            <label>Livro</label>
		            <label>Título</label>
		            <label>Quantidade</label>
		            <label>preco</label>`;       
			
			result.estoque.forEach((field, index)=>{
				if(field.estatus.id >= 11 && field.estatus.id < 15 ){
					pedidos += 
					   `<div><input type="checkbox" name="livro-${field.livro.id}" value="${field.id}"> Selecionar<br></div>
					    <div><img src="\\img\\${field.livro.imagem}" alt="Capa do livro" width="30" height="40"></div>
						<div>${field.livro.titulo}</div>
						<div>${field.quantidade}</div>
						<div>R$ ${field.preco.toFixed(2)}</div>`;
				}
			});		 
			pedidos +=				
			   `	<button  class="btn btn-lg btn-block btn-success text-uppercase">Confirmar troca</button>
			   </form>`;			
			pedidosHTML.innerHTML = pedidos;
		}
	});	
	
}
function confirmarTroca(frm, numeroPedido){
	// console.log("Pedido número: " + numeroPedido)
	let campos =  frm.querySelectorAll("input"); // Selecionar todos os input (checkbox).	
	campos.forEach((field, index)=>{
		var livroArray = new Array();
		cliente.pedido.forEach((estoque, index)=>{
			var livro = new Object();
			livro["id"] = estoque.id;
			var item = new Object();
			// Estatus ------------------------------------
			var estatusArray = new Object();
			estatusArray["id"] = 11;
			item["estatus"] = estatusArray;
			// Livros
			item["quantidade"] = estoque.quantidade;
			item["preco"] = estoque.preco;
			item["livro"] = livro;
			livroArray.push(item);
		});
		
		if (field.getAttribute("type") == "checkbox") {  // Verifica se o campo é do tipo checbox;  
            if(field.checked){ //Verifica se o checkbox está marcado;
            	// console.log("Estoque número: " + field.value);
            	// Estatus ------------------------------------
            	var itemEstoque = new Object();        		
        		var estatusArray = new Object();
        		estatusArray["id"] = 20;
        		itemEstoque["estatus"] = estatusArray;
        		// console.log(JSON.stringify(itemEstoque));        		
        		var url = "/estoque/estatus/" + field.value;
        		if(alterarStatusPedido(itemEstoque, url)){ // Alterar status do item no inventário
        			alert("Solicitação de troca enviado!");
        		}else{
        			alert(" Falha ao tentar registrar solicitação!");
        		}
        		//Atualizar estado do pedido -----------------------------------------------------------------------
        		var url = "/pedido/estatus/" + numeroPedido;
        		var pedido = new Object(); 
        		pedido["observacao"] = "Solicitando troca";
        		console.log(JSON.stringify(pedido));
        		alterarStatusPedido(pedido, url);
        		
            }
		}
		
	});
	
}
function alterarStatusPedido(dados, url){
	$.ajax({				
		type : "PUT",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : url,
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(dados),
		success: function () {			
			return true;
	    },
	    error: function (erro) {
	    	alert(erro.status + " Falha ao tentar atualizar!");
	    	return false;
	    }
	});     
}

function meuPedidoTroca(numeroPedido){
	cliente.pedidoTroca.forEach((troca, index)=>{
		if(troca.id == numeroPedido){
			pedidos = `
				<h1>Pedidos de troca referente ao pedido ${troca.pedido.id}:</h1>
				<div class="trocaDevolucao">
					<label>Situação atual</label>
		            <label>Livro</label>
		            <label>Título</label>
		            <label>Quantidade</label>
		            <label>preco</label>
		        `;
			troca.pedido.estoque.forEach((field, index)=>{
				if(field.estatus.id >=20 && field.estatus.id < 30){
					pedidos += 
				   `<div>${field.estatus.descricao}</div>
				    <div><img src="\\img\\${field.livro.imagem}" alt="Capa do livro" width="30" height="40"></div>
					<div>${field.livro.titulo}</div>
					<div>${field.quantidade}</div>
					<div>R$ ${field.preco.toFixed(2)}</div>
					`;
				}	
			});		 
			pedidos +=				
			   `</div>`;			
			pedidosHTML.innerHTML = pedidos;			
		}
	});
	
}