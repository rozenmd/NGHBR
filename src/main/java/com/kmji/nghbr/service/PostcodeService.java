package com.kmji.nghbr.service;

import com.kmji.nghbr.model.Postcode_db;
import com.kmji.nghbr.model.User;

import java.util.List;

public interface PostcodeService {

	Postcode_db findByPostcodeSuburbState(int postcode, String suburb, String state);

	Postcode_db findByPostcodeSuburb(int postcode, String suburb);

	Postcode_db findBySuburb(String suburb);

	Postcode_db findByPostcode(int postcode);

}