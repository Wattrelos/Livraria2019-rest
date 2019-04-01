package fatec.layer11.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fatec.domain.Usuario;
import fatec.domain.enums.Perfil;
import fatec.layer10.repositories.UsuarioRepository;
import fatec.layer11.services.exceptions.AuthorizationException;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.UsuarioDTO;
import fatec.security.UserSpringSecurity;

@Service
public class UsuarioService extends AbstractJdbcDAO{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioRepository repo;


	// CREATE ------------------------------------------------
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// READ ------------------------------------------------
	public Usuario find(Integer id) {
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		// Restrição: O usuário só pode acessar ele mesmo. Administrador pode acessar todos.
		// Verifica se não há usuáriologado, se o usuário tem perfil de administrador e se o ID do usuário é o mesmo do perfil acessado.
		if(userSpringSecurity == null || !userSpringSecurity.hasRole(Perfil.ADMIN) && !id.equals(userSpringSecurity.getId())) {
			throw new AuthorizationException("Acesso não permitido");			
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Usuario.class.getName()));		
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setSenha(obj.getSenha());
		
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma usuario que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Usuario fromNewDTO(UsuarioDTO objDto) {
		return new Usuario(null, objDto.getEmail(), null, passwordEncoder.encode(objDto.getSenha()), objDto.getPerfil());
	}
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getEmail(), objDto.getDataCadastro(), null, null);
	}
	
}
