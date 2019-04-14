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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "tend_cidade")
@NamedQueries({
    @NamedQuery(name = "TendCidade.findAll", query = "SELECT t FROM TendCidade t"),
    @NamedQuery(name = "TendCidade.findByCidade", query = "SELECT t FROM TendCidade t WHERE t.cidade = :cidade")})
public class TendCidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cidade")
    private Integer idCidade;
    
    @Basic(optional = false)
    @Column(name = "cidade")
    
    private String cidade;
    @JoinColumns({
        @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")})
    @ManyToOne(optional = false)
    private TendEstado estado;

    public TendCidade() {
    }

    public TendCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public TendCidade(Integer idCidade, String cidade) {
        this.idCidade = idCidade;
        this.cidade = cidade;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public TendEstado getTendEstado() {
        return estado;
    }

    public void setTendEstado(TendEstado tendEstado) {
        this.estado = tendEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCidade != null ? idCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TendCidade)) {
            return false;
        }
        TendCidade other = (TendCidade) object;
        if ((this.idCidade == null && other.idCidade != null) || (this.idCidade != null && !this.idCidade.equals(other.idCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TendCidade[ idCidade=" + idCidade + " ]";
    }
    
}
