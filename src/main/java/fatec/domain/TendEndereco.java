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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "tend_endereco")
@NamedQueries({
    @NamedQuery(name = "TendEndereco.findAll", query = "SELECT t FROM TendEndereco t"),
    @NamedQuery(name = "TendEndereco.findByCep", query = "SELECT t FROM TendEndereco t WHERE t.cep = :cep"),
    @NamedQuery(name = "TendEndereco.findByLogradouro", query = "SELECT t FROM TendEndereco t WHERE t.logradouro = :logradouro"),
    @NamedQuery(name = "TendEndereco.findByEndereco", query = "SELECT t FROM TendEndereco t WHERE t.endereco = :endereco"),
    @NamedQuery(name = "TendEndereco.findByEnderecoCompleto", query = "SELECT t FROM TendEndereco t WHERE t.enderecoCompleto = :enderecoCompleto")})
public class TendEndereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cep")    
    private Integer cep;
    
    @Basic(optional = false)
    @Column(name = "logradouro")
    private String logradouro;
    
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    
    @Basic(optional = false)
    @Column(name = "endereco_completo")
    private String enderecoCompleto;
    
    @ManyToOne
    @JoinColumn(name="id_bairro")
    private TendBairro bairro;

    public TendEndereco() {
    }

    
    // Coleções -------------------------------------------------------

    // Construtores ----------------------------------------------------

    public TendEndereco(Integer cep) {
        this.cep = cep;
    }
    
    public TendEndereco(Integer cep, String logradouro, String endereco, String enderecoCompleto) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.endereco = endereco;
        this.enderecoCompleto = enderecoCompleto;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public TendBairro getBairro() {
		return bairro;
	}


	public void setBairro(TendBairro bairro) {
		this.bairro = bairro;
	}


	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TendEndereco)) {
            return false;
        }
        TendEndereco other = (TendEndereco) object;
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
