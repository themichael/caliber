package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.data.GradeDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for Grade DAO crud methods
 */
@Repository(value = "gradeDAO")
public class GradeDAOImpl implements GradeDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void insertGrade(Grade grade) {
		sessionFactory.getCurrentSession().saveOrUpdate(grade);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void updateGrade(Grade grade) {
		sessionFactory.getCurrentSession().update(grade);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Grade> getGradesByTraineeId(Integer traineeId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("trainee", traineeId));
		return criteria.list();
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Grade> getGradesByAssesessment(Integer assessmentId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("assessment", assessmentId));
		return criteria.list();

	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void deleteGrade(Grade grade) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Grade.class);
		criteria.add(Restrictions.eq("gradeId", grade));
		Grade gradeToDelete = (Grade) criteria.uniqueResult();
		sessionFactory.getCurrentSession().delete(gradeToDelete);
	}


	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Grade> getAllGrades() {
		return new ArrayList<>(sessionFactory.getCurrentSession().createCriteria(Grade.class).list());
	}

	@Override	
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Grade getGradeByGradeId(Integer gradeId) {
		return (Grade) sessionFactory.getCurrentSession().get(Grade.class, gradeId);
	}

}