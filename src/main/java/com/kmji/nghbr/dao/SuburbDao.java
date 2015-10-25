package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.Suburb;

public interface SuburbDao {

	Suburb findByPostcodeSuburbState(int postcode, String suburb, String state);

	Suburb findByPostcodeSuburb(int postcode, String suburb);

	Suburb findBySuburb(String suburb);

	List<Suburb> getAll();

	List<Suburb> getTopFifteen();

	List<Suburb> findByPostcode(int postcode);

	void saveOrUpdate(Suburb suburb);

}

