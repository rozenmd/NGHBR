package com.kmji.nghbr.service;

import com.kmji.nghbr.dao.UserDao;
import com.kmji.nghbr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySso(String sso) {
		return dao.findBySSO(sso);
	}

	@Override
	public User findByFacebookId(String facebookId) {
		return dao.findByFacebookId(facebookId);
	}

	public List<User> findAllUsers(){
		return dao.getAll();
	}

	public void save(User user) {
		System.out.println("Only an Admin can Update a User");
		dao.saveOrUpdate(user);
	}

	public void deleteUser(User user){
		dao.destroy(user);
	}
}
