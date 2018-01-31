package com.revature.service;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Assessment;
//import com.revature.caliber.data.AssessmentDAO;
@Service
public class AssessmentService {

	private static final Logger log = Logger.getLogger(AssessmentService.class);
	//private AssessmentDAO assessmentDAO;
	
	/*
	@Autowired
	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}
	*/

	/*
	 *******************************************************
	 *	ASSESSMENT SERVICES
	 *
	 *******************************************************
	 */
	
	/**
	 * SAVE ASSESSMENT
	 * @param assessment
	 */
	public void save(Assessment assessment) {
		/*
		log.debug("Saving assessment " + assessment);
		assessmentDAO.save(assessment);
		*/
	}

	/**
	 * FIND ASSESSMENT BY ID
	 * @param assessmentId
	 * @return
	 */
	public Assessment findAssessment(long assessmentId) {
		/*
		log.debug("Finding one assessment " + assessmentId);
		return assessmentDAO.findOne(assessmentId);
		*/
		return null;
	}

	/**
	 * FIND ALL ASSESSMENTS
	 * @return
	 */
	public List<Assessment> findAllAssessments() {
		/*
		log.debug("Find all assessments");
		return assessmentDAO.findAll();
		*/
		return null;
	}

	/**
	 * FIND ASSESSMENT BY WEEK
	 * @param batch
	 * @param week
	 * @return
	 */
	public List<Assessment> findAssessmentByWeek(Integer batchId, Integer week) {
		/*
		log.debug("Find assessment by week number " + week + " for batch " + batchId + " ");
		return assessmentDAO.findByWeek(batchId, week);
		*/
		return null;
	}
	
	/**
	 * FIND ASSESSMENT BY BATCHID
	 * @param 
	 * @return
	 */
	public List<Assessment> findAssessmentByBatchId(Integer batchId) {
		/*
		log.debug("Find assessment by batchId " + batchId + " ");
		return assessmentDAO.findByBatchId(batchId);
		*/
		return null;
	}

	/**
	 * UPDATE ASSESSMENT
	 * @param assessment
	 */
	public Assessment update(Assessment assessment) {
		/*
		log.info("Updating assessment " + assessment);
		
		assessmentDAO.update(assessment);*/
		return null;
	}

	/**
	 * DELETE ASSESSMENT
	 * @param assessment
	 */
	public void delete(Assessment assessment) {
		/*
		log.debug("Deleting assessment " + assessment);
		//load assessment into persistent state
		Assessment record = assessmentDAO.findOne(assessment.getAssessmentId());
		assessmentDAO.delete(record);
		*/
		
	}

}
