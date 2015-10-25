package com.kmji.nghbr.service;

import com.kmji.nghbr.dao.SuburbDao;
import com.kmji.nghbr.dao.UserDao;
import com.kmji.nghbr.model.Suburb;
import com.kmji.nghbr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("suburbService")
@Transactional
public class SuburbServiceImpl implements SuburbService{

	@Autowired
	private SuburbDao dao;

	public Suburb findByPostcodeSuburbState(int postcode, String suburbName, String state) {
		return dao.findByPostcodeSuburbState(postcode, suburbName, state);
	}

	public Suburb findByPostcodeSuburb(int postcode, String suburbName) {
		return dao.findByPostcodeSuburb(postcode, suburbName);
	}


	public Suburb findBySuburb(String suburbName) {
		return dao.findBySuburb(suburbName);
	}


	public List<Suburb> findAllSuburbs() {
		return dao.getAll();
	}

	@Override
	public void save(Suburb suburb) {
		dao.saveOrUpdate(suburb);
	}


	public List<Suburb> findByPostcode(int postcode) {
		return dao.findByPostcode(postcode);
	}
}
