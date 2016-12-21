package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.intuiti.compreingressos.portal.bean.UsuarioFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean (name ="alterarDadosUsuarioController")
@ViewScoped
public class AlterarDadosUsuarioController implements Serializable {

	@EJB
	private br.com.intuiti.compreingressos.portal.bean.UsuarioFacade ejbFacade;
	private Usuario usuario;
	
	public AlterarDadosUsuarioController() {
		usuario = new Usuario();
		usuario = JsfUtil.getLogin();
	}
	
	public void salvarEdicao() {
		getFacade().edit(usuario);
		JsfUtil.addSuccessMessage("Dados atualizados com sucesso.");
	}

	public UsuarioFacade getFacade() {
		return ejbFacade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
