package com.revature.caliber.assessments.data;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.beans.TrainerNote;

import java.util.HashSet;
import java.util.List;

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
    HashSet<Assessment> getAllAssessments();

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
    HashSet<Assessment> getAssessmentsByTrainerId(int id);

    /**
     * Returns HashSet of Assessments
     *  with WeekId
     * @param id
     * @return List of Assessments
     */
    HashSet<Assessment> getAssessmentsByWeekId(int id);

    /**
     * Returns HashSet of Assessments
     *  with BatchId
     * @param id
     * @return List of Assessments
     */
    HashSet<Assessment> getAssessmentsByBatchId(int id);

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
}
