package com.neu.jianfu.service;

import com.neu.jianfu.entity.User;
import com.neu.jianfu.exception.NameException;
import com.neu.jianfu.exception.PasswordException;

public interface LoginService {
	
	boolean register(User user);
	
	User login(String userName,String password) throws NameException,PasswordException;
	
	boolean updatePassword(User user,String oldpassword,String newpassword);
}
