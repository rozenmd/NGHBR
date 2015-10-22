package com.kmji.nghbr.service;

import com.kmji.nghbr.dao.PostcodeDao;
import com.kmji.nghbr.dao.UserDao;
import com.kmji.nghbr.model.Postcode;
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

	public Postcode findByPostcodeSuburbState(int postcode, String suburb, String state) {
		return dao.findByPostcodeSuburbState(postcode, suburb, state);
	}

	public Postcode findByPostcodeSuburb(int postcode, String suburb) {
		return dao.findByPostcodeSuburb(postcode, suburb);
	}


	public Postcode findBySuburb(String suburb) {
		return dao.findBySuburb(suburb);
	}


	public Postcode findByPostcode(int postcode) {
		return dao.findByPostcode(postcode);
	}
}
