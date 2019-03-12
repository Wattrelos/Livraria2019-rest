package fatec.layer30.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fatec.domain.Subcategoria;
import fatec.layer11.services.SubcategoriaService;
import fatec.layer20.aplications.DataTransferObject.SubcategoriaDTO;

@RestController
@RequestMapping(value="/subcategorias")
public class SubcategoriaResorce {
	
	@Autowired
	private SubcategoriaService service;	

	// CREATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody SubcategoriaDTO objDto) {
		System.out.println(objDto);
		Subcategoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Subcategoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SubcategoriaDTO>> findAll() {
		List<Subcategoria> list = service.findAll();
		List<SubcategoriaDTO> listDto = list.stream().map(obj -> new SubcategoriaDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	// READ (paginação)---------------------------------------
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<SubcategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="subcategoria") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Subcategoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<SubcategoriaDTO> listDto = list.map(obj -> new SubcategoriaDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	// UPDATE ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody SubcategoriaDTO objDto, @PathVariable Integer id) {
		Subcategoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
