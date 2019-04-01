package fatec.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido extends EntidadeDominio implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "quantidade_itens")
    private Integer quantidadeItens;
    
 // @Max(value=2000)  @Min(value=0)//
    @Column(name = "preco")
    private BigDecimal preco;
    
    @ManyToOne
    @JoinColumn(name="Pedido_id")
    @JsonIgnore
    private Pedido pedido;
    
	@OneToOne
    @JoinColumn(name="livro_id")    
    private Livro livro;
    
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public BigDecimal getSubtotal() {
		BigDecimal quantidade = new BigDecimal(quantidadeItens);
		return preco.multiply(quantidade);
	}

}
