package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;


public class ItemPedidoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer quantidade;
	
	public ItemPedidoDTO() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
