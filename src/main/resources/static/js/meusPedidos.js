var pedidosHTML = document.getElementById("listaPedidos");
var pedidos = "";
var cliente = new Object()
$.ajax({				
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
			pedidos += `
			<tr>
				<td><button class="btn btn-sm btn-block btn-primary" onclick="meuPedido(${field.id})">Pedido número ${field.id}</button></td>				
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

function meuPedido(numeroPedido){
	console.log(numeroPedido);
	$.ajax({
		type : "GET",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "/pedido/" + numeroPedido,
		contentType: "application/json; charset=utf-8",
		success: function (result) {
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
					<div>${field.status.descricao}</div>													
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
	    },
	    error: function (erro) {        	
	    	alert("Erro: " + erro.status + " Falha ao tentar ler pedidos!");
	    }
	});		
}

function solicitarTroca(numeroPedido){
	$.ajax({
		type : "GET",
		headers: {"Authorization": window.sessionStorage.getItem('token')},
		url : "/pedido/" + numeroPedido,
		contentType: "application/json; charset=utf-8",
		success: function (result) {
			let total = 0;
			pedidos = `
				<h1>Selecionar livros para troca</h1>
				<form class="trocaDevolucao" onSubmit="confirmarTroca(this, ${numeroPedido});return false;">
					<label>Selecionar</label>
		            <label>Livro</label>
		            <label>Título</label>
		            <label>Quantidade</label>
		            <label>preco</label>
		        `;       
			
			result.estoque.forEach((field, index)=>{
				pedidos += 
				   `<div><input type="checkbox" name="livro-${field.livro.id}" value="${field.livro.id}"> Selecionar<br></div>
				    <div><img src="\\img\\${field.livro.imagem}" alt="Capa do livro" width="30" height="40"></div>
					<div>${field.livro.titulo}</div>
					<div>${field.quantidade}</div>
					<div>R$ ${field.preco.toFixed(2)}</div>
					`;				
			});		 
			pedidos +=				
			   `	<button  class="btn btn-lg btn-block btn-success text-uppercase">Confirmar troca</button>
			   </form>`;
			
			pedidosHTML.innerHTML = pedidos;
	    },
	    error: function (erro) {        	
	    	alert("Erro: " + erro.status + " Falha ao tentar ler pedidos!");
	    }
	});
}
function confirmarTroca(frm, pedido){
	console.log("Pedido número: " + pedido)
	let campos =  frm.querySelectorAll("input"); // Selecionar todos os input (checkbox).	
	campos.forEach((field, index)=>{
		if (field.getAttribute("type") == "checkbox") {  // Verifica se o campo é do tipo checbox;  
            if(field.checked){ //Verifica se o checkbox está marcado;
            	console.log(field);
            }
		}
	});
}

function meuPedidoTroca(numeroPedido){
	cliente.pedidoTroca.forEach((troca, index)=>{
		if(troca.id == numeroPedido){
			pedidos = `
				<h1>Pedidos de troca:</h1>
				<div class="trocaDevolucao">
					<label>Situação atual</label>
		            <label>Livro</label>
		            <label>Título</label>
		            <label>Quantidade</label>
		            <label>preco</label>
		        `;
			troca.pedido.estoque.forEach((field, index)=>{
				if(field.status.id >=20 && field.status.id < 30){
					pedidos += 
				   `<div>${field.status.descricao}</div>
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