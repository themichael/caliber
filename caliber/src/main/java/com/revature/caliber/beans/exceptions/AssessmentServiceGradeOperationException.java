package com.revature.caliber.beans.exceptions;

/**
 * Custom exception for Assessment Service Grade functionality
 */
public class AssessmentServiceGradeOperationException extends RuntimeException{

	public AssessmentServiceGradeOperationException() {super(); }
	public AssessmentServiceGradeOperationException(String message){super(message);}
}
