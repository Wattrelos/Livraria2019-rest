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
@Table(name = "tend_bairro")
@NamedQueries({
    @NamedQuery(name = "TendBairro.findAll", query = "SELECT t FROM TendBairro t"),
    @NamedQuery(name = "TendBairro.findByBairro", query = "SELECT t FROM TendBairro t WHERE t.bairro = :bairro")})
public class TendBairro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bairro")
    private Integer idBairro;
    
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    
    @ManyToOne
    @JoinColumn(name="id_cidade")
    private TendCidade cidade;

    public TendBairro() {
    }

    public TendBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public TendBairro(Integer idBairro, int idCidade, String bairro) {
        this.idBairro = idBairro;
        this.bairro = bairro;
    }

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public TendCidade getCidade() {
		return cidade;
	}

	public void setCidade(TendCidade cidade) {
		this.cidade = cidade;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idBairro != null ? idBairro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TendBairro)) {
            return false;
        }
        TendBairro other = (TendBairro) object;
        if ((this.idBairro == null && other.idBairro != null) || (this.idBairro != null && !this.idBairro.equals(other.idBairro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TendBairro[ idBairro=" + idBairro + " ]";
    }
    
}
