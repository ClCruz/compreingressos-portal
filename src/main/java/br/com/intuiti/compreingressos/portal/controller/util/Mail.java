package br.com.intuiti.compreingressos.portal.controller.util;

import java.util.logging.Level;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.logging.Logger;

@Stateless
public class Mail {

	@Resource(name ="java:jboss/mail/Default")
	private Session session;
	
	public void send(String addresses, String topic, String textMessage) {
		try{
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			message.setSubject(topic);
			message.setText(textMessage);
			message.setContent(textMessage, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
		}
	}
}
