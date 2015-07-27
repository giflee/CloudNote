package com.neu.jianfu.entity;

import java.io.Serializable;

public class Result implements Serializable {
	//服务端为客户返回的数据对象
	private boolean success = true;//程序是否执行成功
	private String message;//失败时给予错误的信息，对于系统报错由框架统一处理
	private Object data;//业务数据
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Result(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	public Result() {
		
	}
	public Result(Object data) {
		super();
		this.data = data;
	}
	
	
}
