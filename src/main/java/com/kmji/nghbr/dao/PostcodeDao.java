package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Postcode_db;

import java.util.List;

public interface PostcodeDao {

	Postcode_db findByPostcodeSuburbState(int postcode, String suburb, String state);

	Postcode_db findByPostcodeSuburb(int postcode, String suburb);

	Postcode_db findBySuburb(String suburb);

	Postcode_db findByPostcode(int postcode);



}

