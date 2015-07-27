package com.neu.jianfu.service;

import java.util.List;

import com.neu.jianfu.entity.NoteBook;

public interface NoteBookService {
	
	List<NoteBook> loadNormal(String userId);
	List<NoteBook> loadSpecial(String userId);
	void update(NoteBook notebook);
	void delete(String id);
	void save(NoteBook notebook);
}
