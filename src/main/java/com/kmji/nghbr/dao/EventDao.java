package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.Event;

public interface EventDao {

    Event findById(int id);

    List<Event> getAll();

    void saveOrUpdate(Event event);

    void destroy(Event event);

}
