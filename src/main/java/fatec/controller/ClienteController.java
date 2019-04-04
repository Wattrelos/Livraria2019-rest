package fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fatec.domain.Cliente;
import fatec.layer11.services.ClienteService;
import fatec.layer20.aplications.DataTransferObject.ClienteDTO;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/cliente/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		
		
		return "/cliente/list"; 
	}
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public  String findPage(Model model,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		model.addAttribute("listaCliente", listDto);
		return "/cliente/list";
	}
}
