package com.revature.caliber.assessments.web.controllers;

import com.revature.caliber.assessments.beans.QCNote;
import com.revature.caliber.assessments.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * QCNote Controller
 */
@RestController
@CrossOrigin(origins = {"*"},
            methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
            allowedHeaders = {"X-PINGOTHER", "Content-Type"},
            maxAge = 10)
public class QCNoteController {

    private BusinessDelegate businessDelegate;
    @Autowired
    public void setBusinessDelegate(BusinessDelegate businessDelegate) { this.businessDelegate = businessDelegate; }

    /**
     * Create a new note by making a PUT request to the URL
     * @param note note to put
     * @return Response with appropriate status
     */
    @RequestMapping(value = "qcnotes/new",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> createQCNote(@RequestBody @Valid QCNote note) {
        ResponseEntity<Serializable> returnEntity;
        try {
            businessDelegate.createQCNote(note);
            returnEntity =  new ResponseEntity<Serializable>(HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    /**
     * Update a note
     * @param note note to update
     * @return Response with appropriate status
     */
    @RequestMapping(value = "qcnotes/update",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> updateQCNote(@RequestBody @Valid QCNote note) {
        ResponseEntity<Serializable> returnEntity;
        try{
            businessDelegate.updateQCNote(note);
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.OK);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @RequestMapping(value = "qcnotes/delete",
                    method = RequestMethod.DELETE,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> deleteQCNote(@RequestBody @Valid QCNote note) {
        ResponseEntity<Serializable> returnEntity;
        try {
            businessDelegate.deleteQCNote(note);
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.OK);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @RequestMapping(value = "qcnotes/byid/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QCNote> getQCNoteById(@PathVariable("identifier") int id) {
        ResponseEntity<QCNote> returnEntity;
        try {
            QCNote result = businessDelegate.getQCNoteById(id);
            if (result == null) {
                returnEntity = new ResponseEntity<QCNote>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<QCNote>(result, HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<QCNote>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @RequestMapping(value = "qcnotes/byweekandtrainee/{week},{trainee}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QCNote> getQCNoteByWeekTrainee(@PathVariable("week") int weekId, @PathVariable("trainee") int traineeId) {
        ResponseEntity<QCNote> returnEntity;
        try {
            QCNote result = businessDelegate.getQCNoteForTraineeWeek(traineeId, weekId);
            if (result == null) {
                returnEntity = new ResponseEntity<QCNote>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<QCNote>(result, HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<QCNote>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @RequestMapping(value = "qcnotes/bytraineeid/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QCNote>> getQCNotesByTrainee(@PathVariable("identifier") int traineeId) {
        ResponseEntity<List<QCNote>> returnEntity;
        try {
            List<QCNote> result = businessDelegate.getQCNotesByTrainee(traineeId);
            if (result == null) {
                returnEntity = new ResponseEntity<List<QCNote>>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<List<QCNote>>(result, HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<List<QCNote>>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @RequestMapping(value = "qcnotes/byweekid/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QCNote>> getQCNotesByWeek(@PathVariable("identifier") int weekId) {
        ResponseEntity<List<QCNote>> returnEntity;
        try {
            List<QCNote> result = businessDelegate.getQCNotesByWeek(weekId);
            if (result == null) {
                returnEntity = new ResponseEntity<List<QCNote>>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<List<QCNote>>(result, HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<List<QCNote>>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

}
