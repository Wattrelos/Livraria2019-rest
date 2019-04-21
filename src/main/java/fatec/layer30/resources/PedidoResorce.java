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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fatec.domain.Pedido;
import fatec.layer11.services.PedidoService;
import fatec.layer20.aplications.DataTransferObject.PedidoDTO;

@RestController
@RequestMapping(value="/pedido")
public class PedidoResorce {
	
	@Autowired
	private PedidoService service;	

	// CREATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoDTO objDto) {			
		Pedido obj = service.fromDTO(objDto);		
		obj = service.insert(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();				
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<Pedido> list = service.findAll();
		List<PedidoDTO> listDto = list.stream().map(obj -> new PedidoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	// READ (paginação)---------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PedidoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> listDto = list.map(obj -> new PedidoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	// UPDATE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PedidoDTO objDto, @PathVariable Integer id) {
		Pedido obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		service.delete(id);
		System.out.print("Pedido deletando...");
		return ResponseEntity.noContent().build();
	}

}
