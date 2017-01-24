package com.revature.caliber.assessments.data.implementations;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.caliber.assessments.data.QCStatusDAO;

@Repository(value = "qcStatusDAO")
public class QCStatusDAOImpl implements QCStatusDAO {

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void getAll() {

	}

	@Override
	public void getByID(short statusId) {

	}

}
