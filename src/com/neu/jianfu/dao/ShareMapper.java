package com.neu.jianfu.dao;

import java.util.List;

import com.neu.jianfu.entity.Share;

public interface ShareMapper {
	
	List<Share> findByPage(String condition,int begin,int pageSize);
}
