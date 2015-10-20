package com.kmji.nghbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmji.nghbr.dao.EventDao;
import com.kmji.nghbr.model.Event;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao dao;

    @Override
    public Event findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Event> getAll() {
        return dao.getAll();
    }

    @Override
    public void saveOrUpdate(Event event) {
        dao.saveOrUpdate(event);
    }

    @Override
    public void destroy(Event event) {
        dao.destroy(event);
    }

}
