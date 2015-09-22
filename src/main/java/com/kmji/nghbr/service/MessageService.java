package com.kmji.nghbr.service;

import com.kmji.nghbr.model.Message;

import java.util.List;


public interface MessageService {
	
	Message findByUsername(String sso);
	
	Message findByPostCode(int postCode);


	List<Message> findAllMessages();

	Message findById(int id);

	void save(Message message);

	void deleteUser(Message message);

}
