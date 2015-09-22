package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Message;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MessageDao")
public class MessageDauImpl extends AbstractDao<Integer, Message> implements MessageDao {

	@Override
	public Message findById(int id) {
		return getByKey(id);
	}

	@Override
	public Message findByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("Username", username));
		return (Message) crit.uniqueResult();
	}
	
	@Override
	
	public List<Message> findByPostCode(int postCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("postCode", postCode));
		return (List<Message>) crit.list();
		
	}


	@Override
	public List<Message> getAll() {
		Criteria crit = createEntityCriteria();
		return crit.list();
	}

	@Override
	public void saveOrUpdate(Message message) {
		persist(message);		
	}

	@Override
	public void destroy(Message message) {
		delete(message);

	}

}
