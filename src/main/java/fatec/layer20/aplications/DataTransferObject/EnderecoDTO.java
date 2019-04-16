package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import fatec.domain.Endereco;
import fatec.domain.Logradouro;



public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private Integer numero;
	private String complemento;
	private Logradouro logradouro;

	public EnderecoDTO() {
	} 
	
	public EnderecoDTO(Endereco obj) {
		id = obj.getId();
		numero = obj.getNumero();
		complemento = obj.getComplemento();
		logradouro = obj.getLogradouro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
}
