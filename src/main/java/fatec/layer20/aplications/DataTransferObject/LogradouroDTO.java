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
	private String complemento;
	private String local;
	// Coleções -------------------------------------------------------
	private Bairro bairro;
	
	// Construtores ----------------------------------------------------
	public LogradouroDTO() {
	} 
	
	public LogradouroDTO(Logradouro obj) {
		cep = obj.getCep();
		tipoLogradouro = obj.getTipoLogradouro();
		logradouro = obj.getLogradouro();
		complemento = obj.getComplemento();
		local = obj.getLocal();
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
		return complemento;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.complemento = enderecoCompleto;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
}
