package com.neu.jianfu.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;



public class TimestampEditor extends PropertyEditorSupport {
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 * ����ת��������spring����ôη����Բ���������ת��
	 * Ϊ���������ע��
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(text == null || text.equals("")){
			setValue(null);
		}else{
			try{
				//��һ�仰
				//Long time = 
				setValue(new Timestamp(Long.valueOf(text)));
				//setValue(new Timestamp(time));
			}catch(Exception e){
				setValue(java.sql.Timestamp.valueOf(text));
			}
		}
	}
}
