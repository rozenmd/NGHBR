package com.kmji.nghbr.service;

import java.util.List;

import com.kmji.nghbr.model.Event;

public interface EventService {

    Event findById(int id);

    List<Event> getAll();

    void saveOrUpdate(Event event);

    void destroy(Event event);

}
