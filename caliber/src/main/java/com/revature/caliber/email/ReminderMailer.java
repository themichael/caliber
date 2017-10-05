package com.revature.caliber.email;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;

public class ReminderMailer extends TimerTask {

	@Value("#{systemEnvironment['DEV_CALIBER_EMAIL']}")
	private static String from;
	@Value("#{systemEnvironment['DEV_CALIBER_PASS']}")
	private static String pass;
	private String to = "mscott@mailinator.com";

	@Override
	public void run() {
		this.send();
	}

	private void send() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "587");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ReminderMailer.from, ReminderMailer.pass);
			}
		});

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
