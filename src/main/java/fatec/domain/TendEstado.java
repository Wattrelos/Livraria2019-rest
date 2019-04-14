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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "tend_estado")
@NamedQueries({
    @NamedQuery(name = "TendEstado.findAll", query = "SELECT t FROM TendEstado t"),
    @NamedQuery(name = "TendEstado.findByIdEstado", query = "SELECT t FROM TendEstado t WHERE t.idEstado = :idEstado"),
    @NamedQuery(name = "TendEstado.findByEstado", query = "SELECT t FROM TendEstado t WHERE t.estado = :estado"),
    @NamedQuery(name = "TendEstado.findByUf", query = "SELECT t FROM TendEstado t WHERE t.uf = :uf")})
public class TendEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    
    @Basic(optional = false)
    @Column(name = "uf")
    private String uf;    

    public TendEstado() {
    }

    public TendEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public TendEstado(Integer idEstado, String estado, String uf) {
        this.idEstado = idEstado;
        this.estado = estado;
        this.uf = uf;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TendEstado)) {
            return false;
        }
        TendEstado other = (TendEstado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TendEstado[ idEstado=" + idEstado + " ]";
    }
    
}
