package com.revature.caliber.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Authenticates the email account that sends the reminder emails.
 * @author Will Underwood
 *
 */
@Component
public class EmailAuthenticator extends Authenticator {

	@Value("#{systemEnvironment['DEV_CALIBER_EMAIL']}")
	private String fromEmail;
	@Value("#{systemEnvironment['DEV_CALIBER_PASS']}")
	private String fromPass;

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.fromEmail, this.fromPass);
	}

}
