package br.com.intuiti.compreingressos.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

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

	public void sendMail() throws EmailException, NoSuchAlgorithmException, IOException {
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
	}
	
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

	public String createMailHTML(Usuario usuario, String link){
		return "<html xmlns='http://www.w3.org/1999/xhtml'>                                                                                                                                                                                                                                                                               "+
				" <head>                                                                                                                                                                                                                                                                                                                  "+
				"     <meta charset='utf-8'>                                                                                                                                                                                                                                                                                              "+
				"     <meta name='viewport' content='width=device-width'>                                                                                                                                                                                                                                                                 "+
				"     <meta http-equiv='X-UA-Compatible' content='IE=edge'>                                                                                                                                                                                                                                                               "+
				"     <meta name='x-apple-disable-message-reformatting'>                                                                                                                                                                                                                                                                  "+
				"     <title>COMPREINGRESSOS.com</title>                                                                                                                                                                                                                                                                                  "+
				"     <style>                                                                                                                                                                                                                                                                                                             "+
				"         html,                                                                                                                                                                                                                                                                                                           "+
				"         body {                                                                                                                                                                                                                                                                                                          "+
				"             margin: 0 auto !important;                                                                                                                                                                                                                                                                                  "+
				"             padding: 0 !important;                                                                                                                                                                                                                                                                                      "+
				"             height: 100% !important;                                                                                                                                                                                                                                                                                    "+
				"             width: 100% !important;                                                                                                                                                                                                                                                                                     "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         * {                                                                                                                                                                                                                                                                                                             "+
				"             -ms-text-size-adjust: 100%;                                                                                                                                                                                                                                                                                 "+
				"             -webkit-text-size-adjust: 100%;                                                                                                                                                                                                                                                                             "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         div[style*='margin: 16px 0'] {                                                                                                                                                                                                                                                                                  "+
				"             margin:0 !important;                                                                                                                                                                                                                                                                                        "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         table,                                                                                                                                                                                                                                                                                                          "+
				"         td {                                                                                                                                                                                                                                                                                                            "+
				"             mso-table-lspace: 0pt !important;                                                                                                                                                                                                                                                                           "+
				"             mso-table-rspace: 0pt !important;                                                                                                                                                                                                                                                                           "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         table {                                                                                                                                                                                                                                                                                                         "+
				"             border-spacing: 0 !important;                                                                                                                                                                                                                                                                               "+
				"             border-collapse: collapse !important;                                                                                                                                                                                                                                                                       "+
				"             table-layout: fixed !important;                                                                                                                                                                                                                                                                             "+
				"             margin: 0 auto !important;                                                                                                                                                                                                                                                                                  "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         table table table {                                                                                                                                                                                                                                                                                             "+
				"             table-layout: auto;                                                                                                                                                                                                                                                                                         "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         img {                                                                                                                                                                                                                                                                                                           "+
				"             -ms-interpolation-mode:bicubic;                                                                                                                                                                                                                                                                             "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         .mobile-link--footer a,                                                                                                                                                                                                                                                                                         "+
				"         a[x-apple-data-detectors] {                                                                                                                                                                                                                                                                                     "+
				"             color:inherit !important;                                                                                                                                                                                                                                                                                   "+
				"             text-decoration: underline !important;                                                                                                                                                                                                                                                                      "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"                                                                                                                                                                                                                                                                                                                         "+
				"                                                                                                                                                                                                                                                                                                                         "+
				"         .button-link {                                                                                                                                                                                                                                                                                                  "+
				"             text-decoration: none !important;                                                                                                                                                                                                                                                                           "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         .button-td,                                                                                                                                                                                                                                                                                                     "+
				"         .button-a {                                                                                                                                                                                                                                                                                                     "+
				"             transition: all 100ms ease-in;                                                                                                                                                                                                                                                                              "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         .button-td:hover,                                                                                                                                                                                                                                                                                               "+
				"         .button-a:hover {                                                                                                                                                                                                                                                                                               "+
				"             background: #555555 !important;                                                                                                                                                                                                                                                                             "+
				"             border-color: #555555 !important;                                                                                                                                                                                                                                                                           "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"         @media screen and (max-width: 480px) {                                                                                                                                                                                                                                                                          "+
				"             .fluid {                                                                                                                                                                                                                                                                                                    "+
				"                 width: 100% !important;                                                                                                                                                                                                                                                                                 "+
				"                 max-width: 100% !important;                                                                                                                                                                                                                                                                             "+
				"                 height: auto !important;                                                                                                                                                                                                                                                                                "+
				"                 margin-left: auto !important;                                                                                                                                                                                                                                                                           "+
				"                 margin-right: auto !important;                                                                                                                                                                                                                                                                          "+
				"             }                                                                                                                                                                                                                                                                                                           "+
				"             .stack-column,                                                                                                                                                                                                                                                                                              "+
				"             .stack-column-center {                                                                                                                                                                                                                                                                                      "+
				"                 display: block !important;                                                                                                                                                                                                                                                                              "+
				"                 width: 100% !important;                                                                                                                                                                                                                                                                                 "+
				"                 max-width: 100% !important;                                                                                                                                                                                                                                                                             "+
				"                 direction: ltr !important;                                                                                                                                                                                                                                                                              "+
				"             }                                                                                                                                                                                                                                                                                                           "+
				"             .stack-column-center {                                                                                                                                                                                                                                                                                      "+
				"                 text-align: center !important;                                                                                                                                                                                                                                                                          "+
				"             }                                                                                                                                                                                                                                                                                                           "+
				"             .center-on-narrow {                                                                                                                                                                                                                                                                                         "+
				"                 text-align: center !important;                                                                                                                                                                                                                                                                          "+
				"                 display: block !important;                                                                                                                                                                                                                                                                              "+
				"                 margin-left: auto !important;                                                                                                                                                                                                                                                                           "+
				"                 margin-right: auto !important;                                                                                                                                                                                                                                                                          "+
				"                 float: none !important;                                                                                                                                                                                                                                                                                 "+
				"             }                                                                                                                                                                                                                                                                                                           "+
				"             table.center-on-narrow {                                                                                                                                                                                                                                                                                    "+
				"                 display: inline-block !important;                                                                                                                                                                                                                                                                       "+
				"             }                                                                                                                                                                                                                                                                                                           "+
				"                                                                                                                                                                                                                                                                                                                         "+
				"         }                                                                                                                                                                                                                                                                                                               "+
				"     </style>                                                                                                                                                                                                                                                                                                            "+
				" </head>                                                                                                                                                                                                                                                                                                                 "+
				" <body width='100%' bgcolor='#7F0014' style='margin: 0; mso-line-height-rule: exactly;'>                                                                                                                                                                                                                                 "+
				"     <center style='width: 100%; background: #7F0014;'>                                                                                                                                                                                                                                                                  "+
				"         <div style='display:none;font-size:1px;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden;mso-hide:all;font-family: sans-serif;'>                                                                                                                                                           "+
				"             Redefinição de Senha                                                                                                                                                                                                                             "+
				"         </div>                                                                                                                                                                                                                                                                                                          "+
				"         <div style='max-width: 680px; margin: auto;'>                                                                                                                                                                                                                                                                   "+
				"             <table role='presentation' cellspacing='0' cellpadding='0' border='0' align='center' width='100%' style='max-width: 680px;'>                                                                                                                                                                                "+
				"                 <tr>                                                                                                                                                                                                                                                                                                    "+
				"                     <td style='padding: 20px 0; text-align: center'>                                                                                                                                                                                                                                                    "+
				"                         <img src='http://200.155.9.201:8080/compreingressos-portal/faces/javax.faces.resource/images/menu_logo.png?ln=rio-layout' width='100' height='50' alt='alt_text' border='0' style='height: auto; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;'>                 "+
				"                     </td>                                                                                                                                                                                                                                                                                               "+
				"                 </tr>                                                                                                                                                                                                                                                                                                   "+
				"             </table>                                                                                                                                                                                                                                                                                                    "+
				"             <table role='presentation' cellspacing='0' cellpadding='0' border='0' align='center' width='100%' style='max-width: 680px;'>                                                                                                                                                                                "+
				"                 <tr>                                                                                                                                                                                                                                                                                                    "+
				"                     <td bgcolor='#ffffff'>                                                                                                                                                                                                                                                                              "+
				"                         <table role='presentation' cellspacing='0' cellpadding='0' border='0' width='100%'>                                                                                                                                                                                                             "+
				"                             <tr>                                                                                                                                                                                                                                                                                        "+
				"                                 <td style='padding: 40px; text-align: center; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;'>                                                                                                                                                            "+
				"                                     Olá <b>"+ usuario.getDsNome()+"</b> <br />                                                                                                                                                                                                                                                            "+
				"                                     Você nos enviou uma solicitação para redefinição de senha <br />                                                                                                                                                                                                                    "+
				"                                     Para redefinir sua senha, basta você clicar no botão abaixo e informar sua nova senha                                                                                                                                                                                               "+
				"                                     <br><br>                                                                                                                                                                                                                                                                            "+
				"                                     <table role='presentation' cellspacing='0' cellpadding='0' border='0' align='center' style='margin: auto'>                                                                                                                                                                          "+
				"                                         <tr>                                                                                                                                                                                                                                                                            "+
				"                                             <td style='border-radius: 3px; background: #7F0014; text-align: center;' class='button-td'>                                                                                                                                                                                 "+
				"                                                 <a href='"+ link +"' style='background: #222222; border: 15px solid #222222; font-family: sans-serif; font-size: 13px; line-height: 1.1; text-align: center; text-decoration: none; display: block; border-radius: 3px; font-weight: bold;' class='button-a'>"+
				"                                                     <span style='color:#ffffff;' class='button-link'>&nbsp;&nbsp;&nbsp;&nbsp;REDEFINIR SENHA&nbsp;&nbsp;&nbsp;&nbsp;</span>                                                                                                                                             "+
				"                                                 </a>                                                                                                                                                                                                                                                                    "+
				"                                             </td>                                                                                                                                                                                                                                                                       "+
				"                                         </tr>                                                                                                                                                                                                                                                                           "+
				"                                     </table>                                                                                                                                                                                                                                                                            "+
				"                                     <br />                                                                                                                                                                                                                                                                              "+
				"                                     Caso você não seja o usuario <b>"+ usuario.getCdLogin() +"</b>, ignore esse e-mail                                                                                                                                                                                                                              "+
				"                                 </td>                                                                                                                                                                                                                                                                                   "+
				"                             </tr>                                                                                                                                                                                                                                                                                       "+
				"                         </table>                                                                                                                                                                                                                                                                                        "+
				"                     </td>                                                                                                                                                                                                                                                                                               "+
				"                 </tr>                                                                                                                                                                                                                                                                                                   "+
				"             </table>                                                                                                                                                                                                                                                                                                    "+
				"             <table role='presentation' cellspacing='0' cellpadding='0' border='0' align='center' width='100%' style='max-width: 680px;'>                                                                                                                                                                                "+
				"                 <tr>                                                                                                                                                                                                                                                                                                    "+
				"                     <td style='padding: 40px 10px;width: 100%;font-size: 12px; font-weight: bold; font-family: sans-serif; line-height:18px; text-align: center; color: white;'>                                                                                                                                        "+
				"                         <webversion style='color:white; '>Intuiti Soluções Técnologicas</webversion>                                                                                                                                                                                                                    "+
				"                         <br><br>                                                                                                                                                                                                                                                                                        "+
				"                         COMPREINGRESSOS<br><span class='mobile-link--footer'>suporte@compreingressos.com</span><br><span class='mobile-link--footer'>(11)2122-4033 </span>                                                                                                                                              "+
				"                         <br><br>                                                                                                                                                                                                                                                                                        "+
				"                     </td>                                                                                                                                                                                                                                                                                               "+
				"                 </tr>                                                                                                                                                                                                                                                                                                   "+
				"             </table>                                                                                                                                                                                                                                                                                                    "+
				"         </div>                                                                                                                                                                                                                                                                                                          "+
				"     </center>                                                                                                                                                                                                                                                                                                           "+
				" </body>                                                                                                                                                                                                                                                                                                                 "+
				" </html>";                                                                                                                                                                                                                                                                                                               
	}               
	
	
}                                                                                                                                                                                                                                                                                                                                         
