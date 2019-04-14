package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.TendEndereco;
import fatec.layer10.repositories.TEnderecoRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.EnderecoDTO;

@Service
public class TEnderecoService extends AbstractJdbcDAO{
	
	@Autowired
	private TEnderecoRepository repo;

	// CREATE ------------------------------------------------
	public TendEndereco insert(TendEndereco obj) {
		obj.setCep(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public TendEndereco find(Integer id) {
		Optional<TendEndereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ TendEndereco.class.getName()));		
	}
	
	public List<TendEndereco> findAll() {
		return repo.findAll();
	}

	public Page<TendEndereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public TendEndereco update(TendEndereco obj) {
		TendEndereco newObj = find(obj.getCep());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(TendEndereco newObj, TendEndereco obj) {
		newObj.setEndereco(obj.getEndereco());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Tendereco que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public TendEndereco fromDTO(EnderecoDTO objDto) {
		return new TendEndereco(objDto.getCep(), objDto.getLogradouro(), objDto.getEndereco(), objDto.getEnderecoCompleto());
	}
	

}
