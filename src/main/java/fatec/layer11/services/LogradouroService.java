package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Logradouro;
import fatec.layer10.repositories.LogradouroRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.LogradouroDTO;

@Service
public class LogradouroService extends AbstractJdbcDAO{
	
	@Autowired
	private LogradouroRepository repo;


	// CREATE ------------------------------------------------
	public Logradouro insert(Logradouro obj) {
		obj.setCep(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Logradouro find(Integer id) {
		Optional<Logradouro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Logradouro.class.getName()));		
	}
	
	public List<Logradouro> findAll() {
		return repo.findAll();
	}

	public Page<Logradouro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Logradouro update(Logradouro obj) {
		Logradouro newObj = find(obj.getCep());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Logradouro newObj, Logradouro obj) {
		newObj.setLogradouro(obj.getLogradouro());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma logradouro que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Logradouro fromDTO(LogradouroDTO objDto) {
		return new Logradouro(objDto.getCep(), objDto.getTipoLogradouro(), objDto.getLogradouro(), objDto.getComplemento(), objDto.getLocal(), objDto.getBairro());
	}
	

}
