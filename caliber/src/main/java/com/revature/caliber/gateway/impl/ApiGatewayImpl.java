package com.revature.caliber.gateway.impl;

import com.revature.caliber.beans.*;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.BatchNote;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.QCNote;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerNote;

import com.revature.caliber.gateway.ApiGateway;
import com.revature.caliber.gateway.services.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Api gateway.
 */
@Component
public class ApiGatewayImpl implements ApiGateway {


    private ServiceLocator serviceLocator;

    /**
     * Sets service locator.
     *
     * @param serviceLocator the service locator
     */
    @Autowired
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    /****************************Batch*******************************/
    public void createBatch(Batch batch) {
        serviceLocator.getTrainingService().createBatch(batch);
    }

    public List<Batch> allBatch() {
        return serviceLocator.getTrainingService().allBatch();
    }

    public List<Batch> getBatches(Integer id) {
        return serviceLocator.getTrainingService().getBatches(id);
    }

    public List<Batch> currentBatch() {
        return serviceLocator.getTrainingService().currentBatch();
    }

    public List<Batch> currentBatch(Integer id) {
        return serviceLocator.getTrainingService().currentBatch(id);
    }

    public Batch getBatch(Integer id) {
        return serviceLocator.getTrainingService().getBatch(id);
    }

    public void updateBatch(Batch batch) {
        serviceLocator.getTrainingService().updateBatch(batch);
    }

    public void deleteBatch(Batch batch) {
        serviceLocator.getTrainingService().deleteBatch(batch);
    }

    /****************************Trainee*******************************/
    @Override
    public void createTrainee(Trainee trainee) {
        serviceLocator.getTrainingService().createTrainee(trainee);
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        serviceLocator.getTrainingService().updateTrainee(trainee);
    }

    @Override
    public Trainee getTrainee(Integer id) {
        return serviceLocator.getTrainingService().getTrainee(id);
    }

    @Override
    public Trainee getTrainee(String email) {
        return serviceLocator.getTrainingService().getTrainee(email);
    }

    @Override
    public List<Trainee> getTraineesInBatch(Integer batchId) {
        return serviceLocator.getTrainingService().getTraineesInBatch(batchId);
    }

    @Override
    public void deleteTrainee(Trainee trainee) {
        serviceLocator.getTrainingService().deleteTrainee(trainee);
    }

    //
    @Override
    public void createTrainer(Trainer trainer) {
        serviceLocator.getTrainingService().createTrainer(trainer);
    }

    @Override
    public Trainer getTrainer(Integer id) {
        return serviceLocator.getTrainingService().getTrainer(id);
    }

    @Override
    public Trainer getTrainer(String email) {
        return serviceLocator.getTrainingService().getTrainer(email);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return serviceLocator.getTrainingService().getAllTrainers();
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        serviceLocator.getTrainingService().updateTrainer(trainer);
    }



	/**************************************Grade************************************/
	@Override
	public List<Grade> getGradesByAssessment(Integer assessmentId) {
		return serviceLocator.getAssessmentService().getGradesByAssessment(assessmentId);
	}

	@Override
	public void insertGrade(Grade grade) {
		serviceLocator.getAssessmentService().insertGrade(grade);

	}

	/***********************************Trainer Notes**********************************/
	@Override
	public void createTrainerNote(TrainerNote note) {
		serviceLocator.getAssessmentService().createTrainerNote(note);

	}

	@Override
	public void updateTrainerNote(TrainerNote note) {
		serviceLocator.getAssessmentService().updateTrainerNote(note);
	}

	@Override
	public void deleteTrainerNote(TrainerNote note) {
		serviceLocator.getAssessmentService().deleteTrainerNote(note);

	}

	/****************************Batch Notes**********************************/
	@Override
	public void createBatchNote(BatchNote batchNote) {
		serviceLocator.getAssessmentService().createBatchNote(batchNote);

	}

	@Override
	public void updateBatchNote(BatchNote batchNote) {
		serviceLocator.getAssessmentService().updateBatchNote(batchNote);

	}

	@Override
	public void deleteBatchNote(BatchNote batchNote) {
		serviceLocator.getAssessmentService().deleteBatchNote(batchNote);

	}

	/****************************Assessment**********************************/
	@Override
	public void insertAssessment(Assessment assessment) {
		serviceLocator.getAssessmentService().insertAssessment(assessment);
	}

	@Override
	public void updateAssessment(Assessment assessment) {
		serviceLocator.getAssessmentService().updateAssessment(assessment);
	}

	@Override
	public void deleteAssessment(Assessment assessment) {
		serviceLocator.getAssessmentService().deleteAssessment(assessment);
	}

	/****************************QCNote**********************************/
	@Override
	public void createQCNote(QCNote note) {
		serviceLocator.getAssessmentService().createQCNote(note);
	}

	@Override
	public void updateQCNote(QCNote note) {
		serviceLocator.getAssessmentService().updateQCNote(note);

	}

    /**
     * Gets batch from current batches by id.
     *
     * @param id the id
     * @return the batch from current batches by id
     */
    public Batch getBatchFromCurrentBatchesById(int id) {
        Batch batch = new Batch();
        batch.setBatchId(id);
        return batch;
    }

    /**
     * Gets current batch.
     *
     * @return the current batch
     */
    public Batch getCurrentBatch() {
        return null;
    }

    /**
     * Gets all batches.
     *
     * @return the all batches
     */
    public Set<Batch> getAllBatches() {
        return null;
    }

    /**
     * Update batch from current batches by id batch.
     *
     * @param batch the batch
     * @return the batch
     */
    public Batch updateBatchFromCurrentBatchesById(Batch batch) {
        return batch;
    }

    /**
     * Insert batch into current batches batch.
     *
     * @param batch the batch
     * @return the batch
     */
    public Batch insertBatchIntoCurrentBatches(Batch batch) {
        return batch;
    }

    /**
     * Delete batch from current batches by id batch.
     *
     * @param id the id
     * @return the batch
     */
    public Batch deleteBatchFromCurrentBatchesById(int id) {
        return null;
    }

    /**
     * Update all current batches set.
     *
     * @param batches the batches
     * @return the set
     */
    public Set<Batch> updateAllCurrentBatches(Set<Batch> batches) {
        return batches;
    }


    /**
     * Gets batch by trainer id.
     *
     * @param id the id
     * @return the batch by trainer id
     */
    public Batch getBatchByTrainerId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Gets all current batches.
     *
     * @return the all current batches
     */
    public Set<Batch> getAllCurrentBatches() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Gets batch from current batches by id.
     *
     * @return the batch from current batches by id
     */
    public Batch getBatchFromCurrentBatchesById() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Gets batch from all batches by id.
     *
     * @return the batch from all batches by id
     */
    public Batch getBatchFromAllBatchesById() {
        // TODO Auto-generated method stub
        return null;
	}


    /**
     * // Trainee
     // Shehar
     Aggregate grades by all tech for a Trainee // param - traineeId
     - HashMap
     - key Tech(Category)
     - value double array
     - average
     - median
     - high
     - low
     Key: REST, Value: [83.54, 78.56, 90.56, 78.56]
     Key: SOAP, Value: [83.54, 78.56, 90.56, 78.56]
     * @param id
     * @return
     */
	@Override
	public HashMap<String, Double[]> getWeekGradeDataForTrainee(int id) {
		List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(id);
		HashMap<String, Double[]> grades = new HashMap<>();//result
		
		for(Grade grade : allGrades){
			long week = grade.getAssessment().getAssessmentId();
			System.out.println(week);
//			if(!grades.containsKey(week.getWeekNumber())){
//				grades.put(Integer.toString(week.getWeekNumber()), new Double[] {0.0,0.0,0.0,0.0});
//			}
		}
		return grades;
	}


    @Override
    public HashMap<String, Double[]> getTechGradeDataForTrainee(int id) {
        List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(id); //grade data that we get from assessment module
        HashMap<String,Double[]> grades = new HashMap<>(); //our result map
        HashMap<String, List<Integer>> gradeValues = new HashMap<>(); //get grade values //what 

        //processing
        for ( Grade grade : allGrades) {
            List<Category> catList = grade.getAssessment().getCategories().stream().collect(Collectors.toList());//makes a list out of the set
            if (catList.size() < 1) { continue; }
            Category category = catList.get(0); //assume there is only one category per assessment

            //map does not have the key yet
            if (!grades.containsKey(category.getSkillCategory())) {
                grades.put(category.getSkillCategory(), new Double[] {0.0, 0.0, 0.0, 0.0});
                gradeValues.put(category.getSkillCategory(), new ArrayList<>());
            }

            //add grade to total number
            grades.get(category.getSkillCategory())[0] += grade.getScore();
            //add grade to list of grades for a tech
            gradeValues.get(category.getSkillCategory()).add(grade.getScore());
        }

        //actually processing the values
        for (String categoryName : grades.keySet()) {
            //convenience
            Double [] gradeV = grades.get(categoryName);
            List<Integer> list = gradeValues.get(categoryName);
            list.sort(Integer::compareTo); //sort list of grades for convenience

            //assume there is at least one grade
            if (list.size() < 1) { continue; }

            //average
            gradeV[0] = gradeV[0] / list.size(); //just divide the total by list size
            //medium
            if (list.size() > 1) {
                gradeV[1] = list.size() % 2 == 1 ? list.get(list.size() / 2).doubleValue() :
                        (list.get(list.size() / 2).doubleValue() + list.get(list.size() / 2 - 1).doubleValue()) / 2;
            }
            else {
                gradeV[1] = list.get(0).doubleValue();
            }
            //since the list of grades is sorted, we can get high and low just by one call for each
            //low
            gradeV[3] = list.get(0).doubleValue();
            //high
            gradeV[2] = list.get(list.size() - 1).doubleValue();

            grades.put(categoryName, gradeV); //put the result array back to the map
        }

        return grades;

    }
    



    public void createNewWeek(Week week) {

    }

    public Set<Grade> getAssessmentGradesById(int id) {
        return null;
    }

    public void createGrade(Grade grade) {
    }

    public void updateGrade(Grade grade) {
    }




}
