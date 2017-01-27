package com.revature.caliber.gateway.impl;

import com.revature.caliber.beans.*;
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

    public List<Batch> currentBatch(Trainer trainer) {
        return serviceLocator.getTrainingService().currentBatch(trainer);
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



    @Override
    public HashMap<String, Double[]> getAggregatedGradesForTrainee(int id) {
        List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(id);
        HashMap<String,Double[]> grades = new HashMap<>();
        HashMap<String, List<Integer>> gradeValues = new HashMap<>(); //get grade values

        for ( Grade grade : allGrades) {
            List<Category> catList = grade.getAssessment().getCategories().stream().collect(Collectors.toList());
            if (catList.size() < 1) { continue; }
            Category category = catList.get(0);

            if (!grades.containsKey(category.getSkillCategory())) {
                grades.put(category.getSkillCategory(), new Double[] {0.0, 0.0, 0.0, 0.0});
                gradeValues.put(category.getSkillCategory(), new ArrayList<>());
            }

            grades.get(category.getSkillCategory())[0] += grade.getScore();
            gradeValues.get(category.getSkillCategory()).add(grade.getScore());
        }

        for (String categoryName : grades.keySet()) {
            Double [] gradeV = grades.get(categoryName);
            List<Integer> list = gradeValues.get(categoryName);
            list.sort(Integer::compareTo);

            if (list.size() < 1) { continue; }

            //average
            gradeV[0] = gradeV[0] / list.size();
            //medium
            if (list.size() > 1) {
                gradeV[1] = list.size() % 2 == 1 ? list.get(list.size() / 2).doubleValue() :
                        (list.get(list.size() / 2).doubleValue() + list.get(list.size() / 2 - 1).doubleValue()) / 2;
            }
            else {
                gradeV[1] = list.get(0).doubleValue();
            }
            //high
            gradeV[3] = list.get(0).doubleValue();
            //low
            gradeV[2] = list.get(list.size() - 1).doubleValue();

            //System.out.println(categoryName + " - " + gradeV[0] + " " + gradeV[1] + " " + gradeV[2] + " " + gradeV[3]);

            grades.put(categoryName, gradeV);
        }

        return grades;

    }
}
