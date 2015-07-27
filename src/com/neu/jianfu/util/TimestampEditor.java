package com.neu.jianfu.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;



public class TimestampEditor extends PropertyEditorSupport {
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 * 类型转换方法，spring会调用次方法对参数做类型转换
	 * 为对象的类型注入
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(text == null || text.equals("")){
			setValue(null);
		}else{
			try{
				//忘一句话
				//Long time = 
				setValue(new Timestamp(Long.valueOf(text)));
				//setValue(new Timestamp(time));
			}catch(Exception e){
				setValue(java.sql.Timestamp.valueOf(text));
			}
		}
	}
}
