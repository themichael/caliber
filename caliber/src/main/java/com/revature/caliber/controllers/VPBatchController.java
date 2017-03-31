package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.service.AssessmentService;
import com.revature.caliber.service.BatchService;
import com.revature.caliber.service.GradeService;
import com.revature.caliber.service.NoteService;
import com.revature.caliber.service.TraineeService;
import com.revature.caliber.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * The type Vp batch controller.
 */
@RestController
@RequestMapping("/vp")
public class VPBatchController {

	private AssessmentService assessmentService;
	private BatchService batchService;
	private GradeService gradeService;
	private NoteService noteService;
	private TrainerService trainerService;
	private TraineeService traineeService;

    /**
     * getAllBatches - REST API method, retrieves all the batches
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
		Set<Batch> allBatches = batchService.getAllBatch();
		return new ResponseEntity<Set<Batch>>(allBatches, HttpStatus.OK);
    }

    @RequestMapping(value = "/agg/tech/batch/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, HashMap<String, Double[]>>> aggregateTechForAllBatches() {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }
    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
    @Autowired
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
    @Autowired
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
    @Autowired
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
    @Autowired
	public void setTrainerService(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
    @Autowired
	public void setTraineeService(TraineeService traineeService) {
		this.traineeService = traineeService;
	}
}
