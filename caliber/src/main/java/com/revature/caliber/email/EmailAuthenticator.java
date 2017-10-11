package com.revature.caliber.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
	
	private String fromEmail;
	private String fromPass;
	
	public EmailAuthenticator(String fromEmail, String fromPass) {
		this.fromEmail = fromEmail;
		this.fromPass = fromPass;
	}
	
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.fromEmail, this.fromPass);
	}

}
