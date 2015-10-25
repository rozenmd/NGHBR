package com.kmji.nghbr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.kmji.nghbr.model.Event;

@Repository("EventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

    @Override
    public Event findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Event> getAll() {
        Criteria crit = createEntityCriteria();
        return crit.list();
    }

    @Override
    public void saveOrUpdate(Event event) {
        persist(event);

    }

    @Override
    public void destroy(Event event) {
        delete(event);

    }

}
