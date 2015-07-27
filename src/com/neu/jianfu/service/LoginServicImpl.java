package com.neu.jianfu.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.jianfu.dao.NoteBookMapper;
import com.neu.jianfu.dao.UserMapper;
import com.neu.jianfu.entity.NoteBook;
import com.neu.jianfu.entity.User;
import com.neu.jianfu.exception.NameException;
import com.neu.jianfu.exception.PasswordException;
import com.neu.jianfu.util.Md5Util;
import com.neu.jianfu.util.SystemCons;
@Service
public class LoginServicImpl implements Serializable ,LoginService,SystemCons{
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private NoteBookMapper notebookmapper;
	//可以加在方法上，也可以加在类上，方法注解会覆盖类上的注解
	//spring管理该方法的事务，当该方法报错时会回滚事务，默认情况下spring只能针对runtimeException实现回滚
	//如果希望其他类型的异常也可以回滚，需要配置参数
	@Transactional(rollbackFor=Exception.class)
	public boolean register(User user) {
		//校验用户名是否重复
		if(user == null)
			throw new RuntimeException("参数不能为空");
		boolean b = checkUserName(user.getCn_user_name());
		if(b){
			//创建用户
			user.setCn_user_id(UUID.randomUUID().toString());
			//加密md5
			String pwd = Md5Util.md5(user.getCn_user_password());
			user.setCn_user_password(pwd);
			userMapper.save(user);
			
			//预制4个特殊笔记本
			initNoteBook(user);
			return true;
		}else{
			
			return b;
		}
		
	}

	private void initNoteBook(User user) {
		NoteBook n1 = new NoteBook();//收藏夹
		n1.setCn_notebook_id(UUID.randomUUID().toString());
		n1.setCn_user_id(user.getCn_user_id());
		n1.setCn_notebook_type_id(FAVORITES_TYPE_ID);
		n1.setCn_notebook_name(FAVORITES_TYPE_NAME);
		n1.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		NoteBook n2 = new NoteBook();//回收站
		
		n2.setCn_notebook_id(UUID.randomUUID().toString());
		n2.setCn_user_id(user.getCn_user_id());
		n2.setCn_notebook_type_id(RECYCLE_TYPE_ID);
		n2.setCn_notebook_name(RECYCLE_TYPE_NAME);
		n2.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		NoteBook n3 = new NoteBook();
		n3.setCn_notebook_id(UUID.randomUUID().toString());
		n3.setCn_user_id(user.getCn_user_id());
		n3.setCn_notebook_type_id(ACTION_TYPE_ID);
		n3.setCn_notebook_name(ACTION_TYPE_NAME);
		n3.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		NoteBook n4 = new NoteBook();
		n4.setCn_notebook_id(UUID.randomUUID().toString());
		n4.setCn_user_id(user.getCn_user_id());
		n4.setCn_notebook_type_id(PUSH_TYPE_ID);
		n4.setCn_notebook_name(PUSH_TYPE_NAME);
		n4.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		notebookmapper.save(n1);
		notebookmapper.save(n2);
		notebookmapper.save(n3);
		notebookmapper.save(n4);
	}
	
	public boolean checkUserName(String name){
		User u = userMapper.findByName(name);
		if(u == null){
			//合法
			
			return true;
		}else{
			//已存在
			return false;
		}
	}
	
	public User login(String name,String password) throws NameException,PasswordException{
		
		if(name == null||name.equals("")){
			throw new NameException("用户名不能为空");
		}
		if(password == null||password.equals("")){
			throw new PasswordException("密码空");
		}
		User user = userMapper.findByName(name);
		String pwd = Md5Util.md5(password);
		if(user == null){
			throw new NameException("用户不存在不存在");
		}else if(!(user.getCn_user_password().equals(pwd))){
			throw new PasswordException("密码错误");
		}else{
			return user;
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper um = ctx.getBean(UserMapper.class);
		Map<String, Object> map = new HashMap<String, Object>();
		String id = "8fc326c0-551e-4fa7-bf06-f87f983d8310";
		String pwd = "12345678";
		map.put("id", id);
		map.put("password", pwd);
		um.updatePassword(map);
		
		
	}

	public boolean updatePassword(User user, String oldpassword,String newpassword) {
		
		String newpwd = Md5Util.md5(newpassword);
		String oldpwd = Md5Util.md5(oldpassword);
		
		if(!(user.getCn_user_password().equals(oldpwd))){
			
			return false;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getCn_user_id());
			
			map.put("password", newpwd);
			userMapper.updatePassword(map);
			
			return true;
		}
	}
	
	
	

	
}