package fatec.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Devolução extends EntidadeDominio implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id")
    private Integer id;
    
 // @Max(value=2000)  @Min(value=0)//
    @Column(name = "saldo")
    private BigDecimal saldo;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="pedido_id")    
    private Pedido pedido;
    
    
 // Objetos----------------------------------------------------------- 
    @JsonIgnore
    @ManyToOne
    private Cliente cliente;
    
    // Construtores ------------------------------------------------------------------------
   
	
}
