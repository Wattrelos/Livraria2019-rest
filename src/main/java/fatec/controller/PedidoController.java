package fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fatec.domain.Pedido;
import fatec.layer11.services.PedidoService;
import fatec.layer20.aplications.DataTransferObject.PedidoDTO;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/pedido/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/pedido/list"; 
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String find(Model model, @PathVariable Integer id) {
		Pedido obj = pedidoService.find(id);
		if(obj != null) {
			model.addAttribute("id", obj.getId());
			model.addAttribute("nome", obj.getClienteNome().getNome());
			model.addAttribute("observacao", obj.getObservacao());
			model.addAttribute("itemPedido", obj.getItemPedido());
			model.addAttribute("total", obj.getTotal());
			model.addAttribute("dataCadastro", obj.getDataCadastro());			
		}
		return "/pedido/pedido";
	}
	
	
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public  String findPage(Model model,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Pedido> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> listDto = list.map(obj -> new PedidoDTO(obj));
		model.addAttribute("listaPedido", listDto);
		return "/pedido/list";
	}
}
