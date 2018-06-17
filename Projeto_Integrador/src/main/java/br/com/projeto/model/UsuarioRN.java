package br.com.projeto.model;

import java.util.List;

public class UsuarioRN {
	
	public Boolean logar(Usuario usuario){
		if (new UsuarioDAO().selectByloginAndSenha(usuario) != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	public List<Usuario> listarTodos(){
		return new UsuarioDAO().selectAll();
	}
	
	public void gravar(Usuario usuario){
		new UsuarioDAO().insert(usuario);
	}
}
