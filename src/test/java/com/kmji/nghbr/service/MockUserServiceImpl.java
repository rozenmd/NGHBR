package com.kmji.nghbr.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.User;

public class MockUserServiceImpl extends UserServiceImpl{
	
	User u = new User();
	
	private void initUser(){
		u.setId(1);	
		u.setEmail("imran@example.com");	
		u.setFacebookId("12345676sdfsdf");	
		u.setFirstName("imran");
		u.setLastName("khanh");
		u.setPassword("12345");
		u.setPoints(12);
		u.setSsoId("1234");
		u.setState("ALIVE");
		
		Item i = new Item();
		i.setId(1);
		i.setOwner(u);
		
		Item i2 = new Item();
		i2.setId(2);
		i2.setOwner(u);
		
		List itemList = new ArrayList<Item>();
		itemList.add(i);
		itemList.add(i2);
		
		u.setOwnedItems(itemList);
		u.setBorrowedItems(itemList);
	}
	
	public User findById(int id) {
		initUser();
		u.setId(id);
		
		return u;
	}
	
	public User findBySso(String sso) {
		initUser();
		u.setSsoId(sso);
		
		return u;
	}
}
	

