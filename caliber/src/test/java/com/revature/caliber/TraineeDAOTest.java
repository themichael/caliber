package com.revature.caliber;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.data.TraineeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class TraineeDAOTest {

	private static Logger log = Logger.getLogger(CheckDAO.class);

	@Autowired
	private TraineeDAO traineeDAO;

	@Test
	public void testFindAll() {

	}

	@Test
	@Ignore
	public void testFindAllByBatch() {
		List<Trainee> trainees = traineeDAO.findAllByBatch(1050);
		log.info("SIze of Trainees: " + trainees.size());
		Integer notDropped = 0;
		Integer dropped = 0;
		for(Trainee t: trainees){
			if(t.getTrainingStatus().equals(TrainingStatus.Dropped)){
				dropped++;
			} else {
				notDropped++;
			}
		}
		assertTrue(dropped == 0);
		assertTrue(trainees.size() == notDropped);
	}

	@Test
	@Ignore
	public void testFindAllDroppedByBatch() {
		List<Trainee> trainees = traineeDAO.findAllDroppedByBatch(1050);
		log.info("SIze of Trainees: " + trainees.size());
		Integer notDropped = 0;
		Integer dropped = 0;
		for(Trainee t: trainees){
			if(t.getTrainingStatus().equals(TrainingStatus.Dropped)){
				dropped++;
			} else {
				notDropped++;
			}
		}
		assertTrue(notDropped == 0);
		assertTrue(trainees.size() == dropped);
	}

	@Test
	@Ignore
	public void testFindAllByTrainer() {
		List<Trainee> trainees = traineeDAO.findAllByTrainer(24);
		log.info("SIze of Trainees: " + trainees.size());
		Integer notDropped = 0;
		Integer dropped = 0;
		for(Trainee t: trainees){
			if(t.getTrainingStatus().equals(TrainingStatus.Dropped)){
				dropped++;
			} else {
				notDropped++;
			}
		}
		assertTrue(dropped == 0);
		assertTrue(trainees.size() == notDropped);
	}

	@Test
	public void testFindOne() {

	}

	@Test
	public void testFindByEmail() {

	}

}
