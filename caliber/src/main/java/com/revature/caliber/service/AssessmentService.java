package com.revature.caliber.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;

/**
 * Provides services and data access
 *
 */
@Service
public class AssessmentService {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Category getCategory(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		return (Category) session.get(Category.class, categoryId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Category> getAllCategories() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
		return criteria.list();
	}

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    public Assessment getById(long id) {
        return (Assessment) sessionFactory.getCurrentSession().get(Assessment.class, id);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    @SuppressWarnings("unchecked")
    public Set<Assessment> getAll() {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(Assessment.class).list());
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    @SuppressWarnings("unchecked")
    public Set<Assessment> getByWeek(Batch batch, short week) {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(Assessment.class)
                        .add(Restrictions.eq("batch.batchId", batch.getBatchId()))
                        .add(Restrictions.eq("week", week)).list());
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    public long insert(Assessment assessment) {
        return (Long) sessionFactory.getCurrentSession().save(assessment);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    public void update(Assessment assessment) {
        sessionFactory.getCurrentSession().update(assessment);

    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    public void delete(Assessment assessment) {
        sessionFactory.getCurrentSession().delete(assessment);
    }
	
}
