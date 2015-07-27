package com.neu.jianfu.entity;

import java.io.Serializable;

public class Result implements Serializable {
	//�����Ϊ�ͻ����ص����ݶ���
	private boolean success = true;//�����Ƿ�ִ�гɹ�
	private String message;//ʧ��ʱ����������Ϣ������ϵͳ�����ɿ��ͳһ����
	private Object data;//ҵ������
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
