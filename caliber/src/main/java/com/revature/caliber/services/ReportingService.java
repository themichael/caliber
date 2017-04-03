package com.revature.caliber.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.intel.ClassroomStatisticalAnalysisBean;

/**
 * Exclusively used to generate data for charts
 * 
 * Provides logic concerning grade and aggregated data sets. Application logic
 * has no business being in a DAO nor in a Controller. This is the ideal place
 * for calculations
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class ReportingService //implements ClassroomStatisticalAnalysisBean
{
	private final static Logger log = Logger.getLogger(ReportingService.class);
	private GradeDAO gradeDAO;

	@Autowired
	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	/**
	 * Get aggregated grades by Category for a Trainee
	 *
	 * @param traineeId
	 * @return
	 */
	public HashMap<Trainee, Double[]> aggregateTechTrainee(@PathVariable("id") int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades by Week for a Trainee
	 *
	 * @param traineeId
	 * @return
	 */

	public HashMap<Trainee, Double[]> aggregateWeekTrainee(int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades by Category for a Batch
	 *
	 * @param batchId
	 * @return
	 */
	public HashMap<Batch, Double[]> aggregateTechBatch(int batchId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades by Category for a Batch
	 *
	 * @param traineeId
	 * @return
	 */
	public HashMap<Batch, Double[]> aggregateWeekBatch(int batchId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades for all Trainees by Trainer
	 *
	 * @param traineeId
	 * @return
	 */
	public Map<String, Double[]> aggregateTraineesTrainer(int trainerId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public Map<Trainee, Double> findAvgGradesForEachTrainee() {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public Map<Assessment, Double> findAvgGradesForEachAssessment() {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public Map<Category, Double> findAvgGradeByCategory(int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public Map<Integer, Double> findAvgGradeByWeek(int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}
}