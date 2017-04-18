package com.revature.caliber.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;

@Component
public class BaseDAO {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Batch initializeActiveTrainees(Batch batch) {
		Set<Trainee> trainees = new HashSet<>();
		if (batch != null) {
			for (Trainee trainee : batch.getTrainees()) {
				if (trainee.getTrainingStatus() != null
						&& !trainee.getTrainingStatus().equals(TrainingStatus.Dropped)) {
					trainees.add(trainee);
				}
			}
			batch.setTrainees(trainees);
		}
		return batch;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Assessment initializeActiveTrainees(Assessment assessment) {
		Set<Trainee> trainees = new HashSet<>();
		if (assessment != null && assessment.getBatch() != null) {
			for (Trainee trainee : assessment.getBatch().getTrainees()) {
				if (trainee.getTrainingStatus() != null
						&& !trainee.getTrainingStatus().equals(TrainingStatus.Dropped)) {
					trainees.add(trainee);
				}
			}
			assessment.getBatch().setTrainees(trainees);
		}
		return assessment;
	}

}
