package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import fatec.domain.PedidoHasLivro;
import fatec.domain.Pedido;

public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@Length(min=0, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String observacao;
	private Date dataCadastro;
	
	//Coleções---------------
	private List<PedidoHasLivro> pedidoHasLivro = new ArrayList<>();
	
	public PedidoDTO() {
		
	}
	
	public PedidoDTO(Pedido	obj) {
		id = obj.getId();
		observacao = obj.getObservacao();
		pedidoHasLivro = obj.getPedidoHasLivro();
		dataCadastro = obj.getDataCadastro();
	}

	// Getters and Setters
	public void setLivro(List<PedidoHasLivro> livro) {
		this.pedidoHasLivro = livro;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public List<PedidoHasLivro> getPedidoHasLivro() {
		return pedidoHasLivro;
	}

	public void setPedidoHasLivro(List<PedidoHasLivro> pedidoHasLivro) {
		this.pedidoHasLivro = pedidoHasLivro;
	}
}
