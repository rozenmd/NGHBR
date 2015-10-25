package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.BorrowRequest;


public interface BorrowRequestDao {
	BorrowRequest findById(int id);
	
	List<BorrowRequest> getAll();
	
	void saveOrUpdate(BorrowRequest req);
	
	void destroy(BorrowRequest req);
}
