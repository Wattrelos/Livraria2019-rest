package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.LojaMatriz;
import fatec.layer10.repositories.LojaRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.LojaDTO;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository repo;
	
	
	// CREATE ------------------------------------------------
	public LojaMatriz insert(LojaMatriz obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public LojaMatriz find(Integer id) {
		Optional<LojaMatriz> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ LojaMatriz.class.getName()));		
	}
	
	public List<LojaMatriz> findAll() {
		return repo.findAll();
	}

	public Page<LojaMatriz> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public LojaMatriz update(LojaMatriz obj) {
		LojaMatriz newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(LojaMatriz newObj, LojaMatriz obj) {		
		newObj.setNomeFantasia(obj.getNomeFantasia());
	}

	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma loja que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public LojaMatriz fromDTO(LojaDTO objDto) {
		return new LojaMatriz(objDto.getId(), objDto.getNomeFantasia(), objDto.getDataCadastro());
	}

}
