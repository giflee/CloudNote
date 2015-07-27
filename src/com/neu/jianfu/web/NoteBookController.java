package com.neu.jianfu.web;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.neu.jianfu.entity.NoteBook;
import com.neu.jianfu.entity.Result;
import com.neu.jianfu.entity.User;
import com.neu.jianfu.service.NoteBookService;
import com.neu.jianfu.util.TimestampEditor;
@Controller
@RequestMapping("/notebook")
public class NoteBookController implements Serializable ,WebBindingInitializer{
	@Resource
	private NoteBookService notebookservice;
	
	@RequestMapping("/load_normal.do")
	@ResponseBody
	public Result loadNormal(HttpSession session){
		User user = (User)session.getAttribute("user");
		List<NoteBook> list = 
	 notebookservice.loadNormal(user.getCn_user_id());
		return new Result(list);
	}
	
	
	@RequestMapping("/load_special.do")
	@ResponseBody
	public Result loadSpecial(HttpSession session){
		User user = (User)session.getAttribute("user");
		List<NoteBook> list = 
	 notebookservice.loadSpecial(user.getCn_user_id());
		return new Result(list);
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public Result update(NoteBook notebook){
		notebookservice.update(notebook);
		return new Result();
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Result delete(String id){
		notebookservice.delete(id);
		return new Result();
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Result save(String name,HttpSession session){
		User user  = (User)session.getAttribute("user");
		String uId = user.getCn_user_id();
		NoteBook notebook = new NoteBook();
		notebook.setCn_notebook_id(UUID.randomUUID().toString());
		notebook.setCn_user_id(uId);
		notebook.setCn_notebook_name(name);
		notebook.setCn_notebook_type_id("5");
		notebookservice.save(notebook);
		return new Result();
	}

	@InitBinder
	//意思是在调用该controller的任何方法之前，先调用者个方法，以绑定类型转换器
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// TODO Auto-generated method stub
		binder.registerCustomEditor(java.sql.Timestamp.class, new TimestampEditor());
	}

}
