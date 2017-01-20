package com.revature.caliber.assessments.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.GradeService;

@Service(value = "gradeService")
public class GradeServiceImpl implements GradeService{

	private Facade facade;
	
	@Autowired
	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	@Override
	public List<Grade> getAllGrades() {
		return facade.getAllGrades();
	}

	@Override
	public Grade getGradeByGradeId(int gradeId) {
		return facade.getGradeByGradeId(gradeId);
	}

	@Override
	public List<Grade> getGradesByTraineeId(int traineeId) {
		return facade.getGradesByTraineeId(traineeId);
	}

	@Override
	public List<Grade> getGradesByAssesessment(int assessmentId) {
		return facade.getGradesByAssesessment(assessmentId);
	}

	@Override
	public void insertGrade(Grade grade) {
		facade.insertGrade(grade);
	}

	@Override
	public void deleteGrade(Grade grade) {
		facade.deleteGrade(grade);
	}

	@Override
	public void updateGrade(Grade grade) {
		facade.updateGrade(grade);
	}

}
