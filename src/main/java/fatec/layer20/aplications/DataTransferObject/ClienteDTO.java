package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fatec.domain.Cliente;
import fatec.domain.LojaMatriz;



public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	private long cpf;
	private Date dataNascimento;
	private Date dataCadastro;
	
	//Chave estrangeira
	@ManyToOne
	@JoinColumn(name="id")	
	private LojaMatriz lojamatriz;

	public LojaMatriz getLojamatriz() {
		return lojamatriz;
	}

	public void setLojamatriz(LojaMatriz lojamatriz) {
		this.lojamatriz = lojamatriz;
	}

	public ClienteDTO() {
	} 
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		dataNascimento = obj.getDataNascimento();
		dataCadastro = obj.getDataCadastro();
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
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
