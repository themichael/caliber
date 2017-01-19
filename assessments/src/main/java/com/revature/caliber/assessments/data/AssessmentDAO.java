package com.revature.caliber.assessments.data;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.HashSet;

public interface AssessmentDAO {

//    Get
    /**
     * Returns HashSet of all Assessments
     * @return List of Assessments
     */
    HashSet<Assessment> getAll();

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
    HashSet<Assessment> getByTrainerId(int id);

    /**
     * Returns HashSet of Assessments
     *  with WeekId
     * @param id
     * @return List of Assessments
     */
    HashSet<Assessment> getByWeekId(int id);

    /**
     * Returns HashSet of Assessments
     *  with BatchId
     * @param id
     * @return List of Assessments
     */
    HashSet<Assessment> getByBatchId(int id);

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
