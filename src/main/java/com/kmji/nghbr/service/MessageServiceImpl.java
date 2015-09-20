package com.kmji.nghbr.service;

import com.kmji.nghbr.dao.MessageDao;
import com.kmji.nghbr.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao dao;

	@Override
	public Message findByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	@Override
	public Message findByPostCode(int postCode){
		return dao.findByPostCode(postCode);
	}
	

	@Override
	public List<Message> findAllUsers() {
		return dao.getAll();
	}

	@Override
	public Message findById(int id) {
		return dao.findById(id);

	}

	@Override
	public void save(Message message) {
		dao.saveOrUpdate(message);
	}

	@Override
	public void deleteUser(Message message) {
		dao.destroy(message);		
	}

}
