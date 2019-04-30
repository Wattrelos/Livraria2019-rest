package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fatec.domain.Cliente;
import fatec.domain.Pedido;
import fatec.domain.enums.Perfil;
import fatec.layer10.repositories.ClienteRepository;
import fatec.layer10.repositories.PedidoRepository;
import fatec.layer11.services.exceptions.AuthorizationException;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.ClienteDTO;
import fatec.layer20.aplications.DataTransferObject.ClienteNewDTO;
import fatec.security.UserSpringSecurity;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder passEnc;
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	// CREATE ------------------------------------------------
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null); // Garantir a criação do objeto ao invés de merge.		
		
		cliente = repo.save(cliente); // Salvar e recuperar Cliente.		
		for(Pedido pedido : cliente.getPedido()) {
			pedido.setCliente(cliente);
			pedidoRepository.save(pedido);
		}
		return cliente;
	}
	
	// READ ------------------------------------------------
	public Cliente find(Integer id) {
		/*
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		// Restrição: O usuário só pode acessar ele mesmo. Administrador pode acessar todos.
		// Verifica se não há usuáriologado, se o usuário tem perfil de administrador e se o ID do usuário é o mesmo do perfil acessado.
		if(userSpringSecurity == null || !userSpringSecurity.hasRole(Perfil.ADMIN) && !id.equals(userSpringSecurity.getId())) {
			throw new AuthorizationException("Acesso não permitido");			
		}
		// Se autenticado, então...		
		 * 
		 */
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Cliente.class.getName()));		
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente findByEmail(String email) {
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		// Restrição: O usuário só pode acessar ele mesmo. Administrador pode acessar todos.
		// Verifica se não há usuáriologado, se o usuário tem perfil de administrador e se o EMAIL do usuário é o mesmo do perfil acessado.
		if(userSpringSecurity == null || !userSpringSecurity.hasRole(Perfil.ADMIN) && !email.equals(userSpringSecurity.getUsername())) {
			throw new AuthorizationException("Acesso não permitido");			
		}
		// Se autenticado, então...
		Cliente obj = repo.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + userSpringSecurity.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	// UPDATE ------------------------------------------------
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setPedido(obj.getPedido());
		newObj.setEndereco(obj.getEndereco());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(), objDto.getCpf(), objDto.getEmail(),objDto.getDataNascimento(), objDto.getSexo(), objDto.getEndereco(),objDto.getPedido(),objDto.getCartaoCredito(),objDto.getDataCadastro() ,null, null);
		
	}
	public Cliente fromNewDto(ClienteNewDTO objNewDto) {		
		return new Cliente(null, objNewDto.getNome(), objNewDto.getCpf(), objNewDto.getEmail(), objNewDto.getDataNascimento(), objNewDto.getSexo(), objNewDto.getEndereco(),objNewDto.getPedido(),objNewDto.getCartaoCredito(), null, passEnc.encode(objNewDto.getSenha()), objNewDto.getPerfil());
	}
	

}
