package com.kmji.nghbr.service;

import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.User;

public class MockItemServiceImpl extends ItemServiceImpl{
	
	Item saved;
	
	public Item findById(int id) {
		Item i = new Item();
		i.setId(id);
		
		return i;
	}
	
	public void saveOrUpdate(Item item) {
		saved = item;
	}
	
}
	

