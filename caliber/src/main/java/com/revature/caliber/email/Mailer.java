package com.revature.caliber.email;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Will Underwood
 *
 */
@Component
public class Mailer extends TimerTask {

	private String toEmail = "mscott@mailinator.com";
	private EmailAuthenticator authenticator;

	//@Autowired
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	@Autowired
	public void setAuthenticator(EmailAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	@Override
	public void run() {
		this.send();
	}

	public void send() {
		Properties properties = this.setProperties();
		Session session = this.getSession(properties);
		this.sendEmail(session);
	}

	private Properties setProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "587");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		return properties;
	}

	private Session getSession(Properties properties) {
		return Session.getDefaultInstance(properties, this.authenticator);
	}

	private void sendEmail(Session session) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject("Test");
			message.setText("This is a test");
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}

}
