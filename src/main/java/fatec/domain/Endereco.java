/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")    
    private Integer id;

    @Basic(optional = false)
    @Column(name = "numero")    
    private Integer numero;

    @Column(name = "complemento", length = 30)
    private String complemento;
    
 // Objetos -------------------------------------------------------
    @JsonIgnore
    @ManyToOne    
    private Cliente cliente;

    // Coleções -------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="logradouro")
    private Logradouro logradouro;
    
    // Construtores ----------------------------------------------------
    public Endereco() {
    }
    
    public Endereco(Integer id, Integer numero, String complemento, Logradouro endereco, Cliente cliente) {
    	this.id = id;
    	this.numero = numero;
        this.complemento = complemento;
        this.logradouro = endereco;
        this.cliente = cliente;
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

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
