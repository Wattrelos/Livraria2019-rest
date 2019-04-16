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

import fatec.domain.Logradouro;
import fatec.layer11.services.LogradouroService;
import fatec.layer20.aplications.DataTransferObject.LogradouroDTO;

@RestController
@RequestMapping(value="/logradouro")
public class LogradouroResorce {
	
	@Autowired
	private LogradouroService service;	

	// CREATE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody LogradouroDTO objDto) {
		System.out.println(objDto.toString());
		Logradouro obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getCep()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Logradouro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LogradouroDTO>> findAll() {
		List<Logradouro> list = service.findAll();
		List<LogradouroDTO> listDto = list.stream().map(obj -> new LogradouroDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	// READ (paginação)---------------------------------------	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<LogradouroDTO>> findPage(
			@RequestHeader(value="Authorization", defaultValue="") String authorization,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="cep") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Logradouro> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<LogradouroDTO> listDto = list.map(obj -> new LogradouroDTO(obj));
		return ResponseEntity.ok().header("Authorization", authorization).body(listDto);
	}
	
	// UPDATE ------------------------------------------------	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody LogradouroDTO objDto, @PathVariable Integer id) {
		Logradouro obj = service.fromDTO(objDto);
		obj.setCep(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		service.delete(id);
		System.out.print("cep deletando...");
		return ResponseEntity.noContent().build();
	}

}
