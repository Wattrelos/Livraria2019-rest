package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.util.List;

import fatec.domain.Pagamento;
import fatec.domain.PagamentoComBoleto;
import fatec.domain.PagamentoComCartao;
import fatec.domain.Pedido;



public class PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private PagamentoComCartao pagamentoComCartao;
	private PagamentoComBoleto pagamentoComBoleto;
	private List<Pedido> pedido;
	

	public PagamentoDTO() {
	} 
	
	public PagamentoDTO(Pagamento obj) {
		this.id = obj.getId();;
		this.pagamentoComCartao = obj.getPagamentoComCartao();
		this.pagamentoComBoleto = obj.getPagamentoComBoleto();
		this.pedido = obj.getPedido();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PagamentoComCartao getPagamentoComCartao() {
		return pagamentoComCartao;
	}

	public void setPagamentoComCartao(PagamentoComCartao pagamentoComCartao) {
		this.pagamentoComCartao = pagamentoComCartao;
	}

	public PagamentoComBoleto getPagamentoComBoleto() {
		return pagamentoComBoleto;
	}

	public void setPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto) {
		this.pagamentoComBoleto = pagamentoComBoleto;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

}
