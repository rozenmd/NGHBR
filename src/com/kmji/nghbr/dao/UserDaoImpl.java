package com.kmji.nghbr.dao;

import com.kmji.nghbr.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

	@Override
	public User findByFacebookId(String facebookId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("facebookId", facebookId));
		return (User) crit.uniqueResult();
	}

	public User findBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (User) crit.uniqueResult();
	}

	public List<User> getAll() {
		Criteria crit = createEntityCriteria();
		return crit.list();
	}

	public void saveOrUpdate(User user) {
		persist(user);
	}

	public void destroy(User user) {
		delete(user);
	}
}
