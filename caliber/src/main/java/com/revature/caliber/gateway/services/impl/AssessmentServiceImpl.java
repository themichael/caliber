package com.revature.caliber.gateway.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.BatchNote;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.QCNote;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainerNote;
import com.revature.caliber.beans.exceptions.AssessmentServiceOperationException;
import com.revature.caliber.beans.exceptions.TrainingServiceTraineeOperationException;
import com.revature.caliber.gateway.services.AssessmentService;

public class AssessmentServiceImpl implements AssessmentService {
	
	private String localhost = "http://localhost:9001";
    private String hostname;
    private String portNumber;
    
    //paths for Grades
    //TODO add the paths to the bean.xml
    private String 	addGradePath, 
    				updateGradePath, 
    				getGradesByAssessmentPath;
    //paths for Trainer Note
    private String 	deleteTrainerNotePath, 
    				updateTrainerNotePath,
    				createTrainerNotePath;
    

    @Override
	public long insertAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void updateAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Grade> getGradesByAssessment(Integer assessmentId) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getGradesByAssessmentPath)
				.path(assessmentId.toString())
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Grade[]> response = service.getForEntity(URI, Grade[].class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new TrainingServiceTraineeOperationException("Failed to retrieve trainees by batch.");
		}
		else if (response.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(response.getBody());
		}
		else {
			return new ArrayList<>();
		}
	}
	
	@Override
	public void insertGrade(Grade grade) {
		RestTemplate service = new RestTemplate();
		//Build Parameter
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(addGradePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Grade> entity = new HttpEntity<>(grade, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Grade could not be inserted");
		}
		
	}
	
	@Override
	public void updateGrade(Grade grade) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateGradePath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, grade, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Trainer could not be updated");
		}
		
	}
	
	@Override
	public void makeBatchNote(BatchNote batchNote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BatchNote weeklyBatchNote(int batchId, int weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BatchNote> allBatchNotesInWeek(int weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BatchNote> allBatchNotes(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateBatchNote(BatchNote batchNote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteBatchNote(BatchNote batchNote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createQCNote(QCNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public QCNote getQCNoteById(Integer qcNoteId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<QCNote> getQCNotesByTrainee(Integer traineeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<QCNote> getQCNotesByWeek(Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateQCNote(QCNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteQCNote(QCNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createTrainerNote(TrainerNote note) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(createTrainerNotePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<TrainerNote> entity = new HttpEntity<>(note, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new TrainingServiceTraineeOperationException("Trainer Note could not be created");
		}
	}
	@Override
	public TrainerNote getTrainerNoteById(Integer trainerNoteId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TrainerNote getTrainerNoteForTrainerWeek(Integer trainerId, Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<TrainerNote> getTrainerNotesByTrainer(Integer trainerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<TrainerNote> getTrainerNotesByWeek(Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateTrainerNote(TrainerNote note) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateTrainerNotePath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, note, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Trainer Note could not be updated");
		}
	}
	@Override
	public void deleteTrainerNote(TrainerNote note) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(deleteTrainerNotePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<TrainerNote> entity = new HttpEntity<>(note, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.DELETE, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Trainer Note could not be deleted");
		}
	}
	
	@Override
	public Set<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	

    /////////// SETTERS ////////////////
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
	
    //Grade
    public void setGradesByAssessments(String getGradesByAssessmentPath){this.getGradesByAssessmentPath = getGradesByAssessmentPath;}
    public void setInsertGrade(String addGradePath){this.addGradePath = addGradePath;}
    public void setUpdateGrade(String updateGradePath){this.updateGradePath = updateGradePath;}
    //end of Grade
    
    
    //TrainerNote
    public void setDeleteTrainerNotePath(String deleteTrainerNotePath){this.deleteTrainerNotePath = deleteTrainerNotePath;}
    public void setUpdateTrainerNotePath(String updateTrainerNotePath){this.updateTrainerNotePath = updateTrainerNotePath;}
    public void setCreateTrainerNotePath(String createTrainerNotePath){this.createTrainerNotePath = createTrainerNotePath;}
    //end of TrainerNote
    
    /*//Batch
  	public void setNewBatch(String newBatch) {this.newBatch = newBatch;}
  	public void setAllBatch(String allBatch) {this.allBatch = allBatch;}
  	public void setAllBatchesForTrainer(String allBatchesForTrainer) {this.allBatchesForTrainer = allBatchesForTrainer;}
  	public void setAllCurrentBatch(String allCurrentBatch) {this.allCurrentBatch = allCurrentBatch;}
  	public void setAllCurrentBatchByTrainer(String allCurrentBatchByTrainer) {this.allCurrentBatchByTrainer = allCurrentBatchByTrainer;}
  	public void setBatchById(String batchById) {this.batchById = batchById;}
  	public void setUpdateBatch(String updateBatch) {this.updateBatch = updateBatch;}
  	public void setDeleteBatch(String deleteBatch) {this.deleteBatch = deleteBatch;}
  	//end of batch
*/	
}
