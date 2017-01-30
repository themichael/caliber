package com.revature.caliber.gateway.impl;

import com.revature.caliber.beans.*;
import com.revature.caliber.gateway.ApiGateway;
import com.revature.caliber.gateway.services.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /****************************Trainer*******************************/
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
    @Override
    public List<Batch> getAllBatches() {
        return serviceLocator.getTrainingService().allBatch();
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
     * Aggregate grades by all tech for a Trainee // param - traineeId
     * - HashMap
     * - key Tech(Category)
     * - value double array
     * - average
     * - median
     * - high
     * - low
     * Key: REST, Value: [83.54, 78.56, 90.56, 78.56]
     * Key: SOAP, Value: [83.54, 78.56, 90.56, 78.56]
     *
     * @param id the id
     * @return something
     */

    @Override
    public HashMap<String, Double[]> getTechGradeDataForTrainee(int id) {
        List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(id); //grade data that we get from assessment module
        HashMap<String, Double[]> grades = new HashMap<>(); //our result map
        HashMap<String, List<Integer>> gradeValues = new HashMap<>(); //get grade values
        //processing
        for (Grade grade : allGrades) {
            List<Category> catList = grade.getAssessment().getCategories().stream().collect(Collectors.toList());
            if (catList.size() < 1) {
                continue;
            }
            Category category = catList.get(0); //assume there is only one category per assessment

            //map does not have the key yet
            if (!grades.containsKey(category.getSkillCategory())) {
                grades.put(category.getSkillCategory(), new Double[]{0.0, 0.0, 0.0, 0.0});
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
            Double[] gradeV = grades.get(categoryName);
            List<Integer> list = gradeValues.get(categoryName);
            list.sort(Integer::compareTo); //sort list of grades for convenience

            //assume there is at least one grade
            if (list.size() < 1) {
                continue;
            }

            //average
            gradeV[0] = gradeV[0] / list.size(); //just divide the total by list size
            //medium
            if (list.size() > 1) {
                gradeV[1] = list.size() % 2 == 1 ? list.get(list.size() / 2).doubleValue() :
                        (list.get(list.size() / 2).doubleValue() + list.get(list.size() / 2 - 1).doubleValue()) / 2;
            } else {
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

    /**
     * // Shehar
     * Aggregate grades per week for a Batch // param - batchId
     * - HashMap
     * - key week
     * - value double array
     * - average
     * - median
     * - high
     * - low
     * Key: Week 1, Value: [83.54, 78.56, 90.56, 78.56]
     * Key: Week 2, Value: [83.54, 78.56, 90.56, 78.56]           etc.
     *
     * @param batchID A batch id
     * @return grades
     */
    @Override
    public HashMap<String, Double[]> getGradesForBatchWeekly(int batchID) {
        List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(batchID); //grade data that we get from assessment module
        HashMap<String, Double[]> grades = new HashMap<>(); //our result map
        HashMap<String, List<Integer>> gradeValues = new HashMap<>(); //get grade values
        Double[] gradeV;
        List<Integer> list;
        int highestWeek = 0;
        for (Grade grade : allGrades) {
            if (grade.getAssessment().getWeek().getWeekNumber() > highestWeek) {
                highestWeek = grade.getAssessment().getWeek().getWeekNumber();
            }
            Week weekNumber = grade.getAssessment().getWeek();
            if (!grades.containsKey(weekNumber.getWeekNumber())) {
                grades.put(String.valueOf(weekNumber.getWeekNumber()), new Double[]{0.0, 0.0, 0.0, 0.0});
                gradeValues.put(String.valueOf(weekNumber.getWeekNumber()), new ArrayList<>());
            }
        }
        //find how many weeks
        int[] weeks = new int[highestWeek];
        //add all the grades
        int[] gradeTotal = new int[highestWeek];
        //grade average holder
        int[] gradeAverage = new int[highestWeek];
        //computation
        for (Grade grade : allGrades) {
            Week weekNumber = grade.getAssessment().getWeek();
            weeks[grade.getAssessment().getWeek().getWeekNumber() - 1] += 1;
            gradeTotal[grade.getAssessment().getWeek().getWeekNumber() - 1] += grade.getScore();
            gradeValues.get(String.valueOf(weekNumber.getWeekNumber())).add(grade.getScore());
            for (String weekName : grades.keySet()) {
                gradeV = grades.get(weekName);
                list = gradeValues.get(weekName);
                list.sort(Integer::compareTo); //sort list of grades for convenience
                //assume there is at least one grade
                if (list.size() < 1) {
                    continue;
                }
                if (list.size() > 1) {
                    gradeV[1] = list.size() % 2 == 1 ? list.get(list.size() / 2).doubleValue() :
                            (list.get(list.size() / 2).doubleValue() + list.get(list.size() / 2 - 1).doubleValue()) / 2;
                } else {
                    gradeV[1] = list.get(0).doubleValue();
                }
                gradeV[3] = list.get(0).doubleValue();
                gradeV[2] = list.get(list.size() - 1).doubleValue();
                grades.put(weekName, gradeV); //put the result array back to the map
            }
        }
        for (int i = 0; i < gradeAverage.length; i++) {
            gradeAverage[i] = gradeTotal[i] / weeks[i];
            grades.get(String.valueOf(i + 1))[0] = Double.valueOf(gradeAverage[i]);

        }
        return grades;
    }


    /**
     * Create new week.
     *
     * @param week the week
     */
    public void createNewWeek(Week week) {

    }

    /**
     * Gets assessment grades by id.
     *
     * @param id the id
     * @return the assessment grades by id
     */
    public Set<Grade> getAssessmentGradesById(int id) {
        return null;
    }

    /**
     * Create grade.
     *
     * @param grade the grade
     */
    public void createGrade(Grade grade) {
    }

    @Override
    public void createAssessmentNote(Note note) {

    }

    public void updateGrade(Grade grade) {
    }

    /**
     * Gets week grade data for trainee init.
     *
     * @param id the id
     * @return the week grade data for trainee init
     */
//@Override
    public HashMap<String, Double[]> getWeekGradeDataForTraineeInit(int id) {
        List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(id);
        HashMap<String, Double[]> grades = new HashMap<>();//result
        List<Week> week = serviceLocator.getTrainingService().getAllWeek();
        for (Grade grade : allGrades) {
//			int weeknum = grade.getAssessment().getWeek().getWeekNumber(); //Should get back a weeknum which should be unique for all
            long assessmentId = grade.getAssessment().getAssessmentId();
            //Assessment assessment =serviceLocator.getAssessmentService().getAssessmentById();
            System.out.println(grade.getAssessment());
//			if(!grades.containsKey(weeknum)){
//				grades.put(Integer.toString(weeknum), new Double[] {0.0,0.0,0.0,0.0});
//			}
        }
        return grades;
    }

    @Override
    public HashMap<String, Double[]> getWeekGradeDataForTrainee(int id) {
        List<Week> weeks = serviceLocator.getTrainingService().getAllWeek();
        HashMap<String, Double[]> grades = new HashMap<>();
        List<Grade> allGrades = serviceLocator.getAssessmentService().getGradesByTraineeId(id);

        for (Week week : weeks) {
            List<Integer> scores = new ArrayList<>();
            for (Grade grade : allGrades) {
                for (Assessment assessment : week.getAssessments()) {
                    if (week.getWeekId() == assessment.getWeek().getWeekId()) {
                        scores.add(grade.getScore());
                    }
                }
//				if(week.getWeekId()==grade.getAssessment().getWeek().getWeekId()){//TODO this won't work because it'll give null ptr
//					
//					scores.add(grade.getScore());
//				}
            }
            //aggregate functions here
            Collections.sort(scores);
            Double[] aggregates = new Double[4];
            aggregates[0] = getAverage(scores);
            aggregates[1] = getMedian(scores);
            aggregates[2] = (double) scores.get(0);
            aggregates[3] = (double) scores.get(scores.size() - 1);
            if (scores.size() > 0) {
                grades.put(Integer.toString(week.getWeekNumber()), aggregates);
            }
        }
        return grades;
    }

    //TODO gonna be kinda funky because it's a set of categories not one category
    @Override
    public HashMap<String, Double[]> getTechGradeDataForBatch(int batchId) {
        Set<Category> categories = serviceLocator.getAssessmentService().getAllCategories();
        HashMap<String, Double[]> grades = new HashMap<>();
        for (Category category : categories) {
            //get one category
            List<Grade> bgrades = null; //serviceLocator.getAssessmentService().getAllGrades();
            List<Integer> scores = new ArrayList<>();
            for (Grade grade : bgrades) {
                int traineeId = grade.getTrainee().getTraineeId(); //TODO Fix this will probably get null pointer
                Trainee t = serviceLocator.getTrainingService().getTrainee(traineeId);
                if (t.getBatch().getBatchId() == batchId && grade.getAssessment().getCategories() == category) {
                    scores.add(grade.getScore());
                }
            }
            Double[] aggregates = new Double[2];
            aggregates[0] = getAverage(scores);
            aggregates[1] = getMedian(scores);
            if (scores.size() > 1) {
                grades.put(category.getSkillCategory(), aggregates);
            }

        }
        return grades;
    }

    @Override
    public Map<String, Double[]> getTraineeGradeDataForTrainer(int trainerId) {
        List<Trainee> trainees = null;//serviceLocator.getTrainingService().getTraineesByTrainer(?)/getallTrainees
        HashMap<String, Double[]> grades = new HashMap<>();
        for (Trainee trainee : trainees) {
            List<Grade> tgrades = serviceLocator.getAssessmentService().getGradesByTraineeId(trainee.getTraineeId());
            List<Integer> scores = new ArrayList<>();
            for (Grade grade : tgrades) {
                scores.add(grade.getScore());
            }

            //agregate here
            Double[] aggregates = new Double[2];
            aggregates[0] = getAverage(scores);
            aggregates[1] = getMedian(scores);
            if (scores.size() > 1) {
                grades.put(trainee.getName(), aggregates);
            }

        }
        return grades;
    }

    /**
     * Gets average.
     *
     * @param list the list
     * @return the average
     */
    public double getAverage(List<Integer> list) {
        int sum = 0;
        for (int nums : list) {
            sum += nums;
        }
        double avg = sum / list.size();
        return avg;
    }

    /**
     * Gets median.
     *
     * @param list the list
     * @return the median
     */
    public double getMedian(List<Integer> list) {
        int middle = list.size() / 2;
        if (list.size() % 2 == 1) {
            return list.get(middle);
        } else {
            return (list.get(middle - 1) + list.get(middle - 2)) / 2;
        }

    }


    /**
     * Create assessment.
     *
     * @param assessment the assessment
     */
    public void createAssessment(Assessment assessment) {
    }

    /**
     * Gets all assessments.
     *
     * @return the all assessments
     */
    public Set<Assessment> getAllAssessments() {
        return null;
    }

    /**
     * Update assessment note.
     *
     * @param note the note
     */
    public void updateAssessmentNote(Note note) {

    }
}
