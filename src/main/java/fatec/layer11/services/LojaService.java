package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Loja;
import fatec.layer10.repositories.LojaRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.LojaDTO;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository repo;
	
	
	// CREATE ------------------------------------------------
	public Loja insert(Loja obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Loja find(Integer id) {
		Optional<Loja> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Loja.class.getName()));		
	}
	
	public List<Loja> findAll() {
		return repo.findAll();
	}

	public Page<Loja> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Loja update(Loja obj) {
		Loja newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Loja newObj, Loja obj) {		
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
	public Loja fromDTO(LojaDTO objDto) {
		return new Loja(objDto.getId(), objDto.getNomeFantasia(), objDto.getDataCadastro());
	}

}
