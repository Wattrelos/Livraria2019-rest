package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fatec.domain.Livro;

public class CarrinhoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private List<Livro> itemPedido = new ArrayList<>();;
	public CarrinhoDTO() {
		
	}
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public List<Livro> getItemPedido() {
		return itemPedido;
	}



	public void setItemPedido(List<Livro> itemPedido) {
		this.itemPedido = itemPedido;
	}
	
}
