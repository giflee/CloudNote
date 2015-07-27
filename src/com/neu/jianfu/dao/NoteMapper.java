package com.neu.jianfu.dao;

import java.util.List;

import com.neu.jianfu.entity.Note;

public interface NoteMapper {
	List<Note> findNotes(String notebookId);
	void saveNote(Note note);
	void delete(String noteId);
	void update(Note note);
}
