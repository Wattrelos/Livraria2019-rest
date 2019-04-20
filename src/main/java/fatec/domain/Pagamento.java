package fatec.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Integer id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pagamento", cascade = CascadeType.REFRESH)
    private List<Pedido> pedido;
	
    // Coleções-----------------------------------------------------------
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cartao_id", referencedColumnName = "id")
    private PagamentoComCartao pagamentoComCartao;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "boleto_id", referencedColumnName = "id")
    private PagamentoComBoleto pagamentoComBoleto;
	
 // Construtores ----------------------------------------------------------- 
	public Pagamento() {
	}

	public Pagamento(Integer id, PagamentoComCartao pagamentoComCartao, PagamentoComBoleto pagamentoComBoleto, List<Pedido> pedido) {
		super();
		this.id = id;
		this.pagamentoComCartao = pagamentoComCartao;
		this.pagamentoComBoleto = pagamentoComBoleto;
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PagamentoComCartao getPagamentoComCartao() {
		return pagamentoComCartao;
	}

	public void setPagamentoComCartao(PagamentoComCartao pagamentoComCartao) {
		this.pagamentoComCartao = pagamentoComCartao;
	}

	public PagamentoComBoleto getPagamentoComBoleto() {
		return pagamentoComBoleto;
	}

	public void setPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto) {
		this.pagamentoComBoleto = pagamentoComBoleto;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
