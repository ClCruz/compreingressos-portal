package br.com.intuiti.compreingressos.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.com.intuiti.compreingressos.portal.bean.EsqueciSenhaFacade;
import br.com.intuiti.compreingressos.portal.bean.UsuarioFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.model.EsqueciSenha;
import br.com.intuiti.compreingressos.portal.model.Usuario;

@ManagedBean(name = "esqueciSenhaController")
@ViewScoped
public class EsqueciSenhaController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private UsuarioFacade ejbFacade;
	@EJB
    private br.com.intuiti.compreingressos.portal.bean.EsqueciSenhaFacade ejbEFacade;
	@EJB
	private br.com.intuiti.compreingressos.portal.controller.util.Mail mail;
	private EsqueciSenha esqueciSenha;
	private String usuario;
	private Integer codigoUsuario;
	private String senha;
	private String senha2;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public UsuarioFacade getFacade() {
		return ejbFacade;
	}
	
	public EsqueciSenhaFacade getEFacade() {
		return ejbEFacade;
	}

	public void enviarEmail() throws EmailException, NoSuchAlgorithmException, IOException {
		try{
			Usuario user = getFacade().findUsuario(usuario);
			StringBuilder builder = new StringBuilder();
			MessageDigest m = MessageDigest.getInstance("MD5");
			String code = user.getDsEmail() + "" + new Date().getTime();
			m.update(code.getBytes(), 0, code.length());
			String codigoMD5 = new BigInteger(1, m.digest()).toString(16);
			gravaDados(codigoMD5, user.getDsEmail(), user.getIdUsuario());
			String link = "http://localhost:8080/compreingressos-portal/faces/redefinirSenha.xhtml?code=" + codigoMD5;
			String content = readFile("C:/Projetos/compreingressos-portal/src/main/resources/emailEsqueciSenha.xhtml", StandardCharsets.UTF_8);
			content = content.replace("{usuario}", user.getDsNome());
			content = content.replace("{login}", user.getCdLogin());
			content = content.replace("{link}", link);
			builder.append(content);
			mail.send(user.getDsEmail(), "Redefinição de Senha", builder.toString());
			JsfUtil.addSuccessMessage("E-mail enviado com sucesso, verifique sua caixa de mensagem");
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch(EJBException e){
			JsfUtil.addErrorMessage("Login inválido");
		}
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	/*public void sendMail() throws EmailException, NoSuchAlgorithmException, IOException {
		Usuario user = getFacade().findUsuario(usuario);
		if (user != null) {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com"); // email.setHostName("smtp.gmail.com");
			
			email.setSmtpPort(587);
			email.addTo(user.getDsEmail(), user.getDsNome());
			email.setFrom("emailtesteintuiti@gmail.com", "COMPREINGRESSOS");
			email.setSubject("Redefinição de Senha");
			
			StringBuilder builder = new StringBuilder();
			MessageDigest m = MessageDigest.getInstance("MD5");
			String code = user.getDsEmail()+""+new Date().getTime();
			m.update(code.getBytes(),0,code.length());
			String codigoMD5 = new BigInteger(1,m.digest()).toString(16);
			gravaDados(codigoMD5, user.getDsEmail(), user.getIdUsuario());
			String link = "http://localhost:8080/compreingressos-portal/faces/redefinirSenha.xhtml?code=" + codigoMD5;
			builder.append(createMailHTML(user, link));
			email.setHtmlMsg(builder.toString());
			email.setSSL(false);
			email.setTLS(true);
			email.setAuthentication("emailtesteintuiti@gmail.com", "padrao111");
			email.send();
			JsfUtil.addSuccessMessage("E-mail enviado com sucesso, verifique sua caixa de mensagem");
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			
		} else {
			JsfUtil.addErrorMessage(
					"Não foi encontrado nenhum usuário com esse login em nosso sitema. Informe um login válido");
		}
	}*/
	
	public void gravaDados(String codigo, String email, Integer idUsuario){
		esqueciSenha = new EsqueciSenha();
		esqueciSenha.setCdEsqueciSenha(codigo);
		esqueciSenha.setDsEmail(email);
		esqueciSenha.setInAtivo('1');
		esqueciSenha.setDtEsqueciSenha(new Date());
		esqueciSenha.setIdUsuario(idUsuario);
		getEFacade().edit(esqueciSenha);
	}
	
	public void alteraSenha() throws NoSuchAlgorithmException, IOException{
		if(senha.equals(senha2)){
			try{
				Usuario us = new Usuario();
				us = getFacade().find(codigoUsuario);
				us.setCdPww(senha);
				getFacade().edit(us);
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(senha != senha2) {
			JsfUtil.addErrorMessage("As senhas não correspondem.");
		}
	}
	public void redefinirSenha(){
		String codigoE = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("code");
		if(codigoE == null || codigoE == ""){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			EsqueciSenha objEsqueci = getEFacade().findCode(codigoE);
			if(objEsqueci.getInAtivo() == '1'){
				objEsqueci.setInAtivo('0');
				codigoUsuario = objEsqueci.getIdUsuario();
				getEFacade().edit(objEsqueci);
			} else {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	
	
	
}                                                                                                                                                                                                                                                                                                                                         
