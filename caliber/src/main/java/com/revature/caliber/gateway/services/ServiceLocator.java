package com.revature.caliber.gateway.services;

public interface ServiceLocator {

	/**
	 * Return convenience object for interaction with
	 * the training module. Includes REST end points
	 * and delegates service calls.
	 * 
	 * @return
	 */
	public TrainingService getTrainingService();
	//TODO public AssessmentService getAssessmentService();
	//TODO public ReportService getReportService();
	//TODO public SalesforceService getSalesforceService();
	
}
