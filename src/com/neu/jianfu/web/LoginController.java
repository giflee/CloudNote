package com.neu.jianfu.web;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.jianfu.entity.Result;
import com.neu.jianfu.entity.User;
import com.neu.jianfu.exception.NameException;
import com.neu.jianfu.exception.PasswordException;
import com.neu.jianfu.service.LoginService;
@Controller
@RequestMapping("/login")
public class LoginController implements Serializable {
	@Resource
	private LoginService loginService; 
	@RequestMapping("/register.do")
	@ResponseBody
	public Result register(User user){
		boolean b = loginService.register(user);
		return new Result(b);
	}
	@RequestMapping("/check.do")
	@ResponseBody
	public Result login(String userName,String password,HttpSession session){
		
		try {
			User user = loginService.login(userName, password);
			session.setAttribute("user", user);
			return new Result(user);
		} catch (NameException e) {
			
			return new Result("1");
		} catch (PasswordException e) {
			
			return new Result("2");
		}
		
	}
	@RequestMapping("/logout.do")
	@ResponseBody
	public Result logout(HttpSession session){
		session.invalidate();
		return new Result();
	}
	@RequestMapping("/update.do")
	@ResponseBody
	public Result updatePassword(HttpSession session,String oldpassword,String newpassword){
		User user = (User)session.getAttribute("user");
		System.out.println(user.getCn_user_id());
		boolean flag = loginService.updatePassword(user,oldpassword,newpassword);
		
		if(flag){
			return logout(session);
		}else{
			Result result = new Result();
			result.setSuccess(false);
			return result;
		}
	}
	
}
