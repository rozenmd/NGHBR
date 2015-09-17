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
	public Message findBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (Message) crit.uniqueResult();
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
