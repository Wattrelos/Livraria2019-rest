package fatec.layer30.resources;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fatec.layer20.aplications.DataTransferObject.CarrinhoDTO;

@RestController
@RequestMapping(value="/carrinho")
public class CarrinhoResorce {		

	// CREATE ------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CarrinhoDTO objDto) {
		System.out.println(objDto.toString());
		return null;
	}
	
}
