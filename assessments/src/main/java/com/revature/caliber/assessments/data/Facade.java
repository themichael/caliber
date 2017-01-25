package com.revature.caliber.assessments.data;

import com.revature.caliber.assessments.beans.*;

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
     *
     * @return a Set of Assessments
     */
    Set<Assessment> getAllAssessments();

    /**
     * Return Assessment with AssessmentId
     *
     * @return an Assessment
     */
    Assessment getAssessmentById(long id);

    /**
     * Returns HashSet of Assessments with WeekId
     *
     * @param id the Week ID
     * @return a Set of Assessments
     */
    Set<Assessment> getAssessmentsByWeekId(long id);

    //    Create

    /**
     * Inserts Assessment
     *
     * @param assessment an Assessment to be inserted
     */
    long insertAssessment(Assessment assessment);

    //    Update

    /**
     * Updates Assessment
     *
     * @param assessment an Assessment to be updated
     */
    void updateAssessment(Assessment assessment);

    //    Delete

    /**
     * Deletes Assessment
     *
     * @param assessment and Assessment to delete
     */
    void deleteAssessment(Assessment assessment);

//    Category
    /**
     * Returns Set of all Categories
     * @return Set of all Categories
     */
    Set<Category> getAllCategories();

    /**
     * Returns Category
     *  with provided ID
     * @param id
     * @return Category
     */
    Category getCategoryById(int id);

    //    Batch Note
    void makeBatchNote(BatchNote batchNote);

    BatchNote getWeeklyBatchNote(int batchId, int weekId);

    List<BatchNote> allBatchNotesInWeek(int weekId);
    
    List<BatchNote> allBatchNotes(int batchId);
    
    void updateBatchNote(BatchNote batchNote);
    
    void deleteBatchNote(BatchNote batchNote);
    


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
    List<Grade> getGradesByAssessment(long assessmentId);

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

    //Note
    Note getNote(String note);

    List<Note> getAllNotes();
    //End Note

    //	Trainer Note
    void createTrainerNote(TrainerNote note);

    TrainerNote getTrainerNoteById(Integer trainerNoteId);

    TrainerNote getTrainerNoteForTrainerWeek(Integer trainerId, Integer weekId);

    Set<TrainerNote> getTrainerNotesByTrainer(Integer trainerId);

    Set<TrainerNote> getTrainerNotesByWeek(Integer weekId);

    void updateTrainerNote(TrainerNote note);

    void deleteTrainerNote(TrainerNote note);

    //QCNote
    void createQCNote(QCNote note);

    QCNote getQCNoteById(Integer qcNoteId);

    QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId);

    List<QCNote> getQCNotesByTrainee(Integer traineeId);

    List<QCNote> getQCNotesByWeek(Integer weekId);

    void updateQCNote(QCNote note);

    void deleteQCNote(QCNote note);
    //QCNote end
}
