package com.revature.caliber.assessments.data.implementations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.data.BatchNoteDAO;

@Repository(value="batchNote")
public class BatchNoteDAOImpl implements BatchNoteDAO {

	private Session session;
	
	@Autowired
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void createBatchNote(int batchId, int weekId) {
		BatchNote note = new BatchNote();
		note.setBatch(batchId);
		note.setWeek(weekId);
		session.saveOrUpdate(note);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public BatchNote getBatchNote(int batchId, int weekId) {
		BatchNote batchNote = (BatchNote) session.createCriteria(BatchNote.class)
				.add(Restrictions.eq("BATCH_ID", batchId))
				.add(Restrictions.eq("WEEK_ID", weekId)).uniqueResult();
		return batchNote;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@SuppressWarnings("unchecked")
	public List<BatchNote> allBatchNotesByWeek(int weekId) {
		List<BatchNote> batchNotes = session.createCriteria(BatchNote.class)
				.add(Restrictions.eq("WEEK_ID", weekId)).list();
		return batchNotes;
	}
	

}
