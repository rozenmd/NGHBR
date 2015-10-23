package com.kmji.nghbr.service;

import com.kmji.nghbr.model.Postcode;
import com.kmji.nghbr.model.Suburb;
import com.kmji.nghbr.model.User;

import java.util.List;

public interface SuburbService {

	Suburb findByPostcodeSuburbState(int postcode, String suburbName, String state);

	Suburb findByPostcodeSuburb(int postcode, String suburb);

	Suburb findBySuburb(String suburb);

	List<Suburb> findByPostcode(int postcode);

}