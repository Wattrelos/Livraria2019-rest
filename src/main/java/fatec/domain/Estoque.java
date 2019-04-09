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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estoque extends EntidadeDominio implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
 // @Max(value=2000)  @Min(value=0)//
    @Column(name = "preco")
    private BigDecimal preco;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="pedido_id")    
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name="livro_id")
    private Livro livro;
    
    // Construtores ------------------------------------------------------------------------
    public Estoque() {    	
    }
    
    public Estoque(Integer id, Integer quantidade, BigDecimal preco, Livro livro) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
		this.livro = livro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		BigDecimal quantidade2 = new BigDecimal(quantidade);
		return preco.multiply(quantidade2);
	}
}
