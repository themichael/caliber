package com.revature.caliber.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;

@Component
public class BaseDAO {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> initializeActiveTrainees(List<Trainee> trainees) {
		List<Trainee> active = new ArrayList<>();
		if (trainees != null) {
			for (Trainee trainee : trainees) {
				if (trainee.getTrainingStatus() != null
						&& !trainee.getTrainingStatus().equals(TrainingStatus.Dropped)) {
					Hibernate.initialize(trainee.getGrades());
					active.add(trainee);
				}
			}
		}
		return active;
	}

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

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Note initializeActiveTrainees(Note note) {
		Set<Trainee> trainees = new HashSet<>();
		if (note != null && note.getBatch() != null) {
			for (Trainee trainee : note.getBatch().getTrainees()) {
				if (trainee.getTrainingStatus() != null
						&& !trainee.getTrainingStatus().equals(TrainingStatus.Dropped)) {
					trainees.add(trainee);
				}
			}
			note.getBatch().setTrainees(trainees);
		}
		return note;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> initializeDroppedTrainees(List<Trainee> trainees) {
		List<Trainee> dropped = new ArrayList<>();
		if (trainees != null) {
			for (Trainee trainee : trainees) {
				if (trainee.getTrainingStatus() != null && trainee.getTrainingStatus().equals(TrainingStatus.Dropped)) {
					Hibernate.initialize(trainee.getGrades());
					dropped.add(trainee);
				}
			}
		}
		return dropped;
	}

}
