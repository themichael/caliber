package com.revature.caliber.assessments.data.implementations;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.data.BatchNoteDAO;

@Repository(value="batchNote")
public class BatchNoteDAOImpl implements BatchNoteDAO {

	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void createBatchNote(int batchId, int weekId) {
		BatchNote note = new BatchNote();
		note.setBatch(batchId);
		note.setWeek(weekId);
		session.saveOrUpdate(note);
	}

	@Override
	public BatchNote getBatchNote(int batchId, int weekId) {
		BatchNote batchNote = (BatchNote) session.createCriteria(BatchNote.class)
				.add(Restrictions.eq("BATCH_ID", batchId))
				.add(Restrictions.eq("WEEK_ID", weekId)).uniqueResult();
		return batchNote;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashSet<BatchNote> allBatchNotesByWeek(int weekId) {
		HashSet<BatchNote> batchNotes = (HashSet<BatchNote>) session.createCriteria(BatchNote.class)
				.add(Restrictions.eq("WEEK_ID", weekId)).list();
		return batchNotes;
	}
	

}
