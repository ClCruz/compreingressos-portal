package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.intuiti.compreingressos.portal.bean.UsuarioFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "alterarSenhaController")
@ViewScoped
public class AlterarSenhaController implements Serializable{

	@EJB
	private br.com.intuiti.compreingressos.portal.bean.UsuarioFacade ejbFacade;
	private Usuario usuario;
	private String senhaAtual;
	private String novaSenha;
	
	public AlterarSenhaController() {
		usuario = new Usuario();
		usuario = JsfUtil.getLogin();
	}
	
	public void alterarSenha() throws NoSuchAlgorithmException {
		if(senhaAtual.equals(usuario.getCdPww())){
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(novaSenha.getBytes(), 0, novaSenha.length());
			usuario.setCdPww(novaSenha);
			getFacade().edit(usuario);
			JsfUtil.addSuccessMessage("Senha alterada com sucesso.");
		} else {
			JsfUtil.addErrorMessage("Senha atual inválida.");
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	
	public void setSenhaAtual(String senhaAtual) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senhaAtual.getBytes(), 0, senhaAtual.length());
		this.senhaAtual = new BigInteger(1, m.digest()).toString(16);
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}
	
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public UsuarioFacade getFacade() {
		return ejbFacade;
	}

}
