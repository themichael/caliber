package com.revature.caliber.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticator extends Authenticator {
	
	private String fromEmail;
	private String fromPass;
	
	@Autowired
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	@Autowired
	public void setFromPass(String fromPass) {
		this.fromPass = fromPass;
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.fromEmail, this.fromPass);
	}

}
