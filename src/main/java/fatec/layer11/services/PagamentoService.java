
package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Pagamento;
import fatec.layer10.repositories.PagamentoRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.PagamentoDTO;

@Service
public class PagamentoService extends AbstractJdbcDAO{
	
	@Autowired
	private PagamentoRepository repo;


	// CREATE ------------------------------------------------
	@Transactional
	public Pagamento insert(Pagamento obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Pagamento find(Integer id) {
		Optional<Pagamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Pagamento.class.getName()));		
	}
	
	public List<Pagamento> findAll() {
		return repo.findAll();
	}

	public Page<Pagamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Pagamento update(Pagamento obj) {
		Pagamento newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Pagamento newObj, Pagamento obj) {
		newObj.setId(obj.getId());
		newObj.setPagamentoComCartao(obj.getPagamentoComCartao());
		newObj.setPagamentoComBoleto(obj.getPagamentoComBoleto());
		newObj.setPedido(obj.getPedido());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Pagamento que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Pagamento fromDTO(PagamentoDTO objDto) {
		return new Pagamento(objDto.getId(), objDto.getPagamentoComCartao(), objDto.getPagamentoComBoleto(), objDto.getPedido());
	}
	

}
