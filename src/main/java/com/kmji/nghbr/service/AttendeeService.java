package com.kmji.nghbr.service;

import java.util.List;

import com.kmji.nghbr.model.Attendee;
import com.kmji.nghbr.model.Event;
import com.kmji.nghbr.model.User;

public interface AttendeeService {

    Attendee findById(int id);

    List<Attendee> getAll();

    void saveOrUpdate(Attendee item);

    void destroy(Attendee item);
}
