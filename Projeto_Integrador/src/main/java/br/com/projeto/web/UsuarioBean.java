package br.com.projeto.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.projeto.model.Usuario;
import br.com.projeto.model.UsuarioRN;

@ManagedBean(name = "userBean")
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	
	
	
	public String actionVoltarMenu() {
		return "home";
	}

	public String actionLogar() {
		if (new UsuarioRN().logar(usuario)) {
			return "home";
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", ""));

		return null;
	}
	
	
	

	public List<Usuario> getListagemUsuarios() {
		return new UsuarioRN().listarTodos();
	}

	public String novo() {
		this.usuario = new Usuario();
		return "form_usuario";
	}

	public String gravar() {

		if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não conferem", ""));
			return null;
		}

		if (usuario.getSenha().length() < 3) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha deve ser maior que Três caracteres", ""));
			return null;
		}

		new UsuarioRN().gravar(usuario);
		return "home";

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
