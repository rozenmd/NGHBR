package com.kmji.nghbr.service;

import java.util.List;

import com.kmji.nghbr.model.BorrowRequest;
import com.kmji.nghbr.model.ReturnRequest;

public interface ReturnRequestService {
	
	ReturnRequest findById(int id);
	
	List<ReturnRequest> getAll();
	
	void saveOrUpdate(ReturnRequest item);
	
	void destroy(ReturnRequest item);
}
