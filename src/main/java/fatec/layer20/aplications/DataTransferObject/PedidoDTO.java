package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import fatec.domain.Pedido;



public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String observacao;

	public PedidoDTO() {
	} 
	
	public PedidoDTO(Pedido obj) {
		id = obj.getId();
		observacao = obj.getObservacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setNome(String observacao) {
		this.observacao = observacao;
	}
}
