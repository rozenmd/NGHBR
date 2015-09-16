package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.User;

public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
}

