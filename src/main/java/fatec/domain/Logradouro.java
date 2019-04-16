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
@Table(name = "logradouro")
public class Logradouro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cep")    
    private Integer cep;
    
    @Basic(optional = false)
    @Column(name = "tipo_logradouro")
    private String tipoLogradouro;
    
    @Basic(optional = false)
    @Column(name = "logradouro")
    private String logradouro;
    
    @Basic(optional = false)
    @Column(name = "endereco_completo")
    private String enderecoCompleto;
    
    @ManyToOne
    @JoinColumn(name="id_bairro")
    private Bairro bairro;

    public Logradouro() {
    }

    
    // Coleções -------------------------------------------------------

    // Construtores ----------------------------------------------------

    public Logradouro(Integer cep) {
        this.cep = cep;
    }
    
    public Logradouro(Integer cep, String tipoLogradouro, String logradouro, String enderecoCompleto, Bairro bairro) {
        this.cep = cep;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.enderecoCompleto = enderecoCompleto;
        this.bairro = bairro;
    }    

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}


	public String getTipoLogradouro() {
		return tipoLogradouro;
	}


	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}


	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cep != null ? cep.hashCode() : 0);
        return hash;
    }

    public Bairro getBairro() {
		return bairro;
	}


	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}


	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logradouro)) {
            return false;
        }
        Logradouro other = (Logradouro) object;
        if ((this.cep == null && other.cep != null) || (this.cep != null && !this.cep.equals(other.cep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TendEndereco[ cep=" + cep + " ]";
    }
    
}
