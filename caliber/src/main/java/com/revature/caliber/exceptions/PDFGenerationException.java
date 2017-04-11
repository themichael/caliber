package com.revature.caliber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE, reason="Failed to generate PDF report.")
public class PDFGenerationException extends RuntimeException{
	private static final long serialVersionUID = 4073491812380749518L;
}
