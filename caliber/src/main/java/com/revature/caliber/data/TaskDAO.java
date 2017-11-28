package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.TrainerTask;
import com.revature.caliber.beans.TrainerTaskCompletion;

@Repository
public class TaskDAO {

	private static final Logger log = Logger.getLogger(TaskDAO.class);
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Find all active tasks
     */
    @SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<TrainerTask> findAllActiveTasks() {
    	log.info("Finding all active tasks");
    	return sessionFactory.getCurrentSession().createCriteria(TrainerTask.class)
                .add(Restrictions.eq("active", true)).addOrder(Order.asc("priority")).list();
    }
    
}
