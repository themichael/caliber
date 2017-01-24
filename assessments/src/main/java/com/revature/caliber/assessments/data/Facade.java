package com.revature.caliber.assessments.data;


import java.util.List;
import java.util.Set;

import com.revature.caliber.assessments.beans.*;
import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.beans.TrainerNote;


import java.util.List;
import java.util.Set;


/**
 * Facade interface for the data tier.
 */
public interface Facade {

//    Assessment
    //    Get
    /**
     * Returns a Set of all Assessments
     * @return a Set of Assessments
     */
    Set<Assessment> getAllAssessments();

    /**
     * Return Assessment with AssessmentId
     * @return an Assessment
     */
    Assessment getById(int id);

    /**
     * Returns HashSet of Assessments with WeekId
     * @param id the Week ID
     * @return a Set of Assessments
     */
    Set<Assessment> getAssessmentsByWeekId(int id);

    //    Create
    /**
     * Inserts Assessment
     * @param assessment an Assessment to be inserted
     */
    void insertAssessment(Assessment assessment);

    //    Update
    /**
     * Updates Assessment
     * @param assessment an Assessment to be updated
     */
    void updateAssessment(Assessment assessment);

    //    Delete
    /**
     * Deletes Assessment
     * @param assessment and Assessment to delete
     */
    void deleteAssessment(Assessment assessment);

    
//    Batch Note
    void makeBatchNote(BatchNote batchNote);

    BatchNote getWeeklyBatchNote(int batchId, int weekId);

    List<BatchNote> allBatchNotesInWeek(int weekId);
    
    void updateBatchNote(BatchNote batchNote);
    

//    Grade
    //Gets
    /**
     * Returns a list of all grade entries
     */
    List<Grade> getAllGrades();

    /**
     * Returns a grade object given a specific gradeId
     */
    Grade getGradeByGradeId(long gradeId);

    /**
     * Returns a list of grades of a specific trainee based on traineeId as an input
     *
     * @param traineeId
     */
    List<Grade> getGradesByTraineeId(int traineeId);

    /**
     * Returns a list of grades of a specific assessment based on assessmentId as an input
     *
     * @param assessmentId
     */
    List<Grade> getGradesByAssesessment(long assessmentId);

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


//	Trainer Note
	void makeTrainerNote(TrainerNote trainerNote);
	
	Set<TrainerNote> getTrainerNoteByTrainerId(int trainerId);
	
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);

	
	

    //QCNote
    void createQCNote(QCNote note);
    QCNote getQCNoteById(Integer QCNoteId);
    QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId);
    List<QCNote> getQCNotesByTrainee(Integer traineeId);
    List<QCNote> getQCNotesByWeek(Integer weekId);
    void updateQCNote(QCNote note);
    void deleteQCNote(QCNote note);
    //QCNote end
}
