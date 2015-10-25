package com.kmji.nghbr.service;

import java.util.List;

import com.kmji.nghbr.model.BorrowRequest;

public interface BorrowRequestService {
	
	BorrowRequest findById(int id);
	
	List<BorrowRequest> getAll();
	
	void saveOrUpdate(BorrowRequest item);
	
	void destroy(BorrowRequest item);
}
