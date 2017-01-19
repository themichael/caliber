package com.revature.caliber.assessments.data;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.HashSet;
import java.util.Set;

/**
 * Facade interface for the data tier.
 */
public interface Facade {

//    Assessment

    //    Get
    /**
     * Returns HashSet of all Assessments
     * @return List of Assessments
     */
    Set<Assessment> getAllAssessments();

    /**
     * Return Assessment
     *  with AssessmentId
     * @return Assessment
     */
    Assessment getById(int id);

    /**
     * Returns HashSet of Assessments
     *  with TrainerId
     * @param id
     * @return List of Assessments
     */
    Set<Assessment> getAssessmentsByTrainerId(int id);

    /**
     * Returns HashSet of Assessments
     *  with WeekId
     * @param id
     * @return List of Assessments
     */
    Set<Assessment> getAssessmentsByWeekId(int id);

    /**
     * Returns HashSet of Assessments
     *  with BatchId
     * @param id
     * @return List of Assessments
     */
    Set<Assessment> getAssessmentsByBatchId(int id);

    //    Create

    /**
     * Inserts Assessment
     * @param assessment
     */
    void insertAssessment(Assessment assessment);

    //    Update

    /**
     * Updates Assessment
     * @param assessment
     */
    void updateAssessment(Assessment assessment);

    //    Delete

    /**
     * Deletes Assessment
     * @param assessment
     */
    void deleteAssessment(Assessment assessment);

//    Batch

}
