var quantidadeCarrinho = document.getElementById("quantidadeCarrinho");
if(localStorage.getItem("carrinho")){
	quantidadeCarrinho.innerHTML = (JSON.parse(localStorage.getItem("carrinho"))).length;
}