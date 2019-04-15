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
    @JoinColumn(name="cep")
    private TendLogradouro endereco;

    // Construtores ----------------------------------------------------

    public Endereco() {
    }
    
    public Endereco(Integer id, Integer numero, String complemento, TendLogradouro endereco) {
    	this.id = id;
    	this.numero = numero;
        this.complemento = complemento;
        this.endereco = endereco;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", numero=" + numero + ", complemento=" + complemento + ", endereco=" + endereco + "]";
	}
    
}
