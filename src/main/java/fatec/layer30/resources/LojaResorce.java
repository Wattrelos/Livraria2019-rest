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

import fatec.domain.LojaMatriz;
import fatec.layer11.services.LojaService;
import fatec.layer20.aplications.DataTransferObject.LojaDTO;

@RestController
@RequestMapping(value="/loja")
public class LojaResorce {
	
	@Autowired
	private LojaService service;	

	// CREATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody LojaDTO objDto) {
		System.out.println(objDto.toString());
		LojaMatriz obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// READ one ------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		LojaMatriz obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// READ (all)---------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LojaDTO>> findAll() {
		List<LojaMatriz> list = service.findAll();
		List<LojaDTO> listDto = list.stream().map(obj -> new LojaDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	// READ (paginação)---------------------------------------
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<LojaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="loja") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<LojaMatriz> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<LojaDTO> listDto = list.map(obj -> new LojaDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	// UPDATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody LojaDTO objDto, @PathVariable Integer id) {
		LojaMatriz obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	// DELETE ------------------------------------------------
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestParam(value="id") Integer id) {
		service.delete(id);
		System.out.print("Loja deletando...");
		return ResponseEntity.noContent().build();
	}

}
