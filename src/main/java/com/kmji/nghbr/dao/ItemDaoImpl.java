package com.kmji.nghbr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.User;

@Repository("ItemDao")
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao {
	
	@Override
	public Item findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<Item> getAll() {
		Criteria crit = createEntityCriteria();
		return crit.list();
	}

	@Override
	public void saveOrUpdate(Item item) {
		persist(item);

	}

	@Override
	public void destroy(Item item) {
		delete(item);

	}

}
