package com.kmji.nghbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmji.nghbr.dao.BorrowRequestDao;
import com.kmji.nghbr.model.BorrowRequest;

@Service("borrowRequestService")
@Transactional
public class BorrowRequestServiceImpl implements BorrowRequestService {
	
	@Autowired
	private BorrowRequestDao dao;
	
	@Override
	public BorrowRequest findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<BorrowRequest> getAll() {
		return dao.getAll();
	}

	@Override
	public void saveOrUpdate(BorrowRequest item) {
		dao.saveOrUpdate(item);

	}

	@Override
	public void destroy(BorrowRequest item) {
		dao.destroy(item);

	}

}
