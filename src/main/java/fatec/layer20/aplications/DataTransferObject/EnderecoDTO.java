package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;

import fatec.domain.Endereco;
import fatec.domain.TendLogradouro;



public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private Integer numero;
    private String complemento;
    private TendLogradouro endereco;

	public EnderecoDTO() {
	} 
	
	public EnderecoDTO(Endereco obj) {
		id = obj.getId();
		numero = obj.getNumero();
		complemento = obj.getComplemento();
		endereco = obj.getEndereco();
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

	public TendLogradouro getEndereco() {
		return endereco;
	}

	public void setEndereco(TendLogradouro endereco) {
		this.endereco = endereco;
	}

	
	
}
