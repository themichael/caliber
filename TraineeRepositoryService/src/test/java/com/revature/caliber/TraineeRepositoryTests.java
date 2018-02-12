package com.revature.caliber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.Trainer;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.TraineeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraineeRepositoryTests {
	
	@Autowired
	TraineeRepository test;
	
	@Test
	public void TestfindOneByTraineeIdAndTrainingStatusNot() {
		SimpleTrainee trainee = test.findOne(1);
		
		trainee = test.findOneByTraineeIdAndTrainingStatusNot((Integer) trainee.getTraineeId(), TrainingStatus.Dropped);
		assertNotNull(trainee.getTraineeId());
	}
	
	@Test
	public void TestfindAllByEmailLikeAndTrainingStatusNot() {
		List<SimpleTrainee> trainee = test.findAllByEmailLikeAndTrainingStatusNot("howard.johnson@hotmail.com", TrainingStatus.Dropped);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByNameLikeAndTrainingStatusNot() {
		List<SimpleTrainee> trainee = test.findAllByNameLikeAndTrainingStatusNot("Howard Johnson", TrainingStatus.Dropped);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllBySkypeIdLikeAndTrainingStatusNot() {
		List<SimpleTrainee> trainee = test.findAllBySkypeIdLikeAndTrainingStatusNot("osher.y.cohen", TrainingStatus.Dropped);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByBatchIdAndTrainingStatusNot() {
		List<SimpleTrainee> trainee = test.findAllByBatchIdAndTrainingStatusNot(2003, TrainingStatus.Dropped);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByBatchIdAndTrainingStatus() {
		List<SimpleTrainee> trainee = test.findAllByBatchIdAndTrainingStatusNot(2003, TrainingStatus.Training);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByTrainingStatusNot() {
		List<SimpleTrainee> trainee = test.findAllByTrainingStatusNot(TrainingStatus.Dropped);
		assertNotNull(trainee);
	}
	
	@Test
	public void TestfindAllByBatchId() {
		List<SimpleTrainee> trainee = test.findAllByBatchId(2003);
		assertNotNull(trainee);
	}
}