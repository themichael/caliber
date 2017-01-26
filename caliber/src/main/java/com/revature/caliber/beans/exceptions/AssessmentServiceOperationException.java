package com.revature.caliber.beans.exceptions;

/**
 * Custom exception for Assessment Service Grade functionality
 */
public class AssessmentServiceOperationException extends RuntimeException{

	public AssessmentServiceOperationException() {super(); }
	public AssessmentServiceOperationException(String message){super(message);}
}
