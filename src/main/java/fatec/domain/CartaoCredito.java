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
 * @author Administrador
 */
@Entity
@Table(name = "cartao_credito")
public class CartaoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "cvv")
    private Integer cvv;  
    
    @Basic(optional = false)
    @Column(name = "nome", length = 80)
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "numero")
    private long numero;
    
    @Column(name = "bandeira", length = 20)
    private String bandeira;
    
    @Basic(optional = false)
    @Column(name = "data_validade")
    @Temporal(TemporalType.DATE)
    private Date dataValidade;
    
    @Basic(optional = false)    
    @Column(name = "data_cadastro",
	updatable=false,
	insertable = false,
	columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    public CartaoCredito() {
    }

    public CartaoCredito(Integer id) {
        this.id = id;
    }

    public CartaoCredito(Integer id, long numero, String nome, int cvv, Date dataValidade, Date dataCadastro) {
        this.id = id;
        this.cvv = cvv;
        this.nome = nome;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
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
        if (!(object instanceof CartaoCredito)) {
            return false;
        }
        CartaoCredito other = (CartaoCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testclass8.CartaoCredito_1[ id=" + id + " ]";
    }
    
}
