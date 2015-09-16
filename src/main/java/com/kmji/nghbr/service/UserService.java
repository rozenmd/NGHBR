package com.kmji.nghbr.service;

import com.kmji.nghbr.model.User;

import java.util.List;

public interface UserService {

	User findBySso(String sso);

	List<User> findAllUsers();

	User findById(int id);

	void save(User user);

	void deleteUser(User user);
	
}