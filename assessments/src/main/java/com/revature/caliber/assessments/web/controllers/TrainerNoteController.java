package com.revature.caliber.assessments.web.controllers;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = { "*" },
        methods = { RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE },
        allowedHeaders = { "X-PINGOTHER", "Content-Type" },
        maxAge = 10)
public class TrainerNoteController {
    private BusinessDelegate delegate;

    //Spring setter based DI
    @Autowired
    public void setDelegate(BusinessDelegate delegate) {
        this.delegate = delegate;
    }

    //Create
    //POST is used over PUT since we are not specifying specific trainerNote url
    @RequestMapping(
            value = "/trainerNote/new",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAssessment(@RequestBody TrainerNote trainerNote) {
        delegate.createTrainerNote(trainerNote);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // getAllTrainerNotesByTrainer
    @RequestMapping(
            value = "/trainerNote/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TrainerNote>> getAllNotesByTrainer (@PathVariable("id") int id) {
        Set<TrainerNote> trainerNote = delegate.getAllNotesByTrainer(id);
        if (trainerNote == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Set<TrainerNote>>(trainerNote, HttpStatus.OK);
    }
/*
    // getTrainerNoteForWeek
    @RequestMapping(
            value = "/trainerNote/{id}/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TrainerNote>> getTrainerNoteForWeek (@PathVariable("trainerId") int trainerId, @PathVariable("weekId") int weekId) {
        Set<TrainerNote> trainerNote = delegate.getTrainerNoteForWeek(trainerId, weekId);
        if (trainerNote == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Set<TrainerNote>>(trainerNote, HttpStatus.OK);
    }*/
}
