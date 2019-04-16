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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")    
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "numero")    
    private Integer numero;
    
    @Basic(optional = false)
    @Column(name = "complemento")
    private String complemento;
    
    // Coleções -------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="logradouro")
    private Logradouro logradouro;

    // Construtores ----------------------------------------------------

    public Endereco() {
    }
    
    public Endereco(Integer id, Integer numero, String complemento, Logradouro endereco) {
    	this.id = id;
    	this.numero = numero;
        this.complemento = complemento;
        this.logradouro = endereco;
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
    
}
