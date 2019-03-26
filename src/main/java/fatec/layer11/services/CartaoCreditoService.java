package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.CartaoCredito;
import fatec.layer10.repositories.CartaoCreditoRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.CartaoCreditoDTO;

@Service
public class CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository repo;
	
	
	// CREATE ------------------------------------------------
	public CartaoCredito insert(CartaoCredito obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public CartaoCredito find(Integer long1) {
		Optional<CartaoCredito> obj = repo.findById(long1);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ long1+ ", Tipo: "+ CartaoCredito.class.getName()));		
	}
	
	public List<CartaoCredito> findAll() {
		return repo.findAll();
	}

	public Page<CartaoCredito> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public CartaoCredito update(CartaoCredito obj) {
		CartaoCredito newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(CartaoCredito newObj, CartaoCredito obj) {		
		newObj.setNumero(obj.getNumero());
		newObj.setNome(obj.getNome());
		newObj.setDataValidade(obj.getDataValidade());
	}
	
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cartaocredito que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public CartaoCredito fromDTO(CartaoCreditoDTO objDto) {
		return new CartaoCredito(objDto.getId(), objDto.getNumero(), objDto.getNome(), objDto.getCvv(), objDto.getDataValidade(), objDto.getDataCadastro());
	}
	

}
