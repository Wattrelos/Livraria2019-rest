package fatec.layer11.services;

import org.springframework.security.core.context.SecurityContextHolder;

import fatec.security.UserSpringSecurity;


public class UserService {
	
	public static UserSpringSecurity authenticated() {
		try {
			// Retorna o usuário que está logado no sistema.
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			// Se não houver usuário logado ou qualquer outra exceção, retorna null.
			return null;
		}
	}
}
