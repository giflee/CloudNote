package com.neu.jianfu.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.neu.jianfu.dao.NoteMapper;
import com.neu.jianfu.dao.ShareMapper;
import com.neu.jianfu.entity.Note;
import com.neu.jianfu.entity.Share;
@Service
public class NoteServiceImpl implements Serializable, NoteService {
	@Resource
	private NoteMapper notemapper;
	@Resource
	private ShareMapper sharemapper;
	public List<Note> findNotes(String notebookId) {
		List<Note> list = notemapper.findNotes(notebookId);
		return list;
	}

	public void saveNote(Note note) {
		notemapper.saveNote(note);
		
	}
	
	public void deleteNote(String noteId){
		//É¾³ý
	}

	public void update(Note note) {
		notemapper.update(note);
	}

	public List<Share> findShares(String condition, int currentPage) {
		if(condition == null || condition.equals("")){
			return null;
		}
		int begin = (currentPage-1)*10;
		return sharemapper.findByPage(condition, begin, 10);
		
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ShareMapper nt = ctx.getBean(ShareMapper.class);
		List<Share> list = nt.findByPage("1", 0, 10);
		if(list == null){
			System.out.println("1");
		}
		for (Share s : list){
			System.out.println(s.getCn_note_id());
		}
	}

}
