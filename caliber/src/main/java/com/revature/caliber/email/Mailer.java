package com.revature.caliber.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Will Underwood
 *
 */
@Component
public class Mailer extends TimerTask {
	
	private static final Logger logger = Logger.getLogger(Mailer.class);

	private static final String EMAIL_TEMPLATE_PATH = "";

	private String toEmail = "mscott@mailinator.com";
	private EmailAuthenticator authenticator;

	// Will be autowired later when we're 
	// ready to send emails to specific users.
	// For now it will be hard coded.
	//@Autowired
//	public void setToEmail(String toEmail) {
//		this.toEmail = toEmail;
//	}

//	@Autowired
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
			File emailTemplate = new File(EMAIL_TEMPLATE_PATH);
			InputStream email = new FileInputStream(emailTemplate);
			message.setContent(email, "text/html");
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			
		}
	}

}
