package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.math.BigDecimal;

import fatec.domain.Estoque;
import fatec.domain.Livro;
import fatec.domain.Pedido;
import fatec.domain.Estatus;

public class EstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer quantidadeItens;	
	private BigDecimal preco;	
	private Livro livro;
	private Pedido pedido;
	private Estatus estatus;
	
	///Construtores -----------------------------------------------------
	public EstoqueDTO() {
		
	}
	
	public EstoqueDTO(Estoque obj) {
		id = obj.getId();
		quantidadeItens = obj.getQuantidade();
		preco = obj.getPreco();
		livro = obj.getLivro();
		pedido = obj.getPedido();
		estatus = obj.getEstatus();
	}
	// Getters and Setters ------------------------------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}
	
}
