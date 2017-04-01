package com.revature.caliber.security.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.I_AM_A_TEAPOT, reason="No users exist in either Salesforce or the database")
public class EmptyTrainerDatabaseException extends RuntimeException{
	private static final long serialVersionUID = 4819516536685545348L;
}
