package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import fatec.domain.CartaoCredito;



public class CartaoCreditoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Long numero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")	
	private String nome;
	private Integer cvv;
	private Date dataValidade;
	
	
	private Date dataCadastro;

	public CartaoCreditoDTO() {
	} 
	
	public CartaoCreditoDTO(CartaoCredito obj) {
		id = obj.getId();
		numero = obj.getNumero();
		nome = obj.getNome();
		dataValidade = obj.getDataValidade();
		dataCadastro = obj.getDataCadastro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}
