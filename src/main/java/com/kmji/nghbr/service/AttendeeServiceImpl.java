package com.kmji.nghbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmji.nghbr.dao.AttendeeDao;
import com.kmji.nghbr.model.Attendee;

@Service("attendeeService")
@Transactional
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeDao dao;

    @Override
    public Attendee findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Attendee> getAll() {
        return dao.getAll();
    }

    @Override
    public void saveOrUpdate(Attendee item) {
        dao.saveOrUpdate(item);

    }

    @Override
    public void destroy(Attendee item) {
        dao.destroy(item);

    }

}
