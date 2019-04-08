package fatec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fatec.layer30.resources.AutorResorce;
import fatec.layer30.resources.CategoriaResorce;
import fatec.layer30.resources.LivroResorce;
import fatec.layer30.resources.PedidoResorce;

@Controller
public class LivrariaController {
	
	@Autowired
	AutorResorce autorResource;
	@Autowired
	CategoriaResorce categoriaResource;
	@Autowired
	LivroResorce livroResource;
	@Autowired
	PedidoResorce pedidoResource;
	
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