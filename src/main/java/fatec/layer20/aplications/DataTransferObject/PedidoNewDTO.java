package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import fatec.domain.Cliente;
import fatec.domain.Estoque;
import fatec.domain.Pedido;

public class PedidoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@Length(min=0, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String observacao;
	private Date dataCadastro;	
	private Cliente cliente;
	
	//Coleções---------------
	private List<Estoque> estoque = new ArrayList<>();
	
	public PedidoNewDTO() {
		
	}
	
	public PedidoNewDTO(Pedido	obj) {
		id = obj.getId();
		observacao = obj.getObservacao();
		estoque = obj.getEstoque();
		dataCadastro = obj.getDataCadastro();
	}

	// Getters and Setters
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}
	
}
