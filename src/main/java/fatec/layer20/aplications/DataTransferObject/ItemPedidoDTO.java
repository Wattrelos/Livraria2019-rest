package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;

import fatec.domain.Livro;


public class ItemPedidoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Livro livro;
	private Integer quantidade;
	
	public ItemPedidoDTO() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
