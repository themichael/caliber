package com.revature.caliber.beans.exceptions;

/**
 * Custom exception for Training Service Trainee functionality
 */
public class TrainingServiceTraineeOperationException extends RuntimeException{
    public TrainingServiceTraineeOperationException() { super(); }
    public TrainingServiceTraineeOperationException(String message) { super(message); }
}
