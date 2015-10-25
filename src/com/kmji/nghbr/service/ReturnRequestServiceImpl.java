package com.kmji.nghbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmji.nghbr.dao.ReturnRequestDao;
import com.kmji.nghbr.model.ReturnRequest;

@Service("returnRequestService")
@Transactional
public class ReturnRequestServiceImpl implements ReturnRequestService {
	
	@Autowired
	private  ReturnRequestDao dao;
	
	@Override
	public  ReturnRequest findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List< ReturnRequest> getAll() {
		return dao.getAll();
	}

	@Override
	public void saveOrUpdate( ReturnRequest item) {
		dao.saveOrUpdate(item);

	}

	@Override
	public void destroy( ReturnRequest item) {
		dao.destroy(item);

	}

}
