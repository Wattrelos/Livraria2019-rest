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
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Josias Wattrelos
*/
@Entity
@Table(name = "categoria")
@XmlRootElement
public class Categoria extends EntidadeDominio implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)    
   @Basic(optional = false) 
   @Column(name = "id")
   private Integer id;
   
   @Basic(optional = false)    
   @Column(name = "categoria", unique = true)
   private String categoria;       
   
   @Column(name = "data_cadastro",
   		updatable=false,
   		insertable = false,
   		columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro; 

   public Categoria() {
   }

   public Categoria(Integer id) {
       this.id = id;
   }
   
       public Categoria(String categoria) {
       this.categoria = categoria;        
   }
   
   public Categoria(Integer id, String categoria) {
       this.id = id;
       this.categoria = categoria;        
   }

   public Categoria(Integer id, String categoria, Date dataCadastro) {
       this.id = id;
       this.categoria = categoria;
       this.dataCadastro = dataCadastro;
   }

   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public String getCategoria() {
       return categoria;
   }

   public void setCategoria(String categoria) {
       this.categoria = categoria;
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
       if (!(object instanceof Categoria)) {
           return false;
       }
       Categoria other = (Categoria) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "Categoria{" + "id=" + id + ", categoria=" + categoria + ", dataCadastro=" + dataCadastro + '}';
   }

}
