package com.revature.caliber.assessments.data;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.Set;

public interface AssessmentDAO {

//    Get
    /**
     * Returns HashSet of all Assessments
     * @return List of Assessments
     */
    Set<Assessment> getAll();

    /**
     * Return Assessment
     *  with AssessmentId
     * @return Assessment
     */
    Assessment getById(long id);

    /**
     * Returns HashSet of Assessments
     *  with TrainerId
     * @param trainerId
     * @return List of Assessments
     */
    /*   
     * 	TODO reconsider how to approach this implementation.
     * 		 data resides in another service, so you cannot query this way
    Set<Assessment> getByTrainerId(long trainerId);
    */

    /**
     * Returns HashSet of Assessments
     *  with WeekId
     * @param id
     * @return List of Assessments
     */
    Set<Assessment> getByWeekId(long id);

    /**
     * Returns HashSet of Assessments
     *  with BatchId
     * @param id
     * @return List of Assessments
     */
    Set<Assessment> getByBatchId(int id);

//    Create

    /**
     * Inserts Assessment
     * @param assessment
     */
    void insert(Assessment assessment);

//    Update

    /**
     * Updates Assessment
     * @param assessment
     */
    void update(Assessment assessment);


//    Delete

    /**
     * Deletes Assessment
     * @param assessment
     */
    void delete(Assessment assessment);
}
