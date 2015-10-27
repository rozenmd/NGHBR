package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.Suburb;

import com.kmji.nghbr.service.UserService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("suburbDao")
public class SuburbDaoImpl extends AbstractDao<Integer, Suburb> implements SuburbDao {

	public Suburb findByPostcodeSuburbState(int postcode, String suburbName, String state) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("suburbName", suburbName));
		crit.add(Restrictions.eq("state", state));
		crit.add(Restrictions.eq("postcode", postcode));
		return (Suburb) crit.uniqueResult();
	}

	public Suburb findByPostcodeSuburb(int postcode, String suburbName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("suburbName", suburbName));
		crit.add(Restrictions.eq("postcode", postcode));
		return (Suburb) crit.uniqueResult();
	}

	public Suburb findBySuburb(String suburbName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("suburbName", suburbName));
		crit.setMaxResults(1);
		List<Suburb> results = crit.list();
		return results.get(0);
	}


	public List<Suburb> getAll() {
		Criteria crit = createEntityCriteria().setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}


	public List<Suburb> getTopFifteen() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.desc("totalPoints"));
		crit.setMaxResults(15);
		return crit.list();
	}

	public void saveOrUpdate(Suburb suburb) {
		persist(suburb);
	}



	public List<Suburb> findByPostcode(int postcode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("postcode", postcode));
		//crit.setMaxResults(1); Changed as could have multiple subs with same postcode
		List<Suburb> results = crit.list();

		return results;
	}
}
