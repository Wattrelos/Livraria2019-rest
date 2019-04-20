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

import fatec.domain.Pagamento;
import fatec.layer11.services.PagamentoService;
import fatec.layer20.aplications.DataTransferObject.PagamentoDTO;

@RestController
@RequestMapping(value="/pagamento")
public class PagamentoResorce {
	
	
	
	
	@Autowired
	private PagamentoService service;	

	// CREATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PagamentoDTO objDto) {
		System.out.println(objDto.toString());
		Pagamento obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pagamento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PagamentoDTO>> findAll() {
		List<Pagamento> list = service.findAll();
		List<PagamentoDTO> listDto = list.stream().map(obj -> new PagamentoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PagamentoDTO>> findPage(
			@RequestHeader(value="Authorization", defaultValue="") String authorization,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="pagamento") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Pagamento> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PagamentoDTO> listDto = list.map(obj -> new PagamentoDTO(obj));
		return ResponseEntity.ok().header("Authorization", authorization).body(listDto);
	}
	
	// UPDATE ------------------------------------------------	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PagamentoDTO objDto, @PathVariable Integer id) {
		Pagamento obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		service.delete(id);
		System.out.print("pagamento deletando...");
		return ResponseEntity.noContent().build();
	}

}
