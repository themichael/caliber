package com.revature.caliber.assessments.data;

import java.util.List;

import com.revature.caliber.assessments.beans.Note;

public interface NoteDAO {

	public Note getNote(String note);
	public List<Note> getAllNotes();

}
