package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Editora;
import fatec.layer10.repositories.EditoraRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.EditoraDTO;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository repo;
	
	
	// CREATE ------------------------------------------------
	public Editora insert(Editora obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Editora find(Integer id) {
		Optional<Editora> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Editora.class.getName()));		
	}
	
	public List<Editora> findAll() {
		return repo.findAll();
	}

	public Page<Editora> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Editora update(Editora obj) {
		Editora newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Editora newObj, Editora obj) {
		newObj.setEditora(obj.getEditora());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma editora que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Editora fromDTO(EditoraDTO objDto) {
		return new Editora(objDto.getId(), objDto.getNome(), objDto.getDataCadastro());
	}
	

}
