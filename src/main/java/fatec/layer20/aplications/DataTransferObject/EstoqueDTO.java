package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.math.BigDecimal;

import fatec.domain.Estoque;
import fatec.domain.Livro;

public class EstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer quantidadeItens;
	private BigDecimal preco;	
	private Livro livro;
	
	///Construtores -----------------------------------------------------
	public EstoqueDTO() {
		
	}
	
	public EstoqueDTO(Estoque obj) {
		id = obj.getId();
		quantidadeItens = obj.getQuantidade();
		preco = obj.getPreco();
		livro = obj.getLivro();
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
}
