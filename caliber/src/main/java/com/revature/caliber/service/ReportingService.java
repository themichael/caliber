package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;

@Service
public class ReportingService {
	
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public HashMap<String, Double[]> getWeekGradeDataForTrainee(int traineeId) {
        List<Grade> allGrades = getGradesByTraineeId(traineeId);
        short numberOfWeeks = getNumberOfWeeksInBatch(traineeId);
        HashMap<String, Double[]> grades = new HashMap<>();
        // for each week
        for (int week=1; week<=numberOfWeeks; week++) {
            List<Integer> scores = new ArrayList<>();
            //get the grades for that week
            for (Grade grade : allGrades) {
                if (week == grade.getAssessment().getWeek()) {
                        scores.add(grade.getScore());
                    }
                }
            if (scores.size() < 1) { continue; }
            Collections.sort(scores);
            Double[] aggregates = new Double[4];
            aggregates[0] = getAverage(scores);
            aggregates[1] = getMedian(scores);
            aggregates[3] = (double) scores.get(0);
            aggregates[2] = (double) scores.get(scores.size() - 1);
            if (scores.size() > 0) {
                grades.put(Integer.toString(week), aggregates);
            }
        }
        return grades;
    }

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
    public short getNumberOfWeeksInBatch(int traineeId) {
    	String hql = "select weeks from Batch "
    			+ "inner join Trainee as T where T.traineeId = :id";
    	short weeks = (short) sessionFactory.getCurrentSession()
    			.createQuery(hql)
    			.setParameter("id", traineeId)
    			.uniqueResult();
		return weeks;
	}


	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@SuppressWarnings("unchecked")
	public List<Grade> getGradesByTraineeId(int traineeId) {
		return sessionFactory.getCurrentSession().createCriteria(Grade.class)
			.add(Restrictions.eq("trainee.traineeId", traineeId)).list();
	}


	public HashMap<String, Double[]> getTechGradeDataForBatch(int batchId) {
    	List<Grade> allGrades = getAllGrades();
    	HashMap<String,Double[]> grades = new HashMap<>(); //our result map
        HashMap<String, List<Integer>> gradeValues = new HashMap<>(); //get grade values
        //processing
        for (Grade grade : allGrades) {
        	if(batchId == grade.getAssessment().getBatch().getBatchId()){
            Category category = grade.getAssessment().getCategory();
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
            //since the list of grades is sorted, we can get high and low just by one call for each
            gradeV[1] = getMedian(list);
            //high
            gradeV[2] = list.get(list.size() - 1).doubleValue();
            //low
            gradeV[3] = list.get(0).doubleValue();

            grades.put(categoryName, gradeV); //put the result array back to the map
        }
        return grades;

    	
    }

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
    @SuppressWarnings("unchecked")
	public List<Grade> getAllGrades() {
		return sessionFactory.getCurrentSession().createCriteria(Grade.class).list();
	}


	public Map<String, Double[]> getTraineeGradeDataForTrainer(int trainerId) {
        List<Trainee> trainees = getTraineesByTrainer(new Long(trainerId));
        List<Grade> allGrades = getAllGrades();
        HashMap<String, Double[]> grades = new HashMap<>();
        for (Trainee trainee : trainees) {
            List<Grade> tgrades = new ArrayList<>();
            for (Grade gr : allGrades) {
                if (gr.getTrainee().getTraineeId() == trainee.getTraineeId()) {
                    tgrades.add(gr);
                }
            }
            List<Integer> scores = new ArrayList<>();
            for (Grade grade : tgrades) {
                scores.add(grade.getScore());
            }

            //aggregate here
            if(scores.size()<1){
            	continue;
            }
            Collections.sort(scores);
            Double[] aggregates = new Double[2];
            aggregates[0] = getAverage(scores);
            aggregates[1] = getMedian(scores);
            grades.put(trainee.getName(), aggregates);

        }
        return grades;
    }
     
    @SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
    public List<Trainee> getTraineesByTrainer(Long trainerId) {
		return sessionFactory.getCurrentSession().createCriteria(Trainee.class)
			.add(Restrictions.or(
					Restrictions.eq("batch.trainer.trainerId", trainerId),
					Restrictions.eq("batch.coTrainer.trainerId", trainerId)))
			.list();
	}


	public HashMap<String,HashMap<String,Double[]>> getTechGradeAllBatch(){
        List<Grade> allGrades = getAllGrades();
        HashMap<String,HashMap<String,Double[]>> grades = new HashMap<>(); //our result map
        HashMap<String, Double[]> techGrade = new HashMap<>();

        Set<Integer> batchIds = new TreeSet<>();

        for (Grade grade : allGrades) {
            batchIds.add(grade.getAssessment().getBatch().getBatchId());
        }
        for (Integer batchId : batchIds) {
            if(!grades.containsKey(batchId)){
                techGrade = getTechGradeDataForBatch(batchId);
                grades.put(String.valueOf(batchId),techGrade);
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
        double avg = (double)sum / list.size();
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
            return (double)((list.get(middle - 1) + list.get(middle)) / 2);
        }

    }
	
}
