package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import fatec.domain.TendEndereco;



public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cep;
    private String logradouro;
    private String endereco;
    private String enderecoCompleto;

	public EnderecoDTO() {
	} 
	
	public EnderecoDTO(TendEndereco obj) {
		cep = obj.getCep();
		logradouro = obj.getLogradouro();
		endereco = obj.getEndereco();
		enderecoCompleto = obj.getEnderecoCompleto();
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
	
	
}
