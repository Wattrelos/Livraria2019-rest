package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import fatec.domain.CartaoCredito;
import fatec.domain.Cliente;
import fatec.domain.Endereco;
import fatec.domain.Pedido;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	// @CPF (message="O número do CPF é inválido!")
	private long cpf;
	
	@Email(message="Email inválido")
	private String email;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	private char sexo;
	
	private Date dataCadastro;
	
	// Coleções
	private List<Endereco> endereco = new ArrayList<>();
	private List<CartaoCredito> cartaoCredito = new ArrayList<>();
	private List<Pedido> pedido = new ArrayList<>();


	public ClienteDTO() {
	} 
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		email = obj.getEmail();
		dataCadastro = obj.getDataCadastro();
		sexo = obj.getSexo();
		dataNascimento = obj.getDataNascimento();
		pedido = obj.getPedido();
		endereco = obj.getEndereco();
		cartaoCredito = obj.getCartaoCredito();
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

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}	

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}
	
	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> enderecos) {
		this.endereco = enderecos;
	}

	public List<CartaoCredito> getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
}
