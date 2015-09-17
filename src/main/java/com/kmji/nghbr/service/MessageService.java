package com.kmji.nghbr.service;

import com.kmji.nghbr.model.Message;

import java.util.List;


public interface MessageService {
	
	Message findBySso(String sso);

	List<Message> findAllUsers();

	Message findById(int id);

	void save(Message user);

	void deleteUser(Message user);

}
