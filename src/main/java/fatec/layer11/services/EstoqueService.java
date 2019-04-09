package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Estoque;
import fatec.layer10.repositories.EstoqueRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.EstoqueDTO;

@Service
public class EstoqueService extends AbstractJdbcDAO{
	
	@Autowired
	private EstoqueRepository repo;


	// CREATE ------------------------------------------------
	public Estoque insert(Estoque obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Estoque find(Integer id) {
		Optional<Estoque> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Estoque.class.getName()));		
	}
	
	public List<Estoque> findAll() {
		return repo.findAll();
	}

	public Page<Estoque> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Estoque update(Estoque obj) {
		Estoque newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Estoque newObj, Estoque obj) {
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setPreco(obj.getPreco());
		newObj.setPedido(obj.getPedido());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma estoque que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Estoque fromDTO(EstoqueDTO objDto) {
		return new Estoque(objDto.getId(), objDto.getQuantidadeItens(), objDto.getPreco(), objDto.getLivro());
	}

}
