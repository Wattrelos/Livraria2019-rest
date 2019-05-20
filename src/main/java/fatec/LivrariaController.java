package fatec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fatec.domain.Cliente;
import fatec.domain.Pedido;
import fatec.layer11.services.ClienteService;
import fatec.layer11.services.PedidoService;
import fatec.layer30.resources.AutorResorce;
import fatec.layer30.resources.CategoriaResorce;
import fatec.layer30.resources.ClienteResorce;
import fatec.layer30.resources.LivroResorce;
import fatec.layer30.resources.PedidoResorce;

@Controller
public class LivrariaController {
	
	@Autowired
	AutorResorce autorResource;
	@Autowired
	CategoriaResorce categoriaResource;
	@Autowired
	ClienteResorce clienteResource;
	@Autowired
	LivroResorce livroResource;
	@Autowired
	PedidoResorce pedidoResource;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	ClienteService clienteService;
	
	public LivrariaController() {
		//vhs.put("listarAutor", new AutorResorce());
	}
	
	@RequestMapping(value="/index")
	public String home() {
		return "/home.html";
	}
	@RequestMapping(value="/gestao")
	public String gestao() {		
		return "/gestao/gestao.html";		
	}
	
	@RequestMapping(value="/carrinho/carrinho")
	public String carrinho() {		
		return "/carrinho/carrinho.html";
	}

	@RequestMapping(value="/cliente/cadastro")
	public String cliente() {		
		return "/cliente/cadastro";
	}
	
	@RequestMapping(value="/pagamento/pagamento")
	public String pagamento() {		
		return "/pagamento/pagamento.html";
	}
	
	@RequestMapping(value="/pedido/pedido")
	public String pedido(Model model, @RequestParam(value="cliente", defaultValue="2") Integer number) {
		Cliente cliente = clienteService.find(number);		
		model.addAttribute("cliente", cliente);		
		return "/pedido/pedido.html";
	}
	
	@RequestMapping(value="/pedido/confirmacao")
	public String pedidoConfirmacao() {
		return "/pedido/confirmacao.html";
	}
	
	@RequestMapping(value="/pedido/meusPedidos")
	public String meusPedidos() {	
		return "/pedido/meusPedidos.html";
	}
	@RequestMapping(value="/pedido/meuPedido")
	public String meuPedido() {	
		return "/pedido/meuPedido.html";
	}
	
	@RequestMapping(value="/endereco/endereco", method=RequestMethod.POST)
	public String endereco(Model model, @RequestParam(value="cliente", defaultValue="2") Integer number) {
		Cliente cliente = clienteService.find(number);
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + cliente);
		
		model.addAttribute("cliente", cliente);		
		return "/endereco/endereco.html";
	}
	
	@RequestMapping(value="/endereco/endereco", method=RequestMethod.GET)
	public String endereco2(Model model, @RequestParam(value="cliente", defaultValue="2") Integer number) {
		Cliente cliente = clienteService.find(number);
		System.out.println(">>>>>>>>>>>>>>>>>>>>" + cliente.getEndereco());
		if(cliente.getEndereco().isEmpty()) {
			return "/endereco/cadastro.html";
		}
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + cliente);
		model.addAttribute("cliente", cliente);		
		return "/endereco/endereco.html";
	}
	
	@RequestMapping(value="/endereco/cadastrar", method=RequestMethod.GET)
	public String enderecoCadastrar() {			
		return "/endereco/cadastro.html";
	}
	
	@RequestMapping(value="/livraria/{entidade}/page", method=RequestMethod.GET)
	public  String findPage(Model model, @PathVariable String entidade,
			@RequestHeader(value="Authorization", defaultValue="") String authorization,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		 switch(entidade){
		 case "autor":
			 model.addAttribute("list", autorResource.findPage(authorization, page, linesPerPage, "autor", direction ).getBody());
			 break;
		 case "categoria":
			 model.addAttribute("list", categoriaResource.findPage(page, linesPerPage, "categoria", direction ).getBody());
			 break;
		 case "cliente":
			 model.addAttribute("list", clienteResource.findPage(page, linesPerPage, "nome", direction ).getBody());
			 break;
		 case "livro":
			 model.addAttribute("list", livroResource.findPage(page, linesPerPage, "titulo", direction ).getBody());
			 break;
		 case "pedido":
			 model.addAttribute("list", pedidoResource.findPage(page, linesPerPage, "id", direction ).getBody());
			 break;
		  default:
		 }
		// model.addAttribute("list", autorResource.findPage(page, linesPerPage, orderBy, direction ).getBody());
		String url = "/" + entidade + "/" + "list.html";
		return url;
	}
	
}