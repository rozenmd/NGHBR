package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Postcode;

import java.util.List;

public interface PostcodeDao {

	Postcode findByPostcodeSuburbState(int postcode, String suburb, String state);

	Postcode findByPostcodeSuburb(int postcode, String suburb);

	Postcode findBySuburb(String suburb);

	Postcode findByPostcode(int postcode);



}

