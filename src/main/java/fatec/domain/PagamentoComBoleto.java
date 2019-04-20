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
public class PagamentoComBoleto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")    
	private Integer id;
	private Integer status;

	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "boleto_id", referencedColumnName = "id")
	private Boleto boleto;
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, Integer status, Boleto boleto) {
		this.id = id;
		this.status = status;
		this.boleto = boleto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
	
}
