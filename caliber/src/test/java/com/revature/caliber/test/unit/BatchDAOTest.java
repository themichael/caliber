package com.revature.caliber.test.unit;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.data.BatchDAO;

public class BatchDAOTest extends CaliberTest{
	
	private static final Logger log = Logger.getLogger(BatchDAOTest.class);
	
	@Autowired
	@InjectMocks
	private BatchDAO batchDAO;
	
	@Mock
	private SessionFactory mockSessionFactory;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private Session mockSession;
	@Mock//(answer = Answers.RETURNS_DEEP_STUBS)
	private Criteria mockCriteria;
	//@Mock
	//private List batches;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
    }

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrent(trainerId)
	 */
	@Test
	public void findAllCurrentIntTest(){
		log.info("Testing the BatchDAO.findAllCurrent(trainerId)");
		List<Batch> list = new ArrayList<Batch>();
		list.add(new Batch());
		log.info(list);
		//Mockito.when(methodCall)
		doReturn(mockSession).when(mockSessionFactory).getCurrentSession();
		when(mockSession.createCriteria(Batch.class).createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", 1),Restrictions.eq("coTrainer.trainerId", 1)))
				.add(Restrictions.le("startDate", Calendar.getInstance().getTime()))
				.add(Restrictions.ge("endDate", Calendar.getInstance().getTime())).addOrder(Order.desc("startDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(mockCriteria);
		when(mockCriteria.list()).thenReturn(list);
		
		List<Batch> result = batchDAO.findAllCurrent(1);
		log.info(result);
		assertArrayEquals(list.toArray(),result.toArray());
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithNotes()
	 */
	@Test
	public void findAllCurrentWithNotesTest(){
		
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithNotesAndTrainees()
	 */
	@Test
	public void findAllCurrentWithNotesAndTraineesTest(){
		
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithTrainees()
	 */
	@Test
	public void findAllCurrentWithTraineesTest(){
		
	}
}
