package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.ReturnRequest;


public interface ReturnRequestDao {
	ReturnRequest findById(int id);
	
	List<ReturnRequest> getAll();
	
	void saveOrUpdate(ReturnRequest req);
	
	void destroy(ReturnRequest req);
}
