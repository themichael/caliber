package com.revature.caliber.assessments.data;

import java.util.List;
import com.revature.caliber.assessments.beans.Grade;

public interface GradeDAO {

	void createGrade(Grade grade);
	void deleteGrade(Grade grade);
	void updateGrade(Grade grade);
	List<Grade> getGradesByTraineeId(Integer traineeId);
	// List<Grade> getGradesByTrainee(Trainee trainee);	
	List<Grade> getGradesByAssesessment(Integer assessmentId);
	// TODO add getByTrainee Object
	// TODO add getbyBatch?
	List<Grade> getGradesByCategoryId(Integer categoryId);
	List<Grade> getallGrades();
}