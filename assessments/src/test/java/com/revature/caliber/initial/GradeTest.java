package com.revature.caliber.initial;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.data.AssessmentDAO;
import com.revature.caliber.assessments.data.GradeDAO;
import com.revature.caliber.assessments.service.AssessmentService;

public class GradeTest {

	private static AbstractApplicationContext ctxt;
	private AssessmentService assessmentService;

	@BeforeClass
	public static void setup() {
		ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	}

	@Ignore
	@Test
	public void getAllGrades() {
		List<Grade> grades = ctxt.getBean(GradeDAO.class).getAllGrades();
		System.out.println(grades.toString());
	}

	@Ignore
	@Test
	public void getGradeByGradeId(){
		Grade grade = ctxt.getBean(GradeDAO.class).getGradeByGradeId(150);
		System.out.println(grade);
	}

	@Test
	public void getGradesByTraineeId() {
		List<Grade> grades = ctxt.getBean(GradeDAO.class).getGradesByTraineeId(1);
		System.out.println(grades);
	}

	
	@Test
	public void getGradeByAssessment() {
		List<Grade> grades = ctxt.getBean(GradeDAO.class).getGradesByAssessment(4100);
		System.out.println(grades);
	}



	@Test
	public void getavgGradeofTrainee(){
		List<Grade> grades = ctxt.getBean(GradeDAO.class).avgGradesOfAssessment();
		System.out.println("grades " + grades);
		
	}
	
	
	@Ignore
	@BeforeClass
	public static void insertGrade() {
		Assessment assessment = ctxt.getBean(AssessmentService.class).getById(4150);
	    Calendar currenttime = Calendar.getInstance();
	    Date date = new Date((currenttime.getTime()).getTime());
		Grade grade = new Grade(assessment, 1, date, 90);
		ctxt.getBean(GradeDAO.class).insertGrade(grade);
	}

	// TODO Update actual grade

	@Ignore
	@Test
	public void updateGrade() {
		
		Grade grade = ctxt.getBean(GradeDAO.class).getGradeByGradeId(150);
		grade.setScore(80);
		ctxt.getBean(GradeDAO.class).updateGrade(grade);
	}

	@Ignore
	@Test
	public void deleteGrade() {
		Grade grade = ctxt.getBean(GradeDAO.class).getGradeByGradeId(100);
		ctxt.getBean(GradeDAO.class).deleteGrade(grade);

	}

}
