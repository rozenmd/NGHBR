package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.Attendee;
import com.kmji.nghbr.model.Event;
import com.kmji.nghbr.model.User;


public interface AttendeeDao {
    Attendee findById(int id);

    Attendee findByUserAndEvent(User user, Event event);

    List<Attendee> getAll();

    void saveOrUpdate(Attendee req);

    void destroy(Attendee req);
}
