package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fatec.domain.Estoque;
import fatec.domain.Pedido;
import fatec.layer10.repositories.EstoqueRepository;
import fatec.layer10.repositories.PedidoRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.PedidoDTO;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	// CREATE------------------------------------------------------
	public Pedido insert(Pedido obj) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>PedidoService pedido.getCliente().getId : " + obj.getCliente().getId());
		System.out.println(">>>>>>>>>>>>>>>>>>>PedidoService PedidoHasLivroDto.obj.getCliente().getNome() : " + obj.getCliente().getNome());		
		
		
		obj.setId(null);  // Garantir a criação do objeto ao invés de merge.
		obj = repo.save(obj); // Salvar e recuperar Cliente.		
		for(Estoque estoque : obj.getEstoque()) {
			estoque.setPedido(obj); // Assina o Pedido em cada ItemPedido.
		}
		estoqueRepository.saveAll(obj.getEstoque());
		return obj;
	}
	
	// READ ------------------------------------------------
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Pedido.class.getName()));		
	}
	
	public List<Pedido> findAll() {
		return repo.findAll();
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Pedido update(Pedido obj) {
		Pedido newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Pedido newObj, Pedido obj) {
		newObj.setObservacao(obj.getObservacao());
		
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma pedido que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Pedido fromDTO(PedidoDTO objDto) {
		return new Pedido(objDto.getId(), objDto.getObservacao(), objDto.getDataCadastro(), objDto.getCliente(), objDto.getEstoque());
	}
	

}
