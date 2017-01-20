package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.Set;

public interface AssessmentService {

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
    Assessment getById(int id);

    /**
     * Returns HashSet of Assessments
     *  with WeekId
     * @param id
     * @return List of Assessments
     */
    Set<Assessment> getByWeekId(int id);

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
