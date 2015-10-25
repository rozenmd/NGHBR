package com.kmji.nghbr.service;

import java.util.List;

import com.kmji.nghbr.model.Attendee;

public interface AttendeeService {

    Attendee findById(int id);

    List<Attendee> getAll();

    void saveOrUpdate(Attendee item);

    void destroy(Attendee item);
}
