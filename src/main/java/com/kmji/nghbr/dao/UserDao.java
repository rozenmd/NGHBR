package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.User;

import java.util.List;

public interface UserDao {

	User findById(int id);

	User findByFacebookId(String facebookId);
	
	User findBySSO(String sso);

	List<User> getAll();

	void saveOrUpdate(User user);

	void destroy(User user);

}

