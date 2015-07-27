package com.neu.jianfu.web;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.jianfu.entity.Note;
import com.neu.jianfu.entity.Result;
import com.neu.jianfu.entity.Share;
import com.neu.jianfu.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController implements Serializable {
	@Resource
	private NoteService noteservice;
	
	@RequestMapping("/findNotes.do")
	@ResponseBody
	public Result findNotes(String notebookId){
		List<Note> list = noteservice.findNotes(notebookId);
		return new Result(list);
	}
	@RequestMapping("/save.do")
	@ResponseBody
	public Result savaNote(String noteTitle,String notebookId){
		Note note = new Note();
		note.setCn_note_id(UUID.randomUUID().toString());
		note.setCn_notebook_id(notebookId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_create_time(System.currentTimeMillis());
		noteservice.saveNote(note);
		return new Result(note);
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public Result update(Note note){
		noteservice.update(note);
		return new Result();
	}
	@RequestMapping("/search.do")
	@ResponseBody
	public Result search(String cond,int currentPage){
		List<Share> list = noteservice.findShares(cond,currentPage);
		return new Result(list);
	}

}
