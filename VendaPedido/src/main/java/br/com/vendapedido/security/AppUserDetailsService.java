package br.com.vendapedido.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.vendapedido.model.Grupo;
import br.com.vendapedido.model.Usuario;
import br.com.vendapedido.repository.Usuarios;
import br.com.vendapedido.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService{

//A INTERFACE 	UserDetailsService é do Spring security, ela vai fornecer os detalhes dos usuarios  
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//CDIServiceLocator -> vai buscar o bean de usuarios
		Usuarios usuarios =CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(email);
		
		UsuarioSistema user = null;
		
		if(usuario != null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
	List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
	
	//toUpperCase() -> Pega os nomes dos grupos que os usuarios pertencem
	for (Grupo grupo : usuario.getGrupos()) {
		authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
	}
	
	return authorities;
}

}
