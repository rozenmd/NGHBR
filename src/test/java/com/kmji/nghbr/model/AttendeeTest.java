package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class AttendeeTest {

    @Test
    public void attendeeTest(){
        Attendee a = new Attendee();
        Event e = new Event();
        User u = new User();

        assertNull(a.getEvent());
        assertNull(a.getUser());
        assertFalse(a.getRsvp());

        a.setEvent(e);
        assertEquals(e, a.getEvent());

        a.setUser(u);
        assertEquals(u, a.getUser());

        a.setRsvp(true);
        assertTrue(a.getRsvp());

    }
}
