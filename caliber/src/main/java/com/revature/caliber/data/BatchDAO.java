package com.revature.caliber.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Batch;

@Repository
public class BatchDAO {

	private final static Logger log = Logger.getLogger(BatchDAO.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,
			propagation=Propagation.REQUIRES_NEW)
	public void save(Batch batch){
		log.info("Saving Batch " + batch);
		sessionFactory.getCurrentSession().save(batch);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,
			propagation=Propagation.REQUIRES_NEW)
	public List<Batch> findAll(){
		log.debug("Fetching all batches");
		
		@SuppressWarnings("unchecked")
		Set<Batch> noduplicates = new HashSet<Batch>(sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.list());
		return new ArrayList<Batch>(noduplicates);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,
			propagation=Propagation.REQUIRES_NEW)
	public List<Batch> findAllByTrainer(int trainerId){
		log.debug("Fetching all batches for trainer: " + trainerId);
		
		@SuppressWarnings("unchecked")
		Set<Batch> noduplicates = new HashSet<Batch>(sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.add(Restrictions
						.or(Restrictions.eq("trainer.trainerId", trainerId),
							Restrictions.eq("coTrainer.trainerId", trainerId)))
				.list());
		return new ArrayList<Batch>(noduplicates);
	}
	
}
