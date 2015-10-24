package com.kmji.nghbr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.kmji.nghbr.model.ReturnRequest;

@Repository("ReturnRequestDao")
public class ReturnRequestDaoImpl extends AbstractDao<Integer, ReturnRequest> implements ReturnRequestDao {

	@Override
	public ReturnRequest findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<ReturnRequest> getAll() {
		Criteria crit = createEntityCriteria();
		return crit.list();
	}

	@Override
	public void saveOrUpdate(ReturnRequest req) {
		persist(req);
	}

	@Override
	public void destroy(ReturnRequest req) {
		delete(req);
	}

}
