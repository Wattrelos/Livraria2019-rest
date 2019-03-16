package fatec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LivrariaController {
	@RequestMapping("/index")
	public String index() {
		return "index.html";
	}
	  
	  // Autor ---------------------------------------------------------------------------------------
		  @RequestMapping("/autoreslistar")
		  public String autores() {		  
		    return "view/listauthor.html";
		  }
		  @RequestMapping("/actorinsert")
		  public String createActor() {
			  return ("view/author/create.html");
		  }
		  
		//--Autor. Página alternativa
		  @RequestMapping("/autor")
		  public String  autorList() {
			  return "view/list.html";
		  }
		  @RequestMapping("/autor2")
		  public String  autorList2() {
			  return "view/list.jsp";
		  }
		  
			// Cliente ---------------------------------------------------------------------------------------
		  @RequestMapping(value="/cliente")
		  public String cliente() {		  
		    return "view/customer/list.html";
		  }
		  @RequestMapping("/customer/insert")
		  public String createCliente() {
			  return ("view/customer/create.html");
		  }
	  
	  // Categoria ---------------------------------------------------------------------------------------
	  @RequestMapping("/categorialistar")
	  public String categorias() {		  
	    return "view/listcategory.html";
	  }
	  
	  @RequestMapping("/categoriainserir")
	  public String create() {
		  return ("view/category/create.html");
	  }
	  
	  // Subcategoria ---------------------------------------------------------------------------------------
	  @RequestMapping("/subcategorialistar")
	  public String Subcategorias() {		  
	    return "view/listsubcategory.html";
	  }
	  
	  @RequestMapping("/subcategoriainserir")
	  public String Subcategorycreate() {
		  return ("view/subcategory/create.html");
	  }
	  
	  // Editora ---------------------------------------------------------------------------------------
	  @RequestMapping("/editoralistar")
	  public String listPublisher() {		  
	    return "view/listpublisher.html";
	  }
	  
	  @RequestMapping("/createpublisher")
	  public String publisherCreate() {
		  return ("view/listpublisher/create.html");
	  }
	  
	  // Livro ---------------------------------------------------------------------------------------
	  @RequestMapping("/livrolistar")
	  public String livros() {
		  return "view/listbook.html";
	  }
	  
	  @RequestMapping("/livroinserir")
	  public String livrosInserir() {
		  return "view/book/create.html";
	  }
}
