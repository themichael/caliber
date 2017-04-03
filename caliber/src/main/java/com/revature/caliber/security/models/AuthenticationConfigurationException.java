package com.revature.caliber.security.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.I_AM_A_TEAPOT, reason="Error in authentication system configuration. Contact technology support.")
public class AuthenticationConfigurationException extends RuntimeException{
	private static final long serialVersionUID = 4073491812380749518L;
}