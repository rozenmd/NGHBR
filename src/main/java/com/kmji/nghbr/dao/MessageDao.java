package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Message;

import java.util.List;

public interface MessageDao {
	
	Message findById(int id);
	
	Message findByUsername(String username);
	
	Message findByPostCode(int postCode);

	List<Message>getAll();

	void saveOrUpdate(Message message);

	void destroy(Message message);

}

