package com.revature.caliber.email;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author Will Underwood
 *
 */
public class Mailer extends TimerTask {

	//@Value("#{systemEnvironment['DEV_CALIBER_EMAIL']}")
	private static String from = System.getenv("DEV_CALIBER_EMAIL");
	//@Value("#{systemEnvironment['DEV_CALIBER_PASS']}")
	private static String pass = System.getenv("DEV_CALIBER_PASS");
	private String to = "mscott@mailinator.com";

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
		return Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Mailer.from, Mailer.pass);
			}
		});
	}

	private void sendEmail(Session session) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Test");
			message.setText("This is a test");
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}

}
