package com.kmji.nghbr.service;

import com.kmji.nghbr.model.Postcode;
import com.kmji.nghbr.model.User;

import java.util.List;

public interface PostcodeService {

	Postcode findByPostcodeSuburbState(int postcode, String suburb, String state);

	Postcode findByPostcodeSuburb(int postcode, String suburb);

	Postcode findBySuburb(String suburb);

	Postcode findByPostcode(int postcode);

}