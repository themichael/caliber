package com.revature.caliber.gateway.services;

public interface ServiceLocator {

    /**
     * Return convenience object for interaction with
     * the training module. Includes REST end points
     * and delegates service calls.
     *
     * @return A convenience object for interaction with
     * the training module.
     */
    TrainingService getTrainingService();

    AssessmentService getAssessmentService();
    //TODO public ReportService getReportService();
    //TODO public SalesforceService getSalesforceService();

}
