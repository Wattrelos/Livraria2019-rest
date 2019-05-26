package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Estatus;
import fatec.layer10.repositories.EstatusRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.EstatusDTO;

@Service
public class EstatusService extends AbstractJdbcDAO{
	
	@Autowired
	private EstatusRepository repo;


	// CREATE ------------------------------------------------
	public Estatus insert(Estatus obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Estatus find(Integer id) {
		Optional<Estatus> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Estatus.class.getName()));		
	}
	
	public List<Estatus> findAll() {
		return repo.findAll();
	}

	public Page<Estatus> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Estatus update(Estatus obj) {
		Estatus newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Estatus newObj, Estatus obj) {
		newObj.setDescricao(obj.getDescricao());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma estatus que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Estatus fromDTO(EstatusDTO objDto) {
		return new Estatus(objDto.getId(), objDto.getDescricao());
	}
	

}
