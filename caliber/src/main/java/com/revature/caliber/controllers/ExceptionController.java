package com.revature.caliber.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	
	private static final Logger log = Logger.getLogger(ExceptionController.class);

	/**
	 * TODO test me.
	 * Handles IllegalArgumentException thrown within the application. 
	 * @param request
	 * @param e
	 */
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public void illegalArgument(HttpServletRequest request, Exception e){
		log.info("[IP] " + request.getRemoteAddr() + " caused exception " + e.getClass());
	}
	
}
