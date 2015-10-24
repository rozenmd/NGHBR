package com.kmji.nghbr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.kmji.nghbr.model.BorrowRequest;
import com.kmji.nghbr.model.Item;

@Repository("BorrowRequestDao")
public class BorrowRequestDaoImpl extends AbstractDao<Integer, BorrowRequest> implements BorrowRequestDao {

	@Override
	public BorrowRequest findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<BorrowRequest> getAll() {
		Criteria crit = createEntityCriteria();
		return crit.list();
	}

	@Override
	public void saveOrUpdate(BorrowRequest req) {
		persist(req);
	}

	@Override
	public void destroy(BorrowRequest req) {
		delete(req);
	}

}
