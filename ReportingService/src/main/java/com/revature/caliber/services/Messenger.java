package com.revature.caliber.services;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Grade;
import com.revature.caliber.model.Note;
import com.revature.caliber.model.Panel;
import com.revature.caliber.model.Trainee;

@Service
public class Messenger {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${revature.caliber.service.exchange}")
	private String serviceExchange;
	
	@Value("${revature.caliber.service.grade}")
	private String grade;
	
	@Value("${revature.caliber.service.batch}")
	private String batch;
	
	@Value("${revature.caliber.service.trainee")
	private String trainee;
	
	@Value("${revature.caliber.service.note}")
	private String note;
	
	@Value("${revature.caliber.service.assessment}")
	private String assessment;
	
	@Value("${revature.caliber.service.panel}")
	private String panel;
	
	@Value("${revature.caliber.service.batchList}")
	private String batchList;
	
	@Value("${revature.caliber.service.noteList}")
	private String noteList;
	
	@Value("${revature.caliber.service.traineeList}")
	private String traineeList;
	
	@Value("${revature.caliber.service.panelList}")
	private String panelList;
	
	@Value("${revature.caliber.service.gradeList}")
	private String gradeList;
	
	@Value("${revature.caliber.service.assessmentList}")
	private String assessmentList;
	
	public List<Note> findAllQCTraineeNotes(int batchId, int weekNumber) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllQCTraineeNotes");
		msg.addProperty("batchId", batchId);
		msg.addProperty("week", weekNumber);
		List<Note> reply = (List<Note>) amqpTemplate.convertSendAndReceive(serviceExchange, noteList, msg.toString());
		return reply;
	}

	public List<Batch> findAllCurrentWithNotesAndTrainees() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllCurrentWithNotesAndTrainees");
		List<Batch> reply = (List<Batch>) amqpTemplate.convertSendAndReceive(serviceExchange, batchList, msg.toString());
		return reply;
	}

	public List<Batch> findAllCurrentWithNotes() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllCurrentWithNotes");
		List<Batch> reply = (List<Batch>) amqpTemplate.convertSendAndReceive(serviceExchange, batchList, msg.toString());
		return reply;
	}

	public Note findQCBatchNotes(Integer batchId, Integer week) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findQCBatchNotes");
		msg.addProperty("batchId", batchId);
		msg.addProperty("week", week);
		Note reply = (Note) amqpTemplate.convertSendAndReceive(serviceExchange, note, msg.toString());
		return reply;
	}

	public List<Trainee> findAllByBatch(int batchId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllByBatch");
		msg.addProperty("batchId", batchId);
		List<Trainee> reply = (List<Trainee>) amqpTemplate.convertSendAndReceive(serviceExchange, traineeList, msg.toString());
		return reply;
	}

	public Batch findOneWithTraineesAndGrades(Integer batchId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findOneWithTraineesAndGrades");
		msg.addProperty("batchId", batchId);
		Batch reply = (Batch) amqpTemplate.convertSendAndReceive(serviceExchange, batch, msg.toString());
		
		return reply;
	}

	public List<Batch> findAllCurrentWithTrainees() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllCurrentWithTrainees");
		List<Batch> reply  = (List<Batch>) amqpTemplate.convertSendAndReceive(serviceExchange, batchList, msg.toString());
		return reply;
	}

	public List<Panel> findBiWeeklyPanels() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findBiWeeklyPanels");
		List<Panel> reply = (List<Panel>) amqpTemplate.convertSendAndReceive(serviceExchange, panelList, msg.toString());
		return reply;
	}

	public List<Grade> findByTrainee(Integer traineeId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findByTrainee");
		msg.addProperty("traineeId", traineeId);
		List<Grade> reply = (List<Grade>) amqpTemplate.convertSendAndReceive(serviceExchange, gradeList, msg.toString());
		return reply;
	}

	public List<Grade> findByBatch(Integer batchId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findByBatch");
		msg.addProperty("batchId", batchId);
		List<Grade> reply = (List<Grade>) amqpTemplate.convertSendAndReceive(serviceExchange, gradeList, msg.toString());
		return reply;
	}

	public List<Assessment> findByWeek(Integer batchId, Integer week) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findByBatchIdAndWeek");
		msg.addProperty("batchId", batchId);
		msg.addProperty("week", week);
		List<Assessment> reply = (List<Assessment>) amqpTemplate.convertSendAndReceive(serviceExchange, assessmentList, msg.toString());
		return reply;
	}

	public List<Batch> findAllAfterDate(int month, int day, int year) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllAfterDate");
		msg.addProperty("month", month);
		msg.addProperty("day", day);
		msg.addProperty("year", year);
		List<Batch> reply = (List<Batch>) amqpTemplate.convertSendAndReceive(serviceExchange, batchList, msg.toString());
		return reply;
	}

}
