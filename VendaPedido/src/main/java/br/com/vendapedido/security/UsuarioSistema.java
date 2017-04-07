package br.com.vendapedido.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.vendapedido.model.Usuario;

public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;

	private Usuario usuario; //Armazenar o objeto usuario "User" do Spring security
	
	public UsuarioSistema(Usuario usuario   , Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		
		this.setUsuario(usuario);
	}
	
//***********GET E SET*********************************************************************************//
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
