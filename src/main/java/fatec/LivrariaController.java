package fatec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LivrariaController {
	@RequestMapping(value="/")
	public String home() {
		return "/index.html";
	}
	
	//-- Página de gestão
	@RequestMapping(value="/gestao")
	public String gestaopage() {
		return "/view/gestao/gestao.html";
	}
	
	@RequestMapping(value="/gestao/{entidade}")
	public String gestao(@PathVariable String entidade,
			@RequestParam(value="action", defaultValue="") String action			
			) {
		String url = "/view/" + entidade + "/" + entidade + action + ".html";
		System.out.print(url);
		return url;
	}
	
	@RequestMapping(value="/livraria/{entidade}")
	public String livraria(@PathVariable String entidade,
			@RequestParam(value="action", defaultValue="") String action			
			) {
		String url = "/view/" + entidade + "/" + entidade + action + ".html";
		System.out.print(url);
		return url;
	}
}
