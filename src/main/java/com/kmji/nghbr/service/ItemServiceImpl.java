package com.kmji.nghbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmji.nghbr.dao.ItemDao;
import com.kmji.nghbr.model.Item;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao dao;
	
	@Override
	public Item findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Item> getAll() {
		return dao.getAll();
	}

	@Override
	public void saveOrUpdate(Item item) {
		dao.saveOrUpdate(item);

	}

	@Override
	public void destroy(Item item) {
		dao.destroy(item);
	}

}
