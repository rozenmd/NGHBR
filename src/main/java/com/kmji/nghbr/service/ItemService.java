package com.kmji.nghbr.service;

import java.util.List;

import com.kmji.nghbr.model.Item;

public interface ItemService {
	
	Item findById(int id);
	
	List<Item> getAll();
	
	void saveOrUpdate(Item item);
	
	void destroy(Item item);

}
