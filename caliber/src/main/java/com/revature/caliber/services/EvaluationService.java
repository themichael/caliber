package com.revature.caliber.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;

/**
 * Used to add grades for assessments and input notes 
 * Application logic has no business being in a DAO
 * nor in a Controller. This is the ideal place for calculations 
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class EvaluationService {

	private static final Logger log = Logger.getLogger(EvaluationService.class);
	private GradeDAO gradeDAO;
	private NoteDAO noteDAO;
	
	@Autowired
	public void setGradeDAO(GradeDAO gradeDAO) {this.gradeDAO = gradeDAO;}
	@Autowired
	public void setNoteDAO(NoteDAO noteDAO) {this.noteDAO = noteDAO;}
	
	
	
}
