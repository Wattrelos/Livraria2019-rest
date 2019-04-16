package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import fatec.domain.Bairro;
import fatec.domain.Logradouro;



public class LogradouroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cep;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String tipoLogradouro;
	private String logradouro;
	private String enderecoCompleto;
	// Coleções -------------------------------------------------------
	private Bairro bairro;
	
	// Construtores ----------------------------------------------------
	public LogradouroDTO() {
	} 
	
	public LogradouroDTO(Logradouro obj) {
		cep = obj.getCep();
		tipoLogradouro = obj.getTipoLogradouro();
		logradouro = obj.getLogradouro();
		enderecoCompleto = obj.getEnderecoCompleto();
		bairro = obj.getBairro();
	}
	
	// Setters and Getters ----------------------------------------------------

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
}
