/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fatec.domain.enums.Perfil;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeDominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;    
    
    @Basic(optional = false)
    @NotNull    
    @Column(name = "email", unique=true, length = 80)    
    private String email;
    
    @JsonIgnore
    @Column(name = "senha", length = 80)
    private String senha;
    
    @Column(name = "data_cadastro",
    		updatable=false,
    		insertable = false,
    		columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro; 
    
 // Definirá o nivel de privilégios de acesso.    
    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
    
    // Construtores ----------------------------------------

    public Usuario() {
    }
    public Usuario(Integer id, String email, Date dataCadastro, String senha, Integer perfil) {
    	this.id = id;
    	this.email = email;
        this.dataCadastro = dataCadastro;
        this.senha = senha;
        this.addPerfil(perfil);
    }
    
    // Setters and Getters ----------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

    public Date getDataCadastro() {
        return dataCadastro;
    }  

    public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Integer perfil) {
		perfis.add(perfil);
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
	
	// Hashcodes and iquals -------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", dataCadastro=" + dataCadastro
				+ ", perfis=" + perfis + "]";
	}

    
    
}
