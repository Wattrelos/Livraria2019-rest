package fatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestao")
public class GestaoController {

	@GetMapping("/listar")
	public String cadastrar() {
		return "/gestao/gestao";
	}
	
}
