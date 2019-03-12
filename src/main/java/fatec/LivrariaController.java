package fatec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LivrariaController {
	
	  @RequestMapping("/")
	  public String index() {
	    return "index.html";
	  }
	  
	  // Categoria ---------------------------------------------------------------------------------------
	  @RequestMapping("/categorialistar")
	  public String categorias() {		  
	    return "view/category/list.html";
	  }
	  
	  @RequestMapping("/categoriainserir")
	  public String create() {
		  return ("view/category/create.html");
	  }

}
