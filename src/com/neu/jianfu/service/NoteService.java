package com.neu.jianfu.service;

import java.util.List;

import com.neu.jianfu.entity.Note;
import com.neu.jianfu.entity.Share;

public interface NoteService {
	List<Note> findNotes(String notebookId);
	void saveNote(Note note);
	void deleteNote(String noteId);
	void update(Note note);
	List<Share> findShares(String condition,int currentPage);
}
