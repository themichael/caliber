package com.revature.caliber.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;

/**
 * Provides services and data access
 *
 */
@Service
public class GradeService {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
     * Save Grade if does not already exist
     *  else updates existing record and returns null
     * @param grade
     * @return the id of created Grade, else returns null
     */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Long insertGrade(Grade grade) {
	    if(grade.getGradeId() == 0)
            return (long)sessionFactory.getCurrentSession().save(grade);
        sessionFactory.getCurrentSession().update(grade);
	    return null;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void updateGrade(Grade grade) {
		sessionFactory.getCurrentSession().update(grade);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Grade> getGradesByTraineeId(int traineeId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("trainee", traineeId));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Grade> getGradesByAssessment(long assessmentId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("assessment.assessmentId", assessmentId));
		return criteria.list();

	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void deleteGrade(Grade grade) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("gradeId", grade.getGradeId()));
		Grade gradeToDelete = (Grade) criteria.uniqueResult();
		sessionFactory.getCurrentSession().delete(gradeToDelete);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Grade> getAllGrades() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Grade.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Grade getGradeByGradeId(long gradeId) {
		return (Grade) sessionFactory.getCurrentSession().get(Grade.class, gradeId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public HashMap<Integer, Double> avgGradesOfTrainees() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.avg("score"))
				.add(Projections.groupProperty("trainee"))
				.add(Projections.property("trainee")));
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		List<Object[]> grades = criteria.list();
		for(Object[] grade:grades){
			Double score = (Double) grade[0];
			Integer traineeId = (Integer) grade[1];
			if(!map.containsKey(traineeId)){
				map.put(traineeId, score);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Map<Long, Double> avgGradesOfAssessments() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.avg("score"))
				.add(Projections.groupProperty("assessment.assessmentId"))
				.add(Projections.property("assessment.assessmentId")));
		HashMap<Long, Double> map = new HashMap<>();
		List<Object[]> grades = criteria.list();
		for(Object[] grade:grades){
			Double score = (Double) grade[0];
			Long assessmedId = (Long) grade[1];
			if(!map.containsKey(assessmedId)){
				map.put(assessmedId, score);
			}
		}
		return map;
	}


	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Map<Set<Category>, Double> gradeByCategory(int traineeId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("trainee", traineeId));
		criteria.createAlias("assessment", "a");
		criteria.setProjection(Projections.projectionList()
				.add(Projections.avg("score"))
				.add(Projections.property("a.categories"))
				.add(Projections.groupProperty("a.categories")));		;
		HashMap<Set<Category>, Double> map = new HashMap<>();
		List<Object[]> grades = criteria.list();		
		for(Object[] grade:grades){
			Double score = (Double) grade[0];
			Set<Category> categories=  (Set<Category>) grade[1];
			if(!map.containsKey(categories)){
				map.put(categories, score);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })	
	public Map<Long, Double> gradeByWeek(int traineeId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("trainee", traineeId));
		criteria.createAlias("assessment", "a");
		criteria.setProjection(Projections.projectionList()
				.add(Projections.avg("score"))
				.add(Projections.groupProperty("a.week"))
				.add(Projections.property("a.week")));
		HashMap<Long, Double> map = new HashMap<>();
		List<Object[]> grades = criteria.list();
		for(Object[] grade:grades){
			Double score = (Double) grade[0];
			Long weekId = (Long) grade[1];
			if(!map.containsKey(weekId)){
				map.put(weekId, score);
			}
		}
		return map;
	}

	
}
