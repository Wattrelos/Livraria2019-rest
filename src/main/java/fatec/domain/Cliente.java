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
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    
    // Coleções. ------------------------------------------------------
    @ManyToOne
	@JoinColumn(name="loja_id")
    @JsonBackReference
	private Loja loja;
    
    @OneToMany(mappedBy="cliente", cascade=CascadeType.PERSIST)
	private List<Endereco> endereco = new ArrayList<>();
    
    @JsonManagedReference
	@OneToMany(mappedBy="cliente", cascade = CascadeType.REFRESH)
	private List<Pedido> pedidos = new ArrayList<>();
	
	// Construtores ---------------------------------------------------------
	public Cliente() {		
	}
	
	public Cliente(Integer id, String nome, long cpf, String email, Date dataNascimento, List<Endereco> endereco, Date dataCadastro, String senha, Integer perfil) {
		super(id, email, dataCadastro, senha, perfil);
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
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
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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

	public void setEndereco(List<Endereco> enderecos) {
		this.endereco = enderecos;
	}
	
	
}
