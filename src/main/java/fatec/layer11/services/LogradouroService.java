package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.TendLogradouro;
import fatec.layer10.repositories.TendLogradouroRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.LogradouroDTO;

@Service
public class LogradouroService extends AbstractJdbcDAO{
	
	@Autowired
	private TendLogradouroRepository repo;


	// CREATE ------------------------------------------------
	public TendLogradouro insert(TendLogradouro obj) {
		obj.setCep(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public TendLogradouro find(Integer id) {
		Optional<TendLogradouro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ TendLogradouro.class.getName()));		
	}
	
	public List<TendLogradouro> findAll() {
		return repo.findAll();
	}

	public Page<TendLogradouro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public TendLogradouro update(TendLogradouro obj) {
		TendLogradouro newObj = find(obj.getCep());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(TendLogradouro newObj, TendLogradouro obj) {
		newObj.setCep(obj.getCep());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cep que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public TendLogradouro fromDTO(LogradouroDTO objDto) {
		return new TendLogradouro(objDto.getCep(), objDto.getLogradouro(), objDto.getEndereco(), objDto.getEnderecoCompleto(), objDto.getBairro());
	}
	

}
