package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import fatec.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=60, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	private long cpf;
	private Date dataNascimento;
	private Date dataCadastro;

	public ClienteDTO() {
	} 
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();		
		dataCadastro = obj.getDataCadastro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}
