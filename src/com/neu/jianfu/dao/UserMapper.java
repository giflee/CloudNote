package com.neu.jianfu.dao;

import java.util.List;
import java.util.Map;

import com.neu.jianfu.entity.NoteBook;
import com.neu.jianfu.entity.User;

public interface UserMapper {
	void save(User user);
	User findByName(String name);
	void updatePassword(Map<String, Object> map);
	
}
