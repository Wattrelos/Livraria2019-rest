package fatec.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    
    // Coleções. ------------------------------------------------------
    @ManyToOne
	@JoinColumn(name="loja_id")
    @JsonBackReference
	private Loja loja;
        
    @JsonManagedReference
	@OneToMany(mappedBy="cliente", cascade = CascadeType.REFRESH)
	private List<Pedido> pedido = new ArrayList<>();
    
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="cliente_has_endereco",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")    
    ) 
    private List<TendEndereco> endereco = new ArrayList<>();
	
	// Construtores ---------------------------------------------------------
	public Cliente() {		
	}
	
	public Cliente(Integer id, String nome, long cpf, String email, Date dataNascimento, List<TendEndereco> endereco, List<Pedido> pedido, Date dataCadastro, String senha, Integer perfil) {
		super(id, email, dataCadastro, senha, perfil);
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.pedido = pedido;
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

	public List<TendEndereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<TendEndereco> endereco) {
		this.endereco = endereco;
	}
}
