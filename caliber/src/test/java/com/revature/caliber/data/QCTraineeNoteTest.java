package com.revature.caliber.data;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Note;
import com.revature.caliber.services.EvaluationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class QCTraineeNoteTest {

	@Autowired
	NoteDAO dao = new NoteDAO();
	@Autowired
	EvaluationService service = new EvaluationService();

	@Test
	public void testNoteDAO() {

		try {
			Note note;
			note = dao.findQCTraineeNote(3058, 2);
			System.out.printf(note.toString());

		} catch (NullPointerException e) {

			e.printStackTrace();

			fail("testNoteDAO failed");

		}
	}
	
	@Test
	public void testEvaluationService() {

		try {
			Note note;
			note = service.findQCTraineeNote(3058, 2);
			System.out.printf(note.toString());

		} catch (NullPointerException e) {

			e.printStackTrace();
			
			fail("evaluationService failed");

		}
	}

}
