package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);

	@Autowired
	ReportingService reportingService;

	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/**
	 * Utility method for testing
	 * Creates and returns a batch with 3 trainees with QC Notes and statuses for 7 weeks
	 * 
	 * @return Batch 
	 */
	private Batch createTestBatchWithQCNotes() {
		Set<Trainee> trainees = new HashSet<>();
		Batch batch = new Batch();
		batch.setWeeks(7);
		for (int i = 1; i < 4; i++) {
			Trainee trainee = new Trainee("Trainee" + i, "java", "email@email.com", batch);
			trainee.setTraineeId(i);
			Set<Note> notes = new HashSet<Note>();
			for (int j = 1; j < 8; j++) {
				Note note = new Note();
				note.setWeek((short) j);
				switch (j % 4) {
				case 0: {
					note.setQcStatus(QCStatus.Poor);
					break;
				}
				case 1: {
					note.setQcStatus(QCStatus.Average);
					break;
				}
				case 2: {
					note.setQcStatus(QCStatus.Good);
					break;
				}
				case 3: {
					note.setQcStatus(QCStatus.Superstar);
					break;
				}
				}
				notes.add(note);
			}
			trainee.setNotes(notes);
			trainees.add(trainee);
		}
		batch.setTrainees(trainees);
		return batch;
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.services.ReportingService#utilSeparateQCTraineeNotesByWeekTest()
	 */
	@Test
	public void utilSeparateQCTraineeNotesByWeekTest() {

		log.info("Testing the ReportingService.utilSeperateQCTraineeNotesByWeek");
		
		int[] statusPoorCountPerWeek = { 0, 0, 0, 3, 0, 0, 0 };
		int[] statusAverageCountPerWeek = { 3, 0, 0, 0, 3, 0, 0 };
		int[] statusGoodCountPerWeek = { 0, 3, 0, 0, 0, 3, 0 };
		int[] statusSuperstarCountPerWeek = { 0, 0, 3, 0, 0, 0, 3 };
		
		Batch batch = createTestBatchWithQCNotes();
		
		Map<Integer, Map<QCStatus, Integer>> results = reportingService.utilSeparateQCTraineeNotesByWeek(batch);
		for (int i = 1; i <= batch.getWeeks(); i++) {
			Map<QCStatus, Integer> weekStatusCount = results.get(i);
			for (QCStatus status : weekStatusCount.keySet()) {
				int expectCount = weekStatusCount.get(status);
				// Actual count is the statusCountPerWeek - 1 to account that i
				// starts at 1;
				switch (status) {
				case Poor: {
					assertEquals(expectCount, statusPoorCountPerWeek[i - 1]);
					break;
				}
				case Average: {
					assertEquals(expectCount, statusAverageCountPerWeek[i - 1]);
					break;
				}
				case Good: {
					assertEquals(expectCount, statusGoodCountPerWeek[i - 1]);
					break;
				}
				case Superstar: {
					assertEquals(expectCount, statusSuperstarCountPerWeek[i - 1]);
					break;
				}
				default: {
					assertTrue("QCStatus " + status + "  not checked during test", false);
				}
				}
			}
		}
	}

}
