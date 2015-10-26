package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.User;

public interface ItemDao {
	
	Item findById(int id);
	
	List<Item> getAll();
	
	void saveOrUpdate(Item item);
	
	void destroy(Item item);

}
