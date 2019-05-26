package fatec.layer30.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fatec.domain.Estoque;
import fatec.layer11.services.EstoqueService;
import fatec.layer20.aplications.DataTransferObject.EstoqueDTO;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueResorce {
	
	@Autowired
	private EstoqueService service;	

	// CREATE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EstoqueDTO objDto) {		
		Estoque obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Estoque obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstoqueDTO>> findAll() {
		List<Estoque> list = service.findAll();
		List<EstoqueDTO> listDto = list.stream().map(obj -> new EstoqueDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<EstoqueDTO>> findPage(
			@RequestHeader(value="Authorization", defaultValue="") String authorization,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="estoque") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Estoque> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<EstoqueDTO> listDto = list.map(obj -> new EstoqueDTO(obj));
		return ResponseEntity.ok().header("Authorization", authorization).body(listDto);
	}
	
	// UPDATE ------------------------------------------------	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EstoqueDTO objDto, @PathVariable Integer id) {		
		Estoque obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/estatus/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> mudarEstatus(@Valid @RequestBody EstoqueDTO objDto, @PathVariable Integer id) {
		Estoque obj = service.find(id); // Busca o item de estoque a ser alterado de status
		obj.setEstatus(objDto.getEstatus());
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		service.delete(id);
		System.out.print("estoque deletando...");
		return ResponseEntity.noContent().build();
	}

}
