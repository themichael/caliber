package com.revature.caliber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.beans.TrainingType;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class SampleDataSetup {

	private static Logger log = Logger.getLogger(SampleDataSetup.class);

	@Autowired
	private TrainerDAO trainerDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	@Autowired
	private AssessmentDAO assessmentDAO;
	@Autowired
	private GradeDAO gradeDAO;
	@Autowired
	private NoteDAO noteDAO;
	
	@Test
	public void testing() {
		log.info("Don't forget to test your code :)");
		assertNotNull(trainerDAO);
		assertThat(trainerDAO, CoreMatchers.instanceOf(TrainerDAO.class));
		batchOne(); batchTwo(); batchThree();
	}

	private void batchOne() {
		// batch 1
		log.info("Fetching Patrick Walsh");
		Trainer trainer = trainerDAO.findByEmail("pjw6193@hotmail.com");
		Calendar startDate = Calendar.getInstance();
		startDate.set(2017, Calendar.APRIL, 3);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2017, Calendar.JUNE, 9);
		Calendar benchmarkStartDate = Calendar.getInstance();
		benchmarkStartDate.set(2003, Calendar.JANUARY, 1);

		log.info("Creating batch 1602 Java for " + trainer.getName());
		Batch batch = new Batch("1602 Apr03 Java", trainer, SkillType.J2EE, TrainingType.Revature, startDate.getTime(),
				endDate.getTime(), benchmarkStartDate.getTime(),
				"Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190",  70,  50, 7);
		batchDAO.save(batch);

		log.info("Creating trainees");
		Trainee denis = new Trainee("Antonov, Denis", "ds@gmail.com", TrainingStatus.Dropped,"(678) 763-3425", null, null, batch);
		Trainee jon = new Trainee("Tech, Jonathon", "jth@gmail.com", TrainingStatus.Employed, "(918) 504-6497","jt", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee kevin = new Trainee("Haas, Kevin", "kh@georgiasouthern.edu", TrainingStatus.Employed,"(229) 255-6651", "kh", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee issac = new Trainee("Fouche, Issac", "if@gmail.com", TrainingStatus.Employed, "(301) 606-9785","iss", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee jimmy = new Trainee("Huang, Jimmy", "zh@buffalo.edu", TrainingStatus.Employed, "(917) 916-6425","jimm", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee erika = new Trainee("Castillo, Erika", "enc@gmail.com", TrainingStatus.Employed,"(229) 669-7528", "ecast", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee wyatt = new Trainee("Duling, Wyatt", "wyd@gmail.com", TrainingStatus.Employed, "563-343-6978","WyD", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee martin = new Trainee("Blanchard, Martin", "m.b@post.com", TrainingStatus.Dropped,"(603) 264-6975", null, null, batch);
		Trainee manya = new Trainee("Almatar, Manya", "ma@gmail.com", TrainingStatus.Dropped, "(773) 954-5468",null, null, batch);
		Trainee russ = new Trainee("Peters, Russell", "rd@gmail.com", TrainingStatus.Dropped, "(240) 678-3465",null, null, batch);
		traineeDAO.save(denis);
		traineeDAO.save(jon);
		traineeDAO.save(kevin);
		traineeDAO.save(issac);
		traineeDAO.save(jimmy);
		traineeDAO.save(erika);
		traineeDAO.save(wyatt);
		traineeDAO.save(martin);
		traineeDAO.save(manya);
		traineeDAO.save(russ);

		log.info("Fetching categories for assessment");
		List<Category> categories = categoryDAO.findAll();

		log.info("Creating/'autograding' assessments: week 1");
		// Java
		Assessment assessment = new Assessment("Java Test", batch, 100, AssessmentType.Exam,  1, categories.get(0));
		assessmentDAO.save(assessment);
		Date dateReceived = endDate.getTime(); // I'm lazy
		gradeDAO.save(new Grade(assessment, denis, dateReceived, 67.9));
		gradeDAO.save(new Grade(assessment, wyatt, dateReceived, 86.8));
		gradeDAO.save(new Grade(assessment, martin, dateReceived, 77.7));
		gradeDAO.save(new Grade(assessment, jon, dateReceived, 81));
		gradeDAO.save(new Grade(assessment, manya, dateReceived, 92.3));
		gradeDAO.save(new Grade(assessment, jimmy, dateReceived, 87));
		gradeDAO.save(new Grade(assessment, kevin, dateReceived, 90.2));
		gradeDAO.save(new Grade(assessment, russ, dateReceived, 79.8));
		gradeDAO.save(new Grade(assessment, issac, dateReceived, 86.63));
		gradeDAO.save(new Grade(assessment, erika, dateReceived, 88.3));

		Assessment assessment05 = new Assessment("Java Interview", batch, 100, AssessmentType.Verbal,  1, categories.get(0));
		assessmentDAO.save(assessment05);
		gradeDAO.save(new Grade(assessment05, denis, dateReceived, 60));
		gradeDAO.save(new Grade(assessment05, wyatt, dateReceived, 80));
		gradeDAO.save(new Grade(assessment05, martin, dateReceived, 70));
		gradeDAO.save(new Grade(assessment05, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment05, manya, dateReceived, 90));
		gradeDAO.save(new Grade(assessment05, jimmy, dateReceived, 80));
		gradeDAO.save(new Grade(assessment05, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment05, russ, dateReceived, 70));
		gradeDAO.save(new Grade(assessment05, issac, dateReceived, 80));
		gradeDAO.save(new Grade(assessment05, erika, dateReceived, 80));
		
		log.info("Creating notes: week 1");
		noteDAO.save(Note.trainerIndividualNote("Not confident in mock interview.", 1, denis));
		noteDAO.save(Note.trainerIndividualNote("Technically good",  1, wyatt));
		noteDAO.save(Note.trainerIndividualNote("Not confident",  1, martin));
		noteDAO.save(Note.trainerIndividualNote("Very talkative and outgoing",  1, jon));
		noteDAO.save(Note.trainerIndividualNote("Very strong technically",  1, manya));
		noteDAO.save(Note.trainerIndividualNote("Technically strong",  1, jimmy));
		noteDAO.save(Note.trainerIndividualNote("Outgoing, polished communication, and technically very strong.",  1, kevin));
		noteDAO.save(Note.trainerIndividualNote("Quiet guy",  1, russ));
		noteDAO.save(Note.trainerIndividualNote("Fun and energetic personality",  1, issac));
		noteDAO.save(Note.trainerIndividualNote("Very shy. Technically good",  1, erika));
		noteDAO.save(Note.trainerBatchNote("Week 1 was great",  1, batch));

		noteDAO.save(Note.qcIndividualNote("Didn't know things",  1, denis, QCStatus.Poor));
		noteDAO.save(Note.qcIndividualNote("Cool guy",  1, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Shy",  1, martin, QCStatus.Average));
		noteDAO.save(Note.qcIndividualNote("Talkative",  1, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Okay",  1, manya, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Quiet",  1, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Excellent stuff",  1, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Quiet guy",  1, russ, QCStatus.Average));
		noteDAO.save(Note.qcIndividualNote("Great personality",  1, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Quiet",  1, erika, QCStatus.Average));
		noteDAO.save(Note.qcBatchNote("Great start",  1, batch, QCStatus.Good));

		// SQL
		log.info("Creating/'autograding' assessments: week 2");
		Assessment assessment2 = new Assessment("SQL Test", batch, 100, AssessmentType.Exam,  2, categories.get(1));
		assessmentDAO.save(assessment2);
		
		gradeDAO.save(new Grade(assessment2, wyatt, dateReceived, 88.9));
		gradeDAO.save(new Grade(assessment2, martin, dateReceived, 64.8));
		gradeDAO.save(new Grade(assessment2, jon, dateReceived, 77.8));
		gradeDAO.save(new Grade(assessment2, manya, dateReceived, 86.3));
		gradeDAO.save(new Grade(assessment2, jimmy, dateReceived, 93));
		gradeDAO.save(new Grade(assessment2, kevin, dateReceived, 97.8));
		gradeDAO.save(new Grade(assessment2, russ, dateReceived, 70.74));
		gradeDAO.save(new Grade(assessment2, issac, dateReceived, 96.29));
		gradeDAO.save(new Grade(assessment2, erika, dateReceived, 89.63));
		
		Assessment assessment25 = new Assessment("JDBC Interview", batch, 100, AssessmentType.Verbal,  2, categories.get(8));
		assessmentDAO.save(assessment25);
		gradeDAO.save(new Grade(assessment25, wyatt, dateReceived, 80));
		gradeDAO.save(new Grade(assessment25, martin, dateReceived, 60));
		gradeDAO.save(new Grade(assessment25, jon, dateReceived, 70));
		gradeDAO.save(new Grade(assessment25, manya, dateReceived, 80));
		gradeDAO.save(new Grade(assessment25, jimmy, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, russ, dateReceived, 70));
		gradeDAO.save(new Grade(assessment25, issac, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, erika, dateReceived, 80));

		log.info("Creating notes: week 2");
		noteDAO.save(Note.trainerIndividualNote("Made great progress on his project",  2,wyatt));
		noteDAO.save(Note.trainerIndividualNote("Cannot keep up with the pace.", 2, martin));
		noteDAO.save(Note.trainerIndividualNote("Good understanding of SQL", 2, jon));
		noteDAO.save(Note.trainerIndividualNote("Very good knowledge in SQL", 2, manya));
		noteDAO.save(Note.trainerIndividualNote("Outstanding work on his project",  2, jimmy));
		noteDAO.save(Note.trainerIndividualNote("Superstar. Impeccable project, very strong technically.",  2,kevin));
		noteDAO.save(Note.trainerIndividualNote("Struggled with the project", 2, russ));
		noteDAO.save(Note.trainerIndividualNote("Amazing work on the project and fantastic interview skills and confidence.",  2, issac));
		noteDAO.save(Note.trainerIndividualNote("Very good work on the project! Great understanding in SQL.",  2,erika));
		noteDAO.save(Note.trainerBatchNote("Week 2 was great",  2, batch));

		noteDAO.save(Note.qcIndividualNote("Great guy",  2, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Decent knowledge",  2, martin, QCStatus.Poor));
		noteDAO.save(Note.qcIndividualNote("Outgoing",  2, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("A little shy",  2, manya, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Still quiet",  2, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Superb work",  2, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Kind of shy",  2, russ, QCStatus.Average));
		noteDAO.save(Note.qcIndividualNote("Great work!",  2, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Great stuff",  2, erika, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Keep it up!",  2, batch, QCStatus.Good));

		// JSP
		log.info("Creating/'autograding' assessments: week 3");
		Assessment assessment3 = new Assessment("JSP Test", batch, 100, AssessmentType.Exam,  3, categories.get(3));
		assessmentDAO.save(assessment3);
		gradeDAO.save(new Grade(assessment3, wyatt, dateReceived, 86.8));
		gradeDAO.save(new Grade(assessment3, martin, dateReceived, 77.7));
		gradeDAO.save(new Grade(assessment3, jon, dateReceived, 81));
		gradeDAO.save(new Grade(assessment3, manya, dateReceived, 92.3));
		gradeDAO.save(new Grade(assessment3, jimmy, dateReceived, 87));
		gradeDAO.save(new Grade(assessment3, kevin, dateReceived, 90.2));
		gradeDAO.save(new Grade(assessment3, russ, dateReceived, 79.8));
		gradeDAO.save(new Grade(assessment3, issac, dateReceived, 86.63));
		gradeDAO.save(new Grade(assessment3, erika, dateReceived, 88.3));

		Assessment assessment35 = new Assessment("JSP Interview", batch, 100, AssessmentType.Verbal,  3, categories.get(3));
		assessmentDAO.save(assessment35);
		gradeDAO.save(new Grade(assessment35, wyatt, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, martin, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, manya, dateReceived, 90));
		gradeDAO.save(new Grade(assessment35, jimmy, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment35, russ, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, issac, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, erika, dateReceived, 80));
		
		Assessment assessment355 = new Assessment("Project", batch, 50, AssessmentType.Project,  3, categories.get(3));
		assessmentDAO.save(assessment355);
		gradeDAO.save(new Grade(assessment355, wyatt, dateReceived, 90));
		gradeDAO.save(new Grade(assessment355, martin, dateReceived, 80));
		gradeDAO.save(new Grade(assessment355, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment355, manya, dateReceived, 90));
		gradeDAO.save(new Grade(assessment355, jimmy, dateReceived, 90));
		gradeDAO.save(new Grade(assessment355, kevin, dateReceived, 100));
		gradeDAO.save(new Grade(assessment355, russ, dateReceived, 60));
		gradeDAO.save(new Grade(assessment355, issac, dateReceived, 100));
		gradeDAO.save(new Grade(assessment355, erika, dateReceived, 80));
		
		log.info("Creating notes: week 3");
		noteDAO.save(Note.trainerIndividualNote("Good progress this week. ",  3, wyatt));
		noteDAO.save(Note.trainerIndividualNote("Made less than 70 on his LMS test again, but is making good overall progress. ",  3, jon));
		noteDAO.save(Note.trainerIndividualNote("Great work so far! Picks up on the material very quickly and made significant progress on her project.", 3, manya));
		noteDAO.save(Note.trainerIndividualNote("Great work from Jimmy this week.",  3, jimmy));
		noteDAO.save(Note.trainerIndividualNote("All-star. Finished his project early and helps other classmates work on theirs. Aced the LMS test where others fell behind a bit.", 3, kevin));
		noteDAO.save(Note.trainerIndividualNote("Dropped. He insisted that he couldn't keep up with the pace. See Nan for more detail.", 3, russ));
		noteDAO.save(Note.trainerIndividualNote("Good work. Very strong technically, and still a friendly outgoing personality shining through.", 3, issac));
		noteDAO.save(Note.trainerIndividualNote("Great progress on her project. Very good technically and seems to be becoming more comfortable in her confidence and presentation skills. ", 3, erika));
		noteDAO.save(Note.trainerBatchNote("Week 3 was great",  3, batch));

		noteDAO.save(Note.qcIndividualNote("Good",  3, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Well understanding",  3, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Quiet week",  3, manya, QCStatus.Average));
		noteDAO.save(Note.qcIndividualNote("Great guy",  3, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fantastic ",  3, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Wow",  3, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Good stuff",  3, erika, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Great week guys",  3, batch, QCStatus.Good));

		// Hibernate
		log.info("Creating/'autograding' assessments: week 4");
		Assessment assessment4 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  4, categories.get(17));
		assessmentDAO.save(assessment4);
		gradeDAO.save(new Grade(assessment4, wyatt, dateReceived, 84.24));
		gradeDAO.save(new Grade(assessment4, jon, dateReceived, 86.56));
		gradeDAO.save(new Grade(assessment4, manya, dateReceived, 90.91));
		gradeDAO.save(new Grade(assessment4, jimmy, dateReceived, 88.28));
		gradeDAO.save(new Grade(assessment4, kevin, dateReceived, 97.98));
		gradeDAO.save(new Grade(assessment4, issac, dateReceived, 85.25));
		gradeDAO.save(new Grade(assessment4, erika, dateReceived, 90.91));
		
		Assessment assessment45 = new Assessment("Interview", batch, 100, AssessmentType.Verbal,  4, categories.get(17));
		assessmentDAO.save(assessment45);
		gradeDAO.save(new Grade(assessment45, wyatt, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, manya, dateReceived, 90));
		gradeDAO.save(new Grade(assessment45, jimmy, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment45, issac, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, erika, dateReceived, 90));
		
		Assessment assessment455 = new Assessment("Project", batch, 100, AssessmentType.Project,  4, categories.get(17));
		assessmentDAO.save(assessment455);
		gradeDAO.save(new Grade(assessment455, wyatt, dateReceived, 100));
		gradeDAO.save(new Grade(assessment455, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment455, manya, dateReceived, 70));
		gradeDAO.save(new Grade(assessment455, jimmy, dateReceived, 80));
		gradeDAO.save(new Grade(assessment455, kevin, dateReceived, 100));
		gradeDAO.save(new Grade(assessment455, issac, dateReceived, 100));
		gradeDAO.save(new Grade(assessment455, erika, dateReceived, 90));

		log.info("Creating notes: week 4");
		noteDAO.save(Note.trainerIndividualNote("Great work this week. Put a lot of work into his chess application. Did okay on interview but struggled with AngularJS on the test.", 4, wyatt));
		noteDAO.save(Note.trainerIndividualNote("Good work on his project and in class. ",  4, jon));
		noteDAO.save(Note.trainerIndividualNote("Struggled on the test but put so much effort into the project and mock interview.",  4, manya));
		noteDAO.save(Note.trainerIndividualNote("Very good work here! I am pleased with his project.",  4, jimmy));
		noteDAO.save(Note.trainerIndividualNote("Great work. Seems very confident in the interview and exceeded expectations on the test.",  4,kevin));
		noteDAO.save(Note.trainerIndividualNote("Great work on the project and in class.",  4, issac));
		noteDAO.save(Note.trainerIndividualNote("Very confident in interview and did very well on the project.", 4, erika));
		noteDAO.save(Note.trainerBatchNote("Week 4 was great",  4, batch));

		noteDAO.save(Note.qcIndividualNote("Great",  4, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Wow",  4, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Good",  4, manya, QCStatus.Average));
		noteDAO.save(Note.qcIndividualNote("Stellar",  4, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Outstanding",  4, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Jeez.. wow",  4, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Great",  4, erika, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("That batch tho",  4, batch, QCStatus.Good));
		// Spring
		log.info("Creating/'autograding' assessments: week 5");
		Assessment assessment5 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  5, categories.get(18));
		assessmentDAO.save(assessment5);
		gradeDAO.save(new Grade(assessment5, wyatt, dateReceived, 100));
		gradeDAO.save(new Grade(assessment5, jon, dateReceived, 83.68));
		gradeDAO.save(new Grade(assessment5, manya, dateReceived, 86.84));
		gradeDAO.save(new Grade(assessment5, jimmy, dateReceived, 93.86));
		gradeDAO.save(new Grade(assessment5, kevin, dateReceived, 97.37));
		gradeDAO.save(new Grade(assessment5, issac, dateReceived, 89.65));
		gradeDAO.save(new Grade(assessment5, erika, dateReceived, 87.72));

		Assessment assessment55 = new Assessment("Interview", batch, 100, AssessmentType.Verbal,  5, categories.get(18));
		assessmentDAO.save(assessment55);
		gradeDAO.save(new Grade(assessment55, wyatt, dateReceived, 80));
		gradeDAO.save(new Grade(assessment55, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment55, manya, dateReceived, 80));
		gradeDAO.save(new Grade(assessment55, jimmy, dateReceived, 90));
		gradeDAO.save(new Grade(assessment55, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment55, issac, dateReceived, 80));
		gradeDAO.save(new Grade(assessment55, erika, dateReceived, 80));
		
		Assessment assessment555 = new Assessment("Project", batch, 100, AssessmentType.Project,  5, categories.get(18));
		assessmentDAO.save(assessment555);
		gradeDAO.save(new Grade(assessment555, wyatt, dateReceived, 100));
		gradeDAO.save(new Grade(assessment555, jon, dateReceived, 75));
		gradeDAO.save(new Grade(assessment555, manya, dateReceived, 85));
		gradeDAO.save(new Grade(assessment555, jimmy, dateReceived, 95));
		gradeDAO.save(new Grade(assessment555, kevin, dateReceived, 95));
		gradeDAO.save(new Grade(assessment555, issac, dateReceived, 85));
		gradeDAO.save(new Grade(assessment555, erika, dateReceived, 85));
		
		log.info("Creating notes: week 5");
		noteDAO.save(Note.trainerIndividualNote("Great week for this guy",  5, wyatt));
		noteDAO.save(Note.trainerIndividualNote("Massive improvements",  5, jon));
		noteDAO.save(Note.trainerIndividualNote("Dropped.", 5, manya));
		noteDAO.save(Note.trainerIndividualNote("Jimmy is a great developer",  5, jimmy));
		noteDAO.save(Note.trainerIndividualNote("Wow factor",  5, kevin));
		noteDAO.save(Note.trainerIndividualNote("Super developer",  5, issac));
		noteDAO.save(Note.trainerIndividualNote("Seem lot of improvements in interpersonal skill",  5, erika));
		noteDAO.save(Note.trainerBatchNote("Week 5 was great",  5, batch));

		noteDAO.save(Note.qcIndividualNote("Wow",  5, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Holy cow",  5, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Good",  5, manya, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Great!",  5, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Insane",  5, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Tremendous",  5, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Stellar",  5, erika, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("This batch is glorious",  5, batch, QCStatus.Good));

		// REST
		log.info("Creating/'autograding' assessments: week 6");
		Assessment assessment6 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  6, categories.get(20));
		assessmentDAO.save(assessment6);
		gradeDAO.save(new Grade(assessment6, wyatt, dateReceived, 92.31));
		gradeDAO.save(new Grade(assessment6, jon, dateReceived, 85));
		gradeDAO.save(new Grade(assessment6, manya, dateReceived, 86.15));
		gradeDAO.save(new Grade(assessment6, jimmy, dateReceived, 88.33));
		gradeDAO.save(new Grade(assessment6, kevin, dateReceived, 98.08));
		gradeDAO.save(new Grade(assessment6, issac, dateReceived, 90.9));
		gradeDAO.save(new Grade(assessment6, erika, dateReceived, 89.49));
		
		Assessment assessment65 = new Assessment("Mock Interview", batch, 100, AssessmentType.Verbal,  6, categories.get(20));
		assessmentDAO.save(assessment65);
		gradeDAO.save(new Grade(assessment65, wyatt, dateReceived, 95));
		gradeDAO.save(new Grade(assessment65, jon, dateReceived, 85));
		gradeDAO.save(new Grade(assessment65, manya, dateReceived, 85));
		gradeDAO.save(new Grade(assessment65, jimmy, dateReceived, 85));
		gradeDAO.save(new Grade(assessment65, kevin, dateReceived, 95));
		gradeDAO.save(new Grade(assessment65, issac, dateReceived, 95));
		gradeDAO.save(new Grade(assessment65, erika, dateReceived, 85));
		
		Assessment assessment655 = new Assessment("Project", batch, 100, AssessmentType.Project,  6, categories.get(20));
		assessmentDAO.save(assessment655);
		gradeDAO.save(new Grade(assessment655, wyatt, dateReceived, 90));
		gradeDAO.save(new Grade(assessment655, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment655, manya, dateReceived, 80));
		gradeDAO.save(new Grade(assessment655, jimmy, dateReceived, 80));
		gradeDAO.save(new Grade(assessment655, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment655, issac, dateReceived, 90));
		gradeDAO.save(new Grade(assessment655, erika, dateReceived, 80));

		log.info("Creating notes: week 6");
		noteDAO.save(Note.trainerIndividualNote("Good understanding on Web Services. A little behind on project work.", 6, wyatt));
		noteDAO.save(Note.trainerIndividualNote("Great at talking Web Services. Networking knowledge definitely paid off for him here. Project is going okay.", 6, jon));
		noteDAO.save(Note.trainerIndividualNote("Great work this week. Good project progress.",  6, jimmy));
		noteDAO.save(Note.trainerIndividualNote("Fantastic work. Nearly finished with second project and implemented Web Service communication between them. ", 6, kevin));
		noteDAO.save(Note.trainerIndividualNote("Great work. Just about finished up with his second project.", 6, issac));
		noteDAO.save(Note.trainerIndividualNote("Great progress! Good understanding on Web Services and nearly finished on second project.",  6,erika));
		noteDAO.save(Note.trainerBatchNote("Week 6 was great",  6, batch));

		noteDAO.save(Note.qcIndividualNote("Wow",  6, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fabulous",  6, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Tremendous",  6, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Splendid",  6, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Glorious",  6, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Fantastic",  6, erika, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Fantastical batch",  6, batch, QCStatus.Good));

		// AngularJS
		log.info("Creating/'autograding' assessments: week 7");
		Assessment assessment7 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  7, categories.get(21));
		assessmentDAO.save(assessment7);
		gradeDAO.save(new Grade(assessment7, wyatt, dateReceived, 88.28));
		gradeDAO.save(new Grade(assessment7, jon, dateReceived, 86.77));
		gradeDAO.save(new Grade(assessment7, jimmy, dateReceived, 90.45));
		gradeDAO.save(new Grade(assessment7, kevin, dateReceived, 94.95));
		gradeDAO.save(new Grade(assessment7, issac, dateReceived, 89.95));
		gradeDAO.save(new Grade(assessment7, erika, dateReceived, 90.61));
		
		Assessment assessment75 = new Assessment("One on One Interview", batch, 100, AssessmentType.Exam,  7, categories.get(21));
		assessmentDAO.save(assessment75);
		gradeDAO.save(new Grade(assessment75, wyatt, dateReceived, 80));
		gradeDAO.save(new Grade(assessment75, jon, dateReceived, 80));
		gradeDAO.save(new Grade(assessment75, jimmy, dateReceived, 90));
		gradeDAO.save(new Grade(assessment75, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment75, issac, dateReceived, 80));
		gradeDAO.save(new Grade(assessment75, erika, dateReceived, 90));
		
		Assessment assessment755 = new Assessment("Project Demonstration", batch, 100, AssessmentType.Exam,  7, categories.get(21));
		assessmentDAO.save(assessment755);
		gradeDAO.save(new Grade(assessment755, wyatt, dateReceived, 85));
		gradeDAO.save(new Grade(assessment755, jon, dateReceived, 85));
		gradeDAO.save(new Grade(assessment755, jimmy, dateReceived, 95));
		gradeDAO.save(new Grade(assessment755, kevin, dateReceived, 95));
		gradeDAO.save(new Grade(assessment755, issac, dateReceived, 85));
		gradeDAO.save(new Grade(assessment755, erika, dateReceived, 95));

		log.info("Creating notes: week 7");
		noteDAO.save(Note.trainerIndividualNote("Very good knowledge of Spring.",  7, wyatt));
		noteDAO.save(Note.trainerIndividualNote("Good knowledge of Spring.",  7, jon));
		noteDAO.save(
				Note.trainerIndividualNote("Good work on the project and on Spring evaluations.",  7, jimmy));
		noteDAO.save(Note.trainerIndividualNote("Excellent work this week and on project.",  7, kevin));
		noteDAO.save(Note.trainerIndividualNote("Great work on projects and Spring.",  7, issac));
		noteDAO.save(Note.trainerIndividualNote("Excellent work on the project and evaluations",  7, erika));
		noteDAO.save(Note.trainerBatchNote("Week 7 was great",  7, batch));

		noteDAO.save(Note.qcIndividualNote("Great",  7, wyatt, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Wow",  7, jon, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fun",  7, jimmy, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fantastic",  7, kevin, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Excellent",  7, issac, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Flawless victory",  7, erika, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Not bad at all",  7, batch, QCStatus.Good));
	}

	private void batchTwo() {
		// batch 2
		log.info("Fetching Patrick Walsh");
		Trainer trainer = trainerDAO.findByEmail("pjw6193@hotmail.com");
		Calendar startDate = Calendar.getInstance();
		startDate.set(2017, Calendar.MARCH, 27);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2017, Calendar.JUNE, 2);
		Calendar benchmarkStartDate = Calendar.getInstance();
		benchmarkStartDate.set(2003, Calendar.JANUARY, 1);

		log.info("Creating batch 1604 Java for " + trainer.getName());
		Batch batch = new Batch("1604 Mar27 Java", trainer, SkillType.J2EE, TrainingType.Revature, startDate.getTime(),
				endDate.getTime(), benchmarkStartDate.getTime(),
				"Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190",  70,  50, 7);
		batchDAO.save(batch);

		log.info("Creating trainees");
		Trainee wong = new Trainee("Wong, Steven", "sw@gmail.com", TrainingStatus.Dropped,"(757) 289-4488", null, null, batch);
		Trainee jeff = new Trainee("Lovell, Jeffrey", "jlvl@hotmail.com", TrainingStatus.Employed, "414-801-9214","j.lo", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=98CB90616B7168BE", batch);
		Trainee denny = new Trainee("Ayard, Denny", "deyd@yahoo.com", TrainingStatus.Employed,"626-394-4631", "d.ay", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=1A4891AD002361F3", batch);
		Trainee jason = new Trainee("Spruce, Jason", "js@gmail.com", TrainingStatus.Dropped, "417-793-8764","jspru", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=C2C31DA539BA9204", batch);
		Trainee sidak = new Trainee("Dhillon, Sidak", "sdh@hotmail.com", TrainingStatus.Dropped, "6783084664","", "", batch);
		Trainee tyler = new Trainee("Welborn, Tyler", "tyw@yahoo.com", TrainingStatus.Employed,"(806) 831-7765", "", "", batch);
		Trainee tony = new Trainee("Hill, Anthony", "thill@yahoo.com", TrainingStatus.Dropped, "312-752-1124","", "", batch);
		Trainee justin = new Trainee("Atkinson, Justin", "jat@gmail.com", TrainingStatus.Employed,"267-229-7896", "j.atkin", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=6B5AE92F8B9E7B42", batch);
		Trainee matt = new Trainee("Olson, Matthew", "mols", TrainingStatus.Dropped, "(573) 382-4546",null, null, batch);
		Trainee yan = new Trainee("Lin, Yan", "ylin@gmail.com", TrainingStatus.Employed, "(646) 641-8863","Yliin", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=BA50174314ADF183", batch);
		Trainee tad = new Trainee("Detlef, Tad", "tdet@hotmail.com", TrainingStatus.Employed, "980-254-7725","t.det", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=760BB40801D49E08", batch);
		Trainee fagbemi = new Trainee("Young, Fagbemi", "fyung@gmail.com", TrainingStatus.Dropped, "(757) 478-3687",null, null, batch);
		Trainee josh = new Trainee("Brown, Joshua", "jbrown@yahoo.com", TrainingStatus.Employed, "301-917-7689","j.brown", "https://app.revature.com/core/admin/pages/portfolio_ta?profile&view&proId=187ABBDC1C05325F", batch);
		traineeDAO.save(wong);
		traineeDAO.save(jeff);
		traineeDAO.save(denny);
		traineeDAO.save(jason);
		traineeDAO.save(sidak);
		traineeDAO.save(tyler);
		traineeDAO.save(tony);
		traineeDAO.save(justin);
		traineeDAO.save(matt);
		traineeDAO.save(yan);
		traineeDAO.save(tad);
		traineeDAO.save(fagbemi);
		traineeDAO.save(josh);

		log.info("Fetching categories for assessment");
		List<Category> categories = categoryDAO.findAll();

		log.info("Creating/'autograding' assessments: week 1");
		// Java
		Assessment assessment = new Assessment("LMS", batch, 100, AssessmentType.Exam,  1, categories.get(0));
		assessmentDAO.save(assessment);
		Date dateReceived = endDate.getTime(); // I'm still lazy
		gradeDAO.save(new Grade(assessment, wong, dateReceived, 60));
		gradeDAO.save(new Grade(assessment, jeff, dateReceived, 96));
		gradeDAO.save(new Grade(assessment, denny, dateReceived, 93));
		gradeDAO.save(new Grade(assessment, jason, dateReceived, 80));
		gradeDAO.save(new Grade(assessment, sidak, dateReceived, 88));
		gradeDAO.save(new Grade(assessment, tyler, dateReceived, 80));
		gradeDAO.save(new Grade(assessment, tony, dateReceived, 75));
		gradeDAO.save(new Grade(assessment, justin, dateReceived, 81));
		gradeDAO.save(new Grade(assessment, matt, dateReceived, 85));
		gradeDAO.save(new Grade(assessment, yan, dateReceived, 87));
		gradeDAO.save(new Grade(assessment, tad, dateReceived, 83));
		gradeDAO.save(new Grade(assessment, fagbemi, dateReceived, 73));
		gradeDAO.save(new Grade(assessment, josh, dateReceived, 85)); 
		
		Assessment assessment0 = new Assessment("Interview", batch, 100, AssessmentType.Verbal,  1, categories.get(0));
		assessmentDAO.save(assessment0);
		gradeDAO.save(new Grade(assessment0, wong, dateReceived, 65));
		gradeDAO.save(new Grade(assessment0, jeff, dateReceived, 95));
		gradeDAO.save(new Grade(assessment0, denny, dateReceived, 95));
		gradeDAO.save(new Grade(assessment0, jason, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, sidak, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, tyler, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, tony, dateReceived, 75));
		gradeDAO.save(new Grade(assessment0, justin, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, matt, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, yan, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, tad, dateReceived, 85));
		gradeDAO.save(new Grade(assessment0, fagbemi, dateReceived, 75));
		gradeDAO.save(new Grade(assessment0, josh, dateReceived, 85)); 
		
		log.info("Creating notes: week 1");
		noteDAO.save(Note.trainerIndividualNote("Dropped", 1, wong));
		noteDAO.save(Note.trainerIndividualNote("Dropped", 1, fagbemi));
		noteDAO.save(Note.trainerBatchNote("The thread questions were usually the ones missed.",  1, batch));

		noteDAO.save(Note.qcIndividualNote("Great stuff!",  1, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Great work",  1, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Great job",  1, josh, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Great efforts",  1, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Tremendous",  1, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fabulous",  1, jeff, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Great start to this batch",  1, batch, QCStatus.Good));

		// SQL
		log.info("Creating/'autograding' assessments: week 2");
		Assessment assessment2 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  2, categories.get(1));
		assessmentDAO.save(assessment2);
		gradeDAO.save(new Grade(assessment2, jeff, dateReceived, 97));
		gradeDAO.save(new Grade(assessment2, denny, dateReceived, 92));
		gradeDAO.save(new Grade(assessment2, jason, dateReceived, 86));
		gradeDAO.save(new Grade(assessment2, sidak, dateReceived, 94));
		gradeDAO.save(new Grade(assessment2, tyler, dateReceived, 87));
		gradeDAO.save(new Grade(assessment2, tony, dateReceived, 92));
		gradeDAO.save(new Grade(assessment2, justin, dateReceived, 80));
		gradeDAO.save(new Grade(assessment2, matt, dateReceived, 92));
		gradeDAO.save(new Grade(assessment2, yan, dateReceived, 98));
		gradeDAO.save(new Grade(assessment2, tad, dateReceived, 92));
		gradeDAO.save(new Grade(assessment2, josh, dateReceived, 98)); 
		
		Assessment assessment25 = new Assessment("Interview", batch, 100, AssessmentType.Verbal,  2, categories.get(1));
		assessmentDAO.save(assessment25);
		gradeDAO.save(new Grade(assessment25, jeff, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, denny, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, jason, dateReceived, 80));
		gradeDAO.save(new Grade(assessment25, sidak, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, tyler, dateReceived, 80));
		gradeDAO.save(new Grade(assessment25, tony, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, justin, dateReceived, 80));
		gradeDAO.save(new Grade(assessment25, matt, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, yan, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, tad, dateReceived, 90));
		gradeDAO.save(new Grade(assessment25, josh, dateReceived, 90)); 

		log.info("Creating notes: week 2");
		noteDAO.save(Note.trainerIndividualNote("Struggled on test", 2, justin));
		noteDAO.save(Note.trainerBatchNote("SQL went well. Thanks again Brian for stepping in.",  2, batch));

		noteDAO.save(Note.qcIndividualNote("Wow! Great stuff!",  2, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Superb! Great work",  2, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Boom! Great job",  2, josh, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Nice! Great efforts",  2, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Very tremendous",  2, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Most Fabulous",  2, jeff, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Great progress in this batch",  2, batch, QCStatus.Good));

		// JSP
		log.info("Creating/'autograding' assessments: week 3");
		Assessment assessment3 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  3, categories.get(3));
		assessmentDAO.save(assessment3);
		gradeDAO.save(new Grade(assessment3, jeff, dateReceived, 93.94));
		gradeDAO.save(new Grade(assessment3, denny, dateReceived, 92.43));
		gradeDAO.save(new Grade(assessment3, jason, dateReceived, 84.87));
		gradeDAO.save(new Grade(assessment3, sidak, dateReceived, 84.87));
		gradeDAO.save(new Grade(assessment3, tyler, dateReceived, 63.31));
		gradeDAO.save(new Grade(assessment3, tony, dateReceived, 83.34));
		gradeDAO.save(new Grade(assessment3, justin, dateReceived, 67.32));
		gradeDAO.save(new Grade(assessment3, matt, dateReceived, 65.34));
		gradeDAO.save(new Grade(assessment3, yan, dateReceived, 87.9));
		gradeDAO.save(new Grade(assessment3, tad, dateReceived, 79.87));
		gradeDAO.save(new Grade(assessment3, josh, dateReceived, 89.4)); 
		
		Assessment assessment35 = new Assessment("Interviews", batch, 100, AssessmentType.Exam,  3, categories.get(3));
		assessmentDAO.save(assessment35);
		gradeDAO.save(new Grade(assessment35, jeff, dateReceived, 90));
		gradeDAO.save(new Grade(assessment35, denny, dateReceived, 90));
		gradeDAO.save(new Grade(assessment35, jason, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, sidak, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, tyler, dateReceived, 60));
		gradeDAO.save(new Grade(assessment35, tony, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, justin, dateReceived, 60));
		gradeDAO.save(new Grade(assessment35, matt, dateReceived, 60));
		gradeDAO.save(new Grade(assessment35, yan, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, tad, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, josh, dateReceived, 80)); 

		log.info("Creating notes: week 3");
		noteDAO.save(Note.trainerBatchNote("Good week in the JSP",  3, batch));

		noteDAO.save(Note.qcIndividualNote("Nice.",  3, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Excellent",  3, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Cool",  3, josh, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Indeed",  3, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Baboom",  3, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fantastic",  3, jeff, QCStatus.Superstar));
		noteDAO.save(Note.qcBatchNote("Great work in this week of batch",  3, batch, QCStatus.Good));

		// Hibernate
		log.info("Creating/'autograding' assessments: week 4");
		Assessment assessment4 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  4, categories.get(17));
		assessmentDAO.save(assessment4);
		gradeDAO.save(new Grade(assessment4, jeff, dateReceived, 72.5));
		gradeDAO.save(new Grade(assessment4, denny, dateReceived,94.23));
		gradeDAO.save(new Grade(assessment4, jason, dateReceived,71.46));
		gradeDAO.save(new Grade(assessment4, sidak, dateReceived,72.23));
		gradeDAO.save(new Grade(assessment4, tyler, dateReceived,70.27));
		gradeDAO.save(new Grade(assessment4, justin, dateReceived,74.5));
		gradeDAO.save(new Grade(assessment4, matt, dateReceived, 75.5));
		gradeDAO.save(new Grade(assessment4, yan, dateReceived, 80.77));
		gradeDAO.save(new Grade(assessment4, tad, dateReceived, 77.5));
		gradeDAO.save(new Grade(assessment4, josh, dateReceived, 93.27));
		
		Assessment assessment45 = new Assessment("Interviews", batch, 100, AssessmentType.Verbal,  4, categories.get(17));
		assessmentDAO.save(assessment45);
		gradeDAO.save(new Grade(assessment45, jeff, dateReceived, 70));
		gradeDAO.save(new Grade(assessment45, denny, dateReceived,90));
		gradeDAO.save(new Grade(assessment45, jason, dateReceived,70));
		gradeDAO.save(new Grade(assessment45, sidak, dateReceived,70));
		gradeDAO.save(new Grade(assessment45, tyler, dateReceived,70));
		gradeDAO.save(new Grade(assessment45, justin, dateReceived,70));
		gradeDAO.save(new Grade(assessment45, matt, dateReceived, 70));
		gradeDAO.save(new Grade(assessment45, yan, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, tad, dateReceived, 70));
		gradeDAO.save(new Grade(assessment45, josh, dateReceived, 90));

		Assessment assessment455 = new Assessment("Projects", batch, 100, AssessmentType.Project,  4, categories.get(17));
		assessmentDAO.save(assessment455);
		gradeDAO.save(new Grade(assessment455, jeff, dateReceived, 75));
		gradeDAO.save(new Grade(assessment455, denny, dateReceived,95));
		gradeDAO.save(new Grade(assessment455, jason, dateReceived,75));
		gradeDAO.save(new Grade(assessment455, sidak, dateReceived,75));
		gradeDAO.save(new Grade(assessment455, tyler, dateReceived,75));
		gradeDAO.save(new Grade(assessment455, justin, dateReceived,75));
		gradeDAO.save(new Grade(assessment455, matt, dateReceived, 75));
		gradeDAO.save(new Grade(assessment455, yan, dateReceived, 85));
		gradeDAO.save(new Grade(assessment455, tad, dateReceived, 75));
		gradeDAO.save(new Grade(assessment455, josh, dateReceived, 95));
		
		log.info("Creating notes: week 4");
		noteDAO.save(Note.trainerBatchNote("Good progress on Hibernate!",  4, batch));

		noteDAO.save(Note.qcIndividualNote("Nice stuff",  4, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Excellent stuff",  4, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Cool stuff",  4, josh, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Indeedily doo",  4, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Baboommma",  4, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fantastical stuff",  4, jeff, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Great stuff in this batch",  4, batch, QCStatus.Good));
		// Spring
		log.info("Creating/'autograding' assessments: week 5");
		Assessment assessment5 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  5, categories.get(18));
		assessmentDAO.save(assessment5);
		gradeDAO.save(new Grade(assessment5, jeff, dateReceived, 89.47));
		gradeDAO.save(new Grade(assessment5, denny, dateReceived,100));
		gradeDAO.save(new Grade(assessment5, jason, dateReceived,71.05));
		gradeDAO.save(new Grade(assessment5, justin, dateReceived,68.42));
		gradeDAO.save(new Grade(assessment5, yan, dateReceived, 78.95));
		gradeDAO.save(new Grade(assessment5, tad, dateReceived, 73.68));
		gradeDAO.save(new Grade(assessment5, josh, dateReceived, 73.68));
		
		Assessment assessment55 = new Assessment("Project", batch, 100, AssessmentType.Project,  5, categories.get(18));
		assessmentDAO.save(assessment55);
		gradeDAO.save(new Grade(assessment55, jeff, dateReceived, 80));
		gradeDAO.save(new Grade(assessment55, denny, dateReceived,90));
		gradeDAO.save(new Grade(assessment55, jason, dateReceived,70));
		gradeDAO.save(new Grade(assessment55, justin, dateReceived,60));
		gradeDAO.save(new Grade(assessment55, yan, dateReceived, 70));
		gradeDAO.save(new Grade(assessment55, tad, dateReceived, 70));
		gradeDAO.save(new Grade(assessment55, josh, dateReceived, 70));

		log.info("Creating notes: week 5");
		noteDAO.save(Note.trainerBatchNote("Good week. Some preinterviews setting a few people back, but altogether they did well.",  5, batch));

		noteDAO.save(Note.qcIndividualNote("Nice stuff",  5, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Excellent stuff",  5, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Cool stuff",  5, josh, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Indeedily doo",  5, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Baboommma",  5, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fantastical stuff",  5, jeff, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("Great stuff in this batch",  5, batch, QCStatus.Good));

		// REST
		log.info("Creating/'autograding' assessments: week 6");
		Assessment assessment6 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  6, categories.get(20));
		assessmentDAO.save(assessment6);
		gradeDAO.save(new Grade(assessment6, jeff, dateReceived, 90.46));
		gradeDAO.save(new Grade(assessment6, denny, dateReceived,95.46));
		gradeDAO.save(new Grade(assessment6, jason, dateReceived,71.37));
		gradeDAO.save(new Grade(assessment6, justin, dateReceived,63.34));
		gradeDAO.save(new Grade(assessment6, yan, dateReceived, 82.43));
		gradeDAO.save(new Grade(assessment6, tad, dateReceived, 67.88));
		gradeDAO.save(new Grade(assessment6, josh, dateReceived, 87.43));
		
		Assessment assessment65 = new Assessment("Interviews", batch, 100, AssessmentType.Verbal,  6, categories.get(20));
		assessmentDAO.save(assessment65);
		gradeDAO.save(new Grade(assessment65, jeff, dateReceived, 95));
		gradeDAO.save(new Grade(assessment65, denny, dateReceived,95));
		gradeDAO.save(new Grade(assessment65, jason, dateReceived,75));
		gradeDAO.save(new Grade(assessment65, justin, dateReceived,65));
		gradeDAO.save(new Grade(assessment65, yan, dateReceived, 85));
		gradeDAO.save(new Grade(assessment65, tad, dateReceived, 65));
		gradeDAO.save(new Grade(assessment65, josh, dateReceived, 85));
		
		Assessment assessment655 = new Assessment("Projects", batch, 100, AssessmentType.Project,  6, categories.get(20));
		assessmentDAO.save(assessment655);
		gradeDAO.save(new Grade(assessment655, jeff, dateReceived, 91));
		gradeDAO.save(new Grade(assessment655, denny, dateReceived,91));
		gradeDAO.save(new Grade(assessment655, jason, dateReceived,71));
		gradeDAO.save(new Grade(assessment655, justin, dateReceived,61));
		gradeDAO.save(new Grade(assessment655, yan, dateReceived, 81));
		gradeDAO.save(new Grade(assessment655, tad, dateReceived, 61));
		gradeDAO.save(new Grade(assessment655, josh, dateReceived, 81));

		log.info("Creating notes: week 6");
		noteDAO.save(Note.trainerBatchNote("Many topics this week. The batch handled this week very well!",  5, batch));

		noteDAO.save(Note.qcIndividualNote("Great",  6, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Wow",  6, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Nice",  6, josh, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Super",  6, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Fun",  6, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Wowwwie!",  6, jeff, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("I sense great knowledge in this batch",  6, batch, QCStatus.Good));

		// AngularJS
		log.info("Creating/'autograding' assessments: week 7");
		Assessment assessment7 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  7, categories.get(21));
		assessmentDAO.save(assessment7);
		gradeDAO.save(new Grade(assessment7, jeff, dateReceived, 90));
		gradeDAO.save(new Grade(assessment7, denny, dateReceived,100));
		gradeDAO.save(new Grade(assessment7, jason, dateReceived,66));
		gradeDAO.save(new Grade(assessment7, justin, dateReceived,69));
		gradeDAO.save(new Grade(assessment7, yan, dateReceived, 86));
		gradeDAO.save(new Grade(assessment7, tad, dateReceived, 82));
		gradeDAO.save(new Grade(assessment7, josh, dateReceived, 86));
		
		Assessment assessment75 = new Assessment("Verbal", batch, 100, AssessmentType.Verbal,  7, categories.get(21));
		assessmentDAO.save(assessment75);
		gradeDAO.save(new Grade(assessment75, jeff, dateReceived, 95));
		gradeDAO.save(new Grade(assessment75, denny, dateReceived,95));
		gradeDAO.save(new Grade(assessment75, jason, dateReceived,65));
		gradeDAO.save(new Grade(assessment75, justin, dateReceived,65));
		gradeDAO.save(new Grade(assessment75, yan, dateReceived, 85));
		gradeDAO.save(new Grade(assessment75, tad, dateReceived, 85));
		gradeDAO.save(new Grade(assessment75, josh, dateReceived, 85));

		log.info("Creating notes: week 7");
		noteDAO.save(Note.trainerBatchNote("Good week.",  7, batch));

		noteDAO.save(Note.qcIndividualNote("Graet",  7, yan, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Great",  7, denny, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Grate",  7, josh, QCStatus.Superstar));
		noteDAO.save(Note.qcIndividualNote("Grite",  7, justin, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Grain",  7, tad, QCStatus.Good));
		noteDAO.save(Note.qcIndividualNote("Grown",  7, jeff, QCStatus.Good));
		noteDAO.save(Note.qcBatchNote("I sense grep knowledge in this batch",  7, batch, QCStatus.Good));
	}

	private void batchThree() {
		// batch 3
		log.info("Fetching Igor");
		Trainer trainer = trainerDAO.findByEmail("igluskin94@gmail.com");
		Calendar startDate = Calendar.getInstance();
		startDate.set(2017, Calendar.MARCH, 27);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2017, Calendar.JUNE, 2);
		Calendar benchmarkStartDate = Calendar.getInstance();
		benchmarkStartDate.set(2003, Calendar.JANUARY, 1);

		log.info("Creating batch 1605 Java for " + trainer.getName());
		Batch batch = new Batch("1605 Mar27 Java", trainer, SkillType.J2EE, TrainingType.University, startDate.getTime(),
				endDate.getTime(), benchmarkStartDate.getTime(),
				"Tech Incubator at Queens College, 65-30 Kissena Blvd, CEP Hall 2, Queens, NY 11367",  70,  50, 4);
		batchDAO.save(batch);

		log.info("Creating trainees");
		Trainee mike = new Trainee("Cartagena, Michael", "mcaez@gmail.com", TrainingStatus.Employed,"347-782-9856", null, null, batch);
		Trainee yani = new Trainee("Peralta, Yanilda", "yany@hotmail.com", TrainingStatus.Employed, "347-638-6734",null,null, batch);
		Trainee jack = new Trainee("Duong, Jack", "jackd@yahoo.com", TrainingStatus.Employed,"(646) 417-7845", null,null, batch);
		Trainee hendy = new Trainee("Valcin, Hendy", "hendy@gmail.com", TrainingStatus.Employed, "347-272-3422",null, null, batch);
		Trainee hossain = new Trainee("Yahya, Hossain", "boss.hoss@hotmail.com", TrainingStatus.Employed, "347-595-6794",null, null, batch);
		Trainee fareed = new Trainee("Ali, Fareed", "fareed@yahoo.com", TrainingStatus.Employed,"347-526-9875", null, null, batch);
		Trainee kam = new Trainee("Lam, Kam", "kam@yahoo.com", TrainingStatus.Employed, "917-951-4568",null, null, batch);
		Trainee pier = new Trainee("Yos, Pier", "pier@gmail.com", TrainingStatus.Employed,"347-238-4978", null,null, batch);
		Trainee sudish = new Trainee("Itwaru, Sudish", "sudish@itwaru.com", TrainingStatus.Employed, "718-415-6485",null, null, batch);
		Trainee daniel = new Trainee("Liu, Daniel", "dan@gmail.com", TrainingStatus.Employed, "646-275-6584",null,null, batch);
		Trainee kevin = new Trainee("Guan, Kevin", "kevin@hotmail.com", TrainingStatus.Employed, "347-447-6455",null,null, batch);
		Trainee sean = new Trainee("Connelly, Sean", "sean@gmail.com", TrainingStatus.Employed, "(718) 772-6457",null, null, batch);

		traineeDAO.save(mike);
		traineeDAO.save(yani);
		traineeDAO.save(jack);
		traineeDAO.save(hendy);
		traineeDAO.save(hossain);
		traineeDAO.save(fareed);
		traineeDAO.save(kam);
		traineeDAO.save(pier);
		traineeDAO.save(sudish);
		traineeDAO.save(daniel);
		traineeDAO.save(kevin);
		traineeDAO.save(sean);

		log.info("Fetching categories for assessment");
		List<Category> categories = categoryDAO.findAll();

		log.info("Creating/'autograding' assessments: week 1");
		// Java
		Assessment assessment = new Assessment("LMS", batch, 100, AssessmentType.Exam,  1, categories.get(0));
		assessmentDAO.save(assessment);
		Date dateReceived = endDate.getTime(); // I'm super lazy
		gradeDAO.save(new Grade(assessment, mike, dateReceived, 60.71));
		gradeDAO.save(new Grade(assessment, yani, dateReceived, 67.86));
		gradeDAO.save(new Grade(assessment, jack, dateReceived, 64.29));
		gradeDAO.save(new Grade(assessment, hendy, dateReceived, 75));
		gradeDAO.save(new Grade(assessment, hossain, dateReceived, 76.79));
		gradeDAO.save(new Grade(assessment, fareed, dateReceived, 75));
		gradeDAO.save(new Grade(assessment, kam, dateReceived, 48.21));
		gradeDAO.save(new Grade(assessment, pier, dateReceived, 69.64));
		gradeDAO.save(new Grade(assessment, sudish, dateReceived, 62.5));
		gradeDAO.save(new Grade(assessment, daniel, dateReceived, 76.79));
		gradeDAO.save(new Grade(assessment, kevin, dateReceived, 73.21));
		gradeDAO.save(new Grade(assessment, sean, dateReceived, 67.86));
		
		Assessment assessment0 = new Assessment("Interview", batch, 100, AssessmentType.Verbal,  1, categories.get(0));
		assessmentDAO.save(assessment0);
		gradeDAO.save(new Grade(assessment0, mike, dateReceived, 70));
		gradeDAO.save(new Grade(assessment0, yani, dateReceived, 100));
		gradeDAO.save(new Grade(assessment0, jack, dateReceived, 70));
		gradeDAO.save(new Grade(assessment0, hendy, dateReceived, 80));
		gradeDAO.save(new Grade(assessment0, hossain, dateReceived,90));
		gradeDAO.save(new Grade(assessment0, fareed, dateReceived, 10));
		gradeDAO.save(new Grade(assessment0, kam, dateReceived, 50));
		gradeDAO.save(new Grade(assessment0, pier, dateReceived, 90));
		gradeDAO.save(new Grade(assessment0, sudish, dateReceived, 70));
		gradeDAO.save(new Grade(assessment0, daniel, dateReceived, 60));
		gradeDAO.save(new Grade(assessment0, kevin, dateReceived, 90));
		gradeDAO.save(new Grade(assessment0, sean, dateReceived, 90));
		
		log.info("Creating notes: week 1.. jk");

		// SQL
		log.info("Creating/'autograding' assessments: week 2");
		Assessment assessment2 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  2, categories.get(1));
		assessmentDAO.save(assessment2);
		gradeDAO.save(new Grade(assessment2, mike, dateReceived, 77.83));
		gradeDAO.save(new Grade(assessment2, sean, dateReceived, 67.45));
		gradeDAO.save(new Grade(assessment2, yani, dateReceived, 75.47));
		gradeDAO.save(new Grade(assessment2, jack, dateReceived, 91.51));
		gradeDAO.save(new Grade(assessment2, hendy, dateReceived, 61.32));
		gradeDAO.save(new Grade(assessment2, hossain, dateReceived, 94.34));
		gradeDAO.save(new Grade(assessment2, fareed, dateReceived, 78.3));
		gradeDAO.save(new Grade(assessment2, kam, dateReceived, 87.74));
		gradeDAO.save(new Grade(assessment2, pier, dateReceived, 91.98));
		gradeDAO.save(new Grade(assessment2, sudish, dateReceived, 82.08));
		gradeDAO.save(new Grade(assessment2, daniel, dateReceived, 93.4));
		gradeDAO.save(new Grade(assessment2, kevin, dateReceived, 64.62));
		
		Assessment assessment25 = new Assessment("Interview", batch, 100, AssessmentType.Verbal,  2, categories.get(1));
		assessmentDAO.save(assessment25);
		gradeDAO.save(new Grade(assessment2, mike, dateReceived, 70));
		gradeDAO.save(new Grade(assessment2, sean, dateReceived, 70));
		gradeDAO.save(new Grade(assessment2, yani, dateReceived, 80));
		gradeDAO.save(new Grade(assessment2, jack, dateReceived, 100));
		gradeDAO.save(new Grade(assessment2, hendy, dateReceived, 70));
		gradeDAO.save(new Grade(assessment2, hossain, dateReceived, 100));
		gradeDAO.save(new Grade(assessment2, fareed, dateReceived, 100));
		gradeDAO.save(new Grade(assessment2, kam, dateReceived, 90));
		gradeDAO.save(new Grade(assessment2, pier, dateReceived, 110));
		gradeDAO.save(new Grade(assessment2, sudish, dateReceived, 100));
		gradeDAO.save(new Grade(assessment2, daniel, dateReceived, 90));
		gradeDAO.save(new Grade(assessment2, kevin, dateReceived, 70));

		log.info("Creating notes: week 2");

		// JSP
		log.info("Creating/'autograding' assessments: week 3");
		Assessment assessment3 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  3, categories.get(3));
		assessmentDAO.save(assessment3);
		gradeDAO.save(new Grade(assessment3, mike, dateReceived, 64.1));
		gradeDAO.save(new Grade(assessment3, sean, dateReceived, 56.41));
		gradeDAO.save(new Grade(assessment3, yani, dateReceived, 74.36));
		gradeDAO.save(new Grade(assessment3, jack, dateReceived, 76.92));
		gradeDAO.save(new Grade(assessment3, hendy, dateReceived, 66.67));
		gradeDAO.save(new Grade(assessment3, hossain, dateReceived, 84.62));
		gradeDAO.save(new Grade(assessment3, fareed, dateReceived, 61.54));
		gradeDAO.save(new Grade(assessment3, kam, dateReceived, 56.41));
		gradeDAO.save(new Grade(assessment3, pier, dateReceived, 76.92));
		gradeDAO.save(new Grade(assessment3, sudish, dateReceived, 61.54));
		gradeDAO.save(new Grade(assessment3, daniel, dateReceived, 61.54));
		gradeDAO.save(new Grade(assessment3, kevin, dateReceived, 64.1)); 
		
		Assessment assessment35 = new Assessment("Interviews", batch, 100, AssessmentType.Verbal,  3, categories.get(3));
		assessmentDAO.save(assessment35);
		gradeDAO.save(new Grade(assessment35, mike, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, sean, dateReceived,80));
		gradeDAO.save(new Grade(assessment35, yani, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, jack, dateReceived, 90));
		gradeDAO.save(new Grade(assessment35, hendy, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, hossain, dateReceived, 100));
		gradeDAO.save(new Grade(assessment35, fareed, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, kam, dateReceived, 60));
		gradeDAO.save(new Grade(assessment35, pier, dateReceived, 100));
		gradeDAO.save(new Grade(assessment35, sudish, dateReceived, 70));
		gradeDAO.save(new Grade(assessment35, daniel, dateReceived, 80));
		gradeDAO.save(new Grade(assessment35, kevin, dateReceived, 70));

		log.info("Creating notes: week 3");

		// Hibernate
		log.info("Creating/'autograding' assessments: week 4");
		Assessment assessment4 = new Assessment("LMS", batch, 100, AssessmentType.Exam,  4, categories.get(17));
		assessmentDAO.save(assessment4);
		gradeDAO.save(new Grade(assessment4, mike, dateReceived, 53.33));
		gradeDAO.save(new Grade(assessment4, sean, dateReceived, 67.88));
		gradeDAO.save(new Grade(assessment4, yani, dateReceived, 65.11));
		gradeDAO.save(new Grade(assessment4, jack, dateReceived,81.11));
		gradeDAO.save(new Grade(assessment4, hendy, dateReceived,66.56));
		gradeDAO.save(new Grade(assessment4, hossain, dateReceived,80.58));
		gradeDAO.save(new Grade(assessment4, fareed, dateReceived,62.86));
		gradeDAO.save(new Grade(assessment4, pier, dateReceived,78.07));
		gradeDAO.save(new Grade(assessment4, sudish, dateReceived, 66.83));
		gradeDAO.save(new Grade(assessment4, daniel, dateReceived, 74.23));
		gradeDAO.save(new Grade(assessment4, kevin, dateReceived, 73.04));
		
		Assessment assessment45 = new Assessment("Interviews", batch, 100, AssessmentType.Verbal,  4, categories.get(17));
		assessmentDAO.save(assessment45);
		gradeDAO.save(new Grade(assessment45, mike, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, sean, dateReceived, 90));
		gradeDAO.save(new Grade(assessment45, yani, dateReceived, 90));
		gradeDAO.save(new Grade(assessment45, jack, dateReceived,90));
		gradeDAO.save(new Grade(assessment45, hendy, dateReceived,70));
		gradeDAO.save(new Grade(assessment45, hossain, dateReceived,100));
		gradeDAO.save(new Grade(assessment45, fareed, dateReceived,80));
		gradeDAO.save(new Grade(assessment45, pier, dateReceived,100));
		gradeDAO.save(new Grade(assessment45, sudish, dateReceived, 80));
		gradeDAO.save(new Grade(assessment45, daniel, dateReceived, 90));
		gradeDAO.save(new Grade(assessment45, kevin, dateReceived, 90));
		
		log.info("Creating notes: week 4");
	}

}
