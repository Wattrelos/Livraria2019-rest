package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Categoria;
import fatec.layer10.repositories.CategoriaRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.CategoriaDTO;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	// CREATE ------------------------------------------------
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Categoria.class.getName()));
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setCategoria(obj.getCategoria());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome(), objDto.getDataCadastro());
	}
	

}
