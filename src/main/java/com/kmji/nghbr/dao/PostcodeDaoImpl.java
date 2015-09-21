package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Postcode_db;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postcodeDao")
public class PostcodeDaoImpl extends AbstractDao<Integer, Postcode_db> implements PostcodeDao {

	public Postcode_db findByPostcodeSuburbState(int postcode, String suburb, String state) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("suburb", suburb));
		crit.add(Restrictions.eq("state", state));
		crit.add(Restrictions.eq("postcode", postcode));
		return (Postcode_db) crit.uniqueResult();
	}

	public Postcode_db findByPostcodeSuburb(int postcode, String suburb) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("suburb", suburb));
		crit.add(Restrictions.eq("postcode", postcode));
		return (Postcode_db) crit.uniqueResult();
	}

	public Postcode_db findBySuburb(String suburb) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("suburb", suburb));
		crit.setMaxResults(1);
		List<Postcode_db> results = crit.list();
		return results.get(0);
	}

	public Postcode_db findByPostcode(int postcode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("postcode", postcode));
		crit.setMaxResults(1);
		List<Postcode_db> results = crit.list();

		return results.get(0);
	}
}
