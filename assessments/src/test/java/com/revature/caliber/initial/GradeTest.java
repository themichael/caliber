package com.revature.caliber.initial;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.data.implementations.GradeDAOImpl;

public class GradeTest {

	private static ApplicationContext ctxt;

	@BeforeClass
	public static void setup() {
		ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	}

	@Ignore
	@Test
	public void getAllGrades() {
		List<Grade> grades = ctxt.getBean(GradeDAOImpl.class).getallGrades();
		System.out.println(grades);
	}

	@Test
	public void getGradesByTraineeId() {
		List<Grade> grades = ctxt.getBean(GradeDAOImpl.class).getGradesByTraineeId(1);
		System.out.println(grades);
	}

	@Ignore
	@Test
	public void getGradeByAssessment() {
		List<Grade> grades = ctxt.getBean(GradeDAOImpl.class).getGradesByAssesessment(1);
		System.out.println(grades);
	}

	@Ignore
	@Test
	public void getGradeByCategory() {
		List<Grade> grades = ctxt.getBean(GradeDAOImpl.class).getGradesByCategoryId(1);
		System.out.println(grades);
	}

	// TODO Add actual grade
	@Ignore
	@Test
	public void insertGrade() {
		// Grade grade = new Grade(gradeId, assessment, trainee, dateReceived,
		// score);
		// ctxt.getBean(GradeDAOImpl.class).createGrade(grade);
	}

	// TODO Update actual grade
	@Ignore
	@Test
	public void updateGrade() {
		// ctxt.getBean(GradeDAOImpl.class).updateGrade(grade);
	}

	@Ignore
	@Test
	public void deleteGrade() {
		// ctxt.getBean(GradeDAOImpl.class).deleteGrade(grade);
	}

}
