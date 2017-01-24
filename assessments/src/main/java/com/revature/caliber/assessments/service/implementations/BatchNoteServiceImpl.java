package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.BatchNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "batchNoteService")
class BatchNoteServiceImpl implements BatchNoteService {

    private Facade facade;

    @Autowired
    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    @Override
    public void createBatchNote(BatchNote batchNote) {
        facade.makeBatchNote(batchNote);
    }

    @Override
    public BatchNote weeklyBatchNote(int batchId, int weekId) {
        return facade.getWeeklyBatchNote(batchId, weekId);
    }

    @Override
    public List<BatchNote> allBatchNotesInWeek(int weekId) {
        return facade.allBatchNotesInWeek(weekId);
    }

	@Override
	public void updateBatchNote(BatchNote batchNote) {
		facade.updateBatchNote(batchNote);
		
	}

}
