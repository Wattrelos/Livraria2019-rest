package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Autor;
import fatec.domain.Categoria;
import fatec.layer10.repositories.AutorRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.AutorDTO;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository repo;
	
	
	// CREATE ------------------------------------------------
	public Autor insert(Autor obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Autor find(Integer id) {
		Optional<Autor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Categoria.class.getName()));		
	}
	
	public List<Autor> findAll() {
		return repo.findAll();
	}

	public Page<Autor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Autor update(Autor obj) {
		Autor newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Autor newObj, Autor obj) {
		newObj.setAutor(obj.getAutor());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma autor que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Autor fromDTO(AutorDTO objDto) {
		return new Autor(objDto.getId(), objDto.getNome(), objDto.getDataCadastro());
	}
	

}
