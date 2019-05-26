package fatec.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PagamentoComCartao implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")    
	private Integer id;
	private Integer numeroDeParcelas;
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "cartao_id", referencedColumnName = "id")	
	private CartaoCredito cartoCredito;
	
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, Integer numeroDeParcelas, CartaoCredito cartaoCredito) {
		this.id = id;
		this.numeroDeParcelas = numeroDeParcelas;
		this.cartoCredito = cartaoCredito;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public CartaoCredito getCartoCredito() {
		return cartoCredito;
	}

	public void setCartoCredito(CartaoCredito cartoCredito) {
		this.cartoCredito = cartoCredito;
	}
	
}
