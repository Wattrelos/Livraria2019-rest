package fatec.layer30.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fatec.domain.Livro;
import fatec.layer11.services.LivroService;
import fatec.layer20.aplications.DataTransferObject.LivroDTO;

@RestController
@RequestMapping(value="/livro")
public class LivroResorce {
	
	@Autowired
	private LivroService service;	

	// CREATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody LivroDTO objDto) {
		Livro obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Livro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LivroDTO>> findAll() {
		List<Livro> list = service.findAll();
		List<LivroDTO> listDto = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	// READ (paginação)---------------------------------------
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<LivroDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="titulo") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Livro> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<LivroDTO> listDto = list.map(obj -> new LivroDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	// UPDATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @ModelAttribute("Livro") LivroDTO objDto) {
		Livro obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		System.out.println("\nDeletando livro:" + id);
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
