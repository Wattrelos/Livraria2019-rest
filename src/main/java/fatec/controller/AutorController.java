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

import fatec.domain.Autor;
import fatec.layer11.services.AutorService;
import fatec.layer20.aplications.DataTransferObject.AutorDTO;

@Controller
@RequestMapping("/autores")
public class AutorController {
	@Autowired
	AutorService autorService;

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		
		
		return "/cargo/lista"; 
	}
	
	@RequestMapping(value="/{id}")
	public String find(Model model, @PathVariable Integer id) {
		Autor obj = autorService.find(id);		
		model.addAttribute("id", obj.getId());
		model.addAttribute("nome", obj.getAutor());
		model.addAttribute("dataCadastro", obj.getDataCadastro());
		return "/autor/autor";
	}
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public  String findPage(Model model,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="autor") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Autor> list = autorService.findPage(page, linesPerPage, orderBy, direction);
		Page<AutorDTO> listDto = list.map(obj -> new AutorDTO(obj));
		model.addAttribute("listaAutor", listDto);
		return "/autor/list";
	}
}
