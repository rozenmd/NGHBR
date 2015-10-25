package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.Attendee;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;


@Repository("AttendeeDao")
public class AttendeeDaoImpl extends AbstractDao<Integer, Attendee> implements AttendeeDao {

    @Override
    public Attendee findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Attendee> getAll() {
        Criteria crit = createEntityCriteria();
        return crit.list();
    }

    @Override
    public void saveOrUpdate(Attendee req) {
        persist(req);
    }

    @Override
    public void destroy(Attendee req) {
        delete(req);
    }

}
