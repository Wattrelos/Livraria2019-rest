package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import fatec.domain.Pedido;
import fatec.domain.ItemPedido;



public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String observacao;
	private Date dataCadastro;
	
	//Coleções---------------	
	private List<ItemPedido> itemPedido = new ArrayList<>();

	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	// Getters and Setters
	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PedidoDTO() {
	} 
	
	public PedidoDTO(Pedido obj) {
		id = obj.getId();
		observacao = obj.getObservacao();
		itemPedido = obj.getItemPedido();
		dataCadastro  = obj.getDataCadastro();
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
