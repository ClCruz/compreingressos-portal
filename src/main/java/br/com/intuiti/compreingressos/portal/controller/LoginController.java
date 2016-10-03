package br.com.intuiti.compreingressos.portal.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController {
	
	private Usuario usuario;
	
	public LoginController() {
	}
	
	@PostConstruct
	public void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
	}
	
	public String logout(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("usuario", null);
		session.invalidate();
		return "/faces/login.xhtml";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
