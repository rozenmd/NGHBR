package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Message;

import java.util.List;

public interface MessageDao {
	
	Message findById(int id);
	
	Message findBySSO(String sso);

	List<Message>getAll();

	void saveOrUpdate(Message message);

	void destroy(Message message);

}

