package com.revature.caliber.assessments.data;

import java.util.List;
import java.util.Set;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.beans.Grade;

import java.util.Set;
import java.util.List;
import com.revature.caliber.assessments.beans.TrainerNote;


/**
 * Facade interface for the data tier.
 */
public interface Facade {

//    Assessment

    //    Get
    /**
     * Returns HashSet of all Assessments
     * @return Set of Assessments
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
     * @return Set of Assessments
     */
    Set<Assessment> getAssessmentsByTrainerId(int id);

    /**
     * Returns HashSet of Assessments
     *  with WeekId
     * @param id
     * @return Set of Assessments
     */
    Set<Assessment> getAssessmentsByWeekId(int id);

    /**
     * Returns HashSet of Assessments
     *  with BatchId
     * @param id
     * @return Set of Assessments
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
    
    //    Batch Note
    void makeBatchNote(int batchId, int weekId);
	
	BatchNote getWeeklyBatchNote(int batchId, int weekId);	
	 
	List<BatchNote> allBatchNotesInWeek(int weekId);
	
	
	//	Trainer Note
	void makeTrainerNote(int trainerId);
	
	List<TrainerNote> listTrainerNotes(int trainerId);
	
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);
//    Batch
  
// Grade
    
    //Gets
    
    /**
     * Returns a list of all grade entries
     */
    List<Grade> getAllGrades();
    
    /**
     * Returns a grade object given a specific gradeId
     */
    Grade getGradeByGradeId(int gradeId);
    
    /**
     * Returns a list of grades of a specific trainee based on traineeId as an input
     * @param traineeId
     */
	List<Grade> getGradesByTraineeId(int traineeId);
	
	/**
	 * Returns a list of grades of a specific assessment based on assessmentId as an input
	 * @param assessmentId
	 */
	List<Grade> getGradesByAssesessment(int assessmentId);
	
	//Insert
	/**
	 * Inserts a new Grade into database
	 */
    void insertGrade(Grade grade);
    
	//Delete
    /**
     * Deletes a grade
     */
    void deleteGrade(Grade grade);
    
	//Update
    /**
     * Updates a grade
     */
    void updateGrade(Grade grade);

}
