package fatec.layer11.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fatec.domain.Usuario;
import fatec.layer10.repositories.UsuarioRepository;
import fatec.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImplement implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Usuario usuario = repository.findByEmail(email);
		System.out.println("UsarDetailService: " + usuario);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
		
	}

}
