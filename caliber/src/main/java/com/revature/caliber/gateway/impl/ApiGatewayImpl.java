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

@Component
public class ApiGatewayImpl implements ApiGateway {

    @Autowired
    private ServiceLocator serviceLocator;

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



    public Set<Batch> getAllBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchFromCurrentBatchesById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getCurrentBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchByTrainerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Batch> getAllCurrentBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchFromCurrentBatchesById() {
		// TODO Auto-generated method stub
		return null;
	}

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
