package com.neu.jianfu.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neu.jianfu.dao.NoteBookMapper;
import com.neu.jianfu.entity.NoteBook;
@Service
public class NoteBookServiceImpl implements Serializable, NoteBookService {

	@Resource
	private NoteBookMapper notemapper;
	public List<NoteBook> loadNormal(String userId) {
		return notemapper.findNormal(userId);
	}
	public List<NoteBook> loadSpecial(String userId) {
		return notemapper.findSpecial(userId);
	}
	public void update(NoteBook notebook) {
		 notemapper.update(notebook);
		
	}
	public void delete(String id) {
		notemapper.delete(id);
	}
	public void save(NoteBook notebook) {
		notemapper.save(notebook);
		
	}

}
