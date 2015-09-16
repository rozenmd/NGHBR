package com.kmji.nghbr.service;

import com.kmji.nghbr.model.User;

public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);
	
}