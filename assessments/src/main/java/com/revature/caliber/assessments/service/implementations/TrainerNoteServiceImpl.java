package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.TrainerNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "trainerNoteService")
public class TrainerNoteServiceImpl implements TrainerNoteService {


    private Facade facade;

    @Autowired
    public void setFacade(Facade facade) {
        this.facade = facade;
    }


    @Override
    public void createTrainerNote(int trainerId) {
        facade.makeTrainerNote(trainerId);
    }

    @Override
    public List<TrainerNote> getAllNotesByTrainer(int trainerId) {
        return facade.listTrainerNotes(trainerId);
    }

    @Override
    public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {
        return facade.getTrainerNoteForWeek(trainerId, weekId);
    }

}
