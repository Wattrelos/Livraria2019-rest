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
import fatec.domain.Subcategoria;
import fatec.layer10.repositories.SubcategoriaRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.SubcategoriaDTO;

@Service
public class SubcategoriaService {
	
	@Autowired
	private SubcategoriaRepository repo;
	
	
	// CREATE ------------------------------------------------
	public Subcategoria insert(Subcategoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Subcategoria find(Integer id) {
		Optional<Subcategoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Categoria.class.getName()));		
	}
	
	public List<Subcategoria> findAll() {
		return repo.findAll();
	}

	public Page<Subcategoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Subcategoria update(Subcategoria obj) {
		Subcategoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Subcategoria newObj, Subcategoria obj) {
		newObj.setSubcategoria(obj.getSubcategoria());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma subcategoria que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Subcategoria fromDTO(SubcategoriaDTO objDto) {
		return new Subcategoria(objDto.getId(), objDto.getNome(), objDto.getDataCadastro());
	}
	

}
