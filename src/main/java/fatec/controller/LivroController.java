package fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fatec.domain.Livro;
import fatec.layer11.services.LivroService;
import fatec.layer20.aplications.DataTransferObject.LivroDTO;

@Controller
@RequestMapping("/livros")
public class LivroController {
	@Autowired
	LivroService livroService;

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/livro/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		
		
		return "/livro/list"; 
	}
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public  String findPage(Model model,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="titulo") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Livro> list = livroService.findPage(page, linesPerPage, orderBy, direction);
		Page<LivroDTO> listDto = list.map(obj -> new LivroDTO(obj));
		model.addAttribute("listaLivro", listDto);
		return "/livro/list";
	}
}
