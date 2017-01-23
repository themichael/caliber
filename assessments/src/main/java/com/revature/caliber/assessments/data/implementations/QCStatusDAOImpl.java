package com.revature.caliber.assessments.data.implementations;


import com.revature.caliber.assessments.data.QCStatusDAO;
import org.springframework.stereotype.Repository;

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
