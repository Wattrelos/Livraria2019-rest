package fatec.layer30.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fatec.domain.Editora;
import fatec.layer11.services.EditoraService;
import fatec.layer20.aplications.DataTransferObject.EditoraDTO;

@RestController
@RequestMapping(value="/editora")
public class EditoraResorce {
	
	@Autowired
	private EditoraService service;	

	// CREATE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EditoraDTO objDto) {		
		Editora obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("create/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Editora obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EditoraDTO>> findAll() {
		List<Editora> list = service.findAll();
		List<EditoraDTO> listDto = list.stream().map(obj -> new EditoraDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	// READ (paginação)---------------------------------------
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<EditoraDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="editora") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Editora> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<EditoraDTO> listDto = list.map(obj -> new EditoraDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	// UPDATE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @ModelAttribute("Editora") EditoraDTO objDto) {
		Editora obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		System.out.println("\nDeletando autor:" + id);
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
