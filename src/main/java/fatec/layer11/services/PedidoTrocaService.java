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

import fatec.domain.PedidoTroca;
import fatec.layer10.repositories.PedidoTrocaRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.PedidoTrocaDTO;

@Service
public class PedidoTrocaService {
	
	@Autowired
	private PedidoTrocaRepository repo;
	
	// CREATE------------------------------------------------------
	@Transactional
	public PedidoTroca insert(PedidoTroca obj) {
			
		obj.setId(null);  // Garantir a criação do objeto ao invés de merge.
		obj = repo.save(obj); // Salvar e recuperar Cliente.		
		return obj;
	}
	
	// READ ------------------------------------------------
	public PedidoTroca find(Integer id) {
		Optional<PedidoTroca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ PedidoTroca.class.getName()));		
	}
	
	public List<PedidoTroca> findAll() {
		return repo.findAll();
	}

	public Page<PedidoTroca> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public PedidoTroca update(PedidoTroca obj) {
		PedidoTroca newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(PedidoTroca newObj, PedidoTroca obj) {
		newObj.setId(obj.getId());
		newObj.setObservacao(obj.getObservacao());
		newObj.setDataCadastro(obj.getDataCadastro());
		newObj.setCliente(obj.getCliente());
		newObj.setPedido(obj.getPedido());
		newObj.setEstatus(obj.getEstatus());
		
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma pedidoTroca que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public PedidoTroca fromDTO(PedidoTrocaDTO objDto) {
		return new PedidoTroca(objDto.getId(), objDto.getObservacao(), objDto.getDataCadastro(), objDto.getEstatus(), objDto.getCliente(), objDto.getPedido());
	}
	

}
