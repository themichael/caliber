package com.revature.caliber.gateway.services.impl;

import com.revature.caliber.beans.*;
import com.revature.caliber.beans.exceptions.AssessmentServiceAssessmentOperationException;
import com.revature.caliber.beans.exceptions.AssessmentServiceOperationException;
import com.revature.caliber.beans.exceptions.TrainingServiceTraineeOperationException;
import com.revature.caliber.gateway.services.AssessmentService;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.*;


public class AssessmentServiceImpl implements AssessmentService {
	
	private String localhost = "http://localhost:8081";
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
    
    //paths for QC Note
    private String createQCNotePath, updateQCNotePath;
    
    //paths for Batch Note
    private String 	createBatchNotePath,
    				updateBatchNotePath,
    				deleteBatchNotePath;
    
    //paths for assessments
    private String addAssessmentPath, updateAssessmentPath, deleteAssessmentPath;
	private String getGradesByTraineePath;


	@Override
	public void insertAssessment(Assessment assessment) {
		RestTemplate service = new RestTemplate();
		
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(addAssessmentPath)
				.build().toUriString();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Assessment> entity = new HttpEntity<>(assessment, headers);
		
		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceAssessmentOperationException("Assessment could not be made");
		}
	}
    
	@Override
	public void updateAssessment(Assessment assessment) {
		RestTemplate service = new RestTemplate();
		
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateAssessmentPath).build().toUriString();
		
		//invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, assessment, Serializable.class);
		
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceAssessmentOperationException("Assessment could not be updated");
		}
	}
	@Override
	public void deleteAssessment(Assessment assessment) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(deleteAssessmentPath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Assessment> entity = new HttpEntity<>(assessment, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.DELETE, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceAssessmentOperationException("Assessment could not be deleted");
		}
		
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
			throw new TrainingServiceTraineeOperationException("Failed to retrieve grades from the assessment.");
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
			throw new AssessmentServiceOperationException("Grade could not be updated");

		}
		
	}
	
	@Override
	public void createBatchNote(BatchNote batchNote) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(createBatchNotePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<BatchNote> entity = new HttpEntity<>(batchNote, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Batch Note could not be created");
		}
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
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateBatchNotePath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, batchNote, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Batch Note could not be updated");
		}
	}
	@Override
	public void deleteBatchNote(BatchNote batchNote) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(deleteBatchNotePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<BatchNote> entity = new HttpEntity<>(batchNote, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.DELETE, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("Batch Note could not be deleted");
		}
	}
	@Override
	public void createQCNote(QCNote note) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(createQCNotePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<QCNote> entity = new HttpEntity<>(note, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("QC Note could not be created");
		}
	}
	
	@Override
	public void updateQCNote(QCNote note) {
		RestTemplate service = new RestTemplate();
		
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateTrainerNotePath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, note, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new AssessmentServiceOperationException("QC Note could not be updated");
		}
		
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


	@Override
	public List<Grade> getGradesByTraineeId(int id) {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<com.revature.caliber.assessment.beans.Grade[]> response =
				rest.getForEntity("http://localhost:8081/assessments/grades/trainee/"+ id,
						com.revature.caliber.assessment.beans.Grade[].class);

		com.revature.caliber.assessment.beans.Grade[] grades = response.getBody();

		List<Grade> newGrades = new ArrayList<>();

		for (com.revature.caliber.assessment.beans.Grade someGrade : grades) {
			Grade someNewGrade  = new Grade();

			com.revature.caliber.assessment.beans.Assessment someAssessment = someGrade.getAssessment();
			Assessment someNewAssessment = new Assessment();
			someNewAssessment.setAssessmentId(someAssessment.getAssessmentId());

			Set<Category> someNewCategories = new HashSet<>();
			Set<com.revature.caliber.assessment.beans.Category> someCategories = someAssessment.getCategories();
			for (com.revature.caliber.assessment.beans.Category category : someCategories) {
				Category newCategory = new Category();
				newCategory.setCategoryId(category.getCategoryId());
				newCategory.setSkillCategory(category.getSkillCategory());
				someNewCategories.add(newCategory);
			}
			someNewAssessment.setCategories(someNewCategories);

			someNewGrade.setScore(someGrade.getScore());
			someNewGrade.setAssessment(someNewAssessment);
			someNewGrade.setGradeId(someGrade.getGradeId());


			newGrades.add(someNewGrade);
		}
		//ResponseEntity<Grade[]> response =
				//rest.getForEntity("http://localhost:8080/assessments/grades/trainee/"+id, Grade[].class); //TODO change ip to get from config file
		return newGrades;
	}




    /**
     * Sets hostname.
     *
     * @param hostname the hostname
     */
/////////// SETTERS ////////////////
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Sets port number.
     *
     * @param portNumber the port number
     */
    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
	

    //Grade
    public void setGradesByAssessments(String getGradesByAssessmentPath){this.getGradesByAssessmentPath = getGradesByAssessmentPath;}
    public void setInsertGrade(String addGradePath){this.addGradePath = addGradePath;}
    public void setUpdateGrade(String updateGradePath){this.updateGradePath = updateGradePath;}
    //end of Grade
    
    //Assessment
    public void setDeleteAssessment(String deleteAssessmentPath){this.deleteAssessmentPath = deleteAssessmentPath;}
    public void setInsertAssessment(String addAssessmentPath){this.addAssessmentPath = addAssessmentPath;}
    public void setUpdateAssessment(String updateAssessmentPath){this.updateAssessmentPath = updateAssessmentPath;}
    //end of Assessment
    
    //TrainerNote
    public void setDeleteTrainerNotePath(String deleteTrainerNotePath){this.deleteTrainerNotePath = deleteTrainerNotePath;}
    public void setUpdateTrainerNotePath(String updateTrainerNotePath){this.updateTrainerNotePath = updateTrainerNotePath;}
    public void setCreateTrainerNotePath(String createTrainerNotePath){this.createTrainerNotePath = createTrainerNotePath;}
    //end of TrainerNote
    
    //QCNote
    public void setCreateQCNotePath(String createQCNotePath){this.createQCNotePath = createQCNotePath;}
    public void setUpdateQCNotePath(String updateQCNotePath){this.updateQCNotePath = updateQCNotePath;}
    //end of QCNote
    
    //BatchNote
    public void setCreateBatchNotePath(String createBatchNotePath){this.createBatchNotePath = createBatchNotePath;}
    public void setUpdateBatchNotePath(String updateBatchNotePath){this.updateBatchNotePath = updateBatchNotePath;}
    public void setDeleteBatchNotePath(String deleteBatchNotePath){this.deleteBatchNotePath = deleteBatchNotePath;}

	public void setGetGradesByTraineePath(String getGradesByTraineePath) {
		this.getGradesByTraineePath = getGradesByTraineePath;
	}

	public String getGetGradesByTraineePath() {
		return getGradesByTraineePath;
	}
}
