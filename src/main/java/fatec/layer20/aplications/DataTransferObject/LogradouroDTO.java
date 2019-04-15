package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;

import fatec.domain.TendBairro;
import fatec.domain.TendLogradouro;



public class LogradouroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cep;
    private String logradouro;
    private String endereco;
    private String enderecoCompleto;
    private TendBairro bairro;

	public LogradouroDTO() {
	} 
	
	public LogradouroDTO(TendLogradouro obj) {
		cep = obj.getCep();
		logradouro = obj.getLogradouro();
		endereco = obj.getEndereco();
		enderecoCompleto = obj.getEnderecoCompleto();
		bairro = obj.getBairro();
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public TendBairro getBairro() {
		return bairro;
	}

	public void setBairro(TendBairro bairro) {
		this.bairro = bairro;
	}

}
