package com.kmji.nghbr.dao;

import java.util.List;

import com.kmji.nghbr.model.Attendee;


public interface AttendeeDao {
    Attendee findById(int id);

    List<Attendee> getAll();

    void saveOrUpdate(Attendee req);

    void destroy(Attendee req);
}
