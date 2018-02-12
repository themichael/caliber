package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.Grade;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.GradeRepository;
@Service
public class GradeCompositionService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeCompositionMessagingService gradeCompositionMessagingService;

    /**
     * saves the grade in the table
     * 
     * @param save
     * @return
     */
    public void save(Grade grade) {
        SimpleGrade sGrade = new SimpleGrade(grade);
        gradeRepository.save(sGrade);
    }

    /**
     * updates the grade in the table
     * 
     * @param grade
     * @return
     */
    public void update(Grade grade) {
        save(grade);
    }

    /**
     * Returns a specific Grade given a grade Id. Not called directy, and is
     * mainly used as a helper method.
     * 
     * @param gradeId
     * @return
     */
    public Grade findOne(Long gradeId) {
        SimpleGrade basis = gradeRepository.findOne(gradeId);
        Grade result = composeGrade(basis);
        return result;
    }

    /**
     * Returns absolutely all grades for only the most coarsely-grained
     * reporting. Useful for feeding data into application for statistical
     * analyses, such as regression analysis, calculating mean, and finding
     * average ;)
     * 
     * @param traineeId
     * @return
     */
    public List<Grade> findAll(Long gradeId) {
        List<SimpleGrade> basis = gradeRepository.findAll();
        List<Grade> result = composeListOfGrade(basis);
        return result;
    }

    /**
     * Returns grades for all trainees that took a particular assignment. Great
     * for finding average/median/highest/lowest grades for a test
     * 
     * @param assessmentId
     * @return
     */
    public List<Grade> findByAssessment(Long assessmentId) {
        List<SimpleGrade> basis = gradeRepository.findByAssessmentId(assessmentId);
        List<Grade> result = composeListOfGrade(basis);
        return result;
    }

    /**
     * Returns all grades for a trainee. Useful for generating a full-view of
     * individual trainee performance.
     * 
     * @param traineeId
     * @return
     */
    public List<Grade> findByTrainee(Integer traineeId) {
        List<SimpleGrade> basis = gradeRepository.findByTraineeId(traineeId);
        List<Grade> result = composeListOfGrade(basis);
        return result;
    }

    /**
     * Returns all grades for a batch. Useful for calculating coarsely-grained
     * data for reporting.
     * 
     * @param batchId
     * @return
     */
    public List<Grade> findByBatch(Integer batchId) {
        List<SimpleTrainee> trainees = gradeCompositionMessagingService.sendSimpleFindAllByBatchIdRequest(batchId);                                                                                                     
        System.out.println(trainees);  
        List<Grade> part = null;
        List<Grade> result = new ArrayList<Grade>();
        for (int i = 0; i < trainees.size(); i++) {
            part = findByTrainee(trainees.get(i).getTraineeId());
            result.addAll(part);
        }
        return result;
    }

    /**
     * Returns all grades for a category. Useful for improving performance time
     * of company-wide reporting
     * 
     * @param batchId
     * @return
     */
    public List<Grade> findByCategory(Integer categoryId) {
        List<SimpleAssessment> catAssessments = gradeCompositionMessagingService.sendListAssessmentRequest(categoryId);
        List<SimpleGrade> sGrades = new ArrayList<SimpleGrade>();

        for (SimpleAssessment a : catAssessments) {
            sGrades.addAll(gradeRepository.findByAssessmentId(a.getAssessmentId()));
        }

        return composeListOfGrade(sGrades);
    }

    /**
     * Returns grades for all trainees in the batch on a given week. Used to
     * load grade data onto the input spreadsheet, as well as tabular/chart
     * reporting.
     * 
     * @param batchId
     * @param week
     * @return
     */
    public List<Grade> findByWeek(Integer batchId, Integer week) {
        List<Grade> grades = new ArrayList<Grade>();
        List<SimpleTrainee> trainees = gradeCompositionMessagingService.sendSimpleFindAllByBatchIdRequest(batchId); // get
                                                                                                                    // list
                                                                                                                    // of
                                                                                                                    // trainees
                                                                                                                    // belonging
                                                                                                                    // to
                                                                                                                    // the

        for (SimpleTrainee trainee : trainees) {
            if (trainee.getTrainingStatus() != TrainingStatus.Dropped) {
                List<Grade> traineeGrades = findByTrainee(trainee.getTraineeId());
                for (Grade grade : traineeGrades) {
                    if (grade.getAssessment() != null) {
                        if (grade.getAssessment().getWeek() == week.shortValue()) {
                            grades.add(grade);
                        }
                    }
                }
            }
        }

        return grades;
    }

    /**
     * Returns all grades issued as acting trainer or cotrainer to a batch.
     * Useful for calculating coarsely-grained data for reporting. Potential
     * refactor here.. this queries database twice where we could find way to
     * simply join.
     * 
     * @param trainerId
     * @return
     */
    public List<Grade> findByTrainer(Integer trainerId) {
        return null;
    }

    private List<Grade> composeListOfGrade(List<SimpleGrade> src) {
        List<Grade> dest = new LinkedList<Grade>();

        for (SimpleGrade curr : src) {
            Grade grade = composeGrade(curr);
            dest.add(grade);
        }

        return dest;
    }

    /**
     * Takes in a Simple grade and converts it into a Complex Grade by making
     * requests to Trainee and Assessment.
     * 
     * @param src
     * @return
     */
    private Grade composeGrade(SimpleGrade src) {
        SimpleTrainee simpleTrainee = gradeCompositionMessagingService.sendSimpleTraineeRequest(src.getTraineeId());
        if (simpleTrainee == null) {
            System.out.println("simpletrainee is null");
        }
        SimpleAssessment simpleAssessment = gradeCompositionMessagingService.sendSimpleAssessmentRequest(src.getAssessmentId());

        Trainee trainee = new Trainee(simpleTrainee);
        Assessment assessment = new Assessment(simpleAssessment);

        Grade dest = new Grade(src);

        dest.setAssessment(assessment);
        dest.setTrainee(trainee);

        return dest;
    }

    /**
     * Finds grades in a given week and batch id.
     * 
     * @param batchId,
     *            week
     * @return
     */

    public Map<Integer, List<Grade>> findGradesByWeek(Integer batchId, Integer week) {
        List<Grade> grades = findByWeek(batchId, week);
        Map<Integer, List<Grade>> table = new HashMap<>();
        
        for (Grade grade : grades) {
            Integer key = grade.getTrainee().getTraineeId();
            
            if (table.containsKey(key)) {
                table.get(key).add(grade);
            } else {
                List<Grade> assessments = new ArrayList<>();
                assessments.add(grade);
                table.put(key, assessments);
            }
        }

        return table;
    }

    public void saveOrUpdateGradeFromDTO(long assessmentId, double score, String traineeResourceId) {
        SimpleTrainee sTrainee = gradeCompositionMessagingService.sendSimpleTraineeRequest(traineeResourceId);
        SimpleGrade sGrade = new SimpleGrade();

        sGrade.setTraineeId(sTrainee.getTraineeId());
        sGrade.setAssessmentId(assessmentId);
        sGrade.setScore(score);

        gradeRepository.save(sGrade);
    }

}
