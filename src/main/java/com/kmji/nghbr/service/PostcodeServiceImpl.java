package com.kmji.nghbr.service;

import com.kmji.nghbr.dao.PostcodeDao;
import com.kmji.nghbr.dao.UserDao;
import com.kmji.nghbr.model.Postcode_db;
import com.kmji.nghbr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postcodeService")
@Transactional
public class PostcodeServiceImpl implements PostcodeService{

	@Autowired
	private PostcodeDao dao;

	public Postcode_db findByPostcodeSuburbState(int postcode, String suburb, String state) {
		return dao.findByPostcodeSuburbState(postcode, suburb, state);
	}

	public Postcode_db findByPostcodeSuburb(int postcode, String suburb) {
		return dao.findByPostcodeSuburb(postcode, suburb);
	}


	public Postcode_db findBySuburb(String suburb) {
		return dao.findBySuburb(suburb);
	}


	public Postcode_db findByPostcode(int postcode) {
		return dao.findByPostcode(postcode);
	}
}
