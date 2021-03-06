package fatec.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@SuppressWarnings("serial")
@Entity
public class Cliente extends Usuario {
	
    @Basic(optional = false)
    @Size(min = 8, max = 80)
    @Column(name = "nome")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "cpf", unique=true)
    private long cpf;    
    
    @Basic(optional = false)
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @Basic(optional = false)
    @Column(name = "sexo")
    private char sexo;
    
    // Coleções. ------------------------------------------------------
    @ManyToOne
	@JoinColumn(name="loja_id")
    @JsonBackReference
	private Loja loja;
        
    @OneToMany(mappedBy="cliente", cascade = CascadeType.REFRESH)
	private List<CartaoCredito> cartaoCredito = new ArrayList<>();
    
	@OneToMany(mappedBy="cliente", cascade = CascadeType.REFRESH)
	private List<Pedido> pedido = new ArrayList<>();	
    
	@OneToMany(mappedBy="cliente", cascade = CascadeType.REFRESH)    
    private List<Endereco> endereco = new ArrayList<>();
	
	@OneToMany(mappedBy="cliente", cascade = CascadeType.REFRESH)    
    private List<PedidoTroca> pedidoTroca = new ArrayList<>();
	
	
    
	// Construtores ---------------------------------------------------------
	public Cliente() {
		
	}
	
	public Cliente(Integer id, String nome, long cpf, String email, Date dataNascimento, char sexo, List<Endereco> endereco, List<Pedido> pedido,List<CartaoCredito> cartaoCredito, Date dataCadastro,
			String senha, Integer perfil) {
		
		super(id, email, dataCadastro, senha, perfil);
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.pedido = pedido;
		this.endereco = endereco;
		this.cartaoCredito = cartaoCredito;
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

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public List<CartaoCredito> getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<PedidoTroca> getPedidoTroca() {
		return pedidoTroca;
	}

	public void setPedidoTroca(List<PedidoTroca> pedidoTroca) {
		this.pedidoTroca = pedidoTroca;
	}
}
