package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.util.Date;

import fatec.domain.Cliente;
import fatec.domain.Estatus;
import fatec.domain.Pedido;
import fatec.domain.PedidoTroca;

public class PedidoTrocaDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String observacao;
    private Date dataCadastro;  
    private Cliente cliente;
    private Pedido pedido;
    private Estatus estatus;
    
    public PedidoTrocaDTO() {    	
    }
    
    public PedidoTrocaDTO(PedidoTroca obj) {    	
    	id = obj.getId();
    	observacao = obj.getObservacao();
    	dataCadastro = obj.getDataCadastro();
    	cliente = obj.getCliente();
    	pedido = obj.getPedido();
    	estatus = obj.getEstatus();
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

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}
}
