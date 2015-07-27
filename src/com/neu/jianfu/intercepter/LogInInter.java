package com.neu.jianfu.intercepter;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neu.jianfu.entity.User;

public class LogInInter implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp,
			Object obj) throws Exception {
		User user = (User)req.getSession().getAttribute("user");
		if(user == null){
			rsp.setCharacterEncoding("utf-8");
			rsp.setContentType("application/json");
			Writer writer = rsp.getWriter();
			//没写完
			writer.write("{\"success\":false,\"message\":\"没有登录\"}");
			return false;
		}else{
			
			return true;
		}
	}

}
