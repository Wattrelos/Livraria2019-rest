/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Josias Wattrelos
 */
@Entity
@Table(name = "editora")
public class Editora extends EntidadeDominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "editora", unique = true, length = 80)
    private String editora;
    
    @Column(name = "data_cadastro",
    		updatable=false,
    		insertable = false,
    		columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro; 

    public Editora() {
    }

    public Editora(Integer id) {
        this.id = id;
    }

    public Editora(String editora) {
        this.editora = editora;
    }
    public Editora(Integer id, String editora) {
    	this.id = id;
        this.editora = editora;
    }
    
    public Editora(Integer id, String editora, Date dataCadastro) {
    	this.id = id;
        this.editora = editora;
        this.dataCadastro = dataCadastro;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editora)) {
            return false;
        }
        Editora other = (Editora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Editora{" + "id=" + id + ", editora=" + editora + ", dataCadastro=" + dataCadastro;
    }


    
}
