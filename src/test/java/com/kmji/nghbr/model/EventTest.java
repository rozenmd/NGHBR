package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventTest {

    @Test
    public void itemEvent(){
        Event e = new Event();

        assertNull(e.getTitle());
        assertNull(e.getDescription());
        assertNull(e.getStart());
        assertNull(e.getEnd());

        e.setTitle("Skipping ELEC5619 lectures.");
        assertEquals("Skipping ELEC5619 lectures.", e.getTitle());

        e.setDescription("Some description");
        assertEquals("Some description", e.getDescription());

        e.setStart(new Date(2015, 1, 1));
        assertEquals(new Date(2015, 1, 1), e.getStart());

        e.setEnd(new Date(2015, 1, 1));
        assertEquals(new Date(2015, 1, 1), e.getEnd());
    }

    @Test
    public void eventUserTest(){
        User user = new User();
        user.setId(1);
        user.setFirstName("Khanh");
        user.setLastName("Nguyen");

        Event e = new Event();
        e.setOwner(user);
        assertEquals(user, e.getHost());
    }

    @Test
    public void attendeeListTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Khanh");
        user.setLastName("Nguyen");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Imran");
        user2.setLastName("Khanh");

        Event e = new Event();
        e.setOwner(user);
        e.setTitle("Skipping ELEC5619 lectures.");
        e.setDescription("Some description");
        e.setStart(new Date(2015, 1, 1));
        e.setEnd(new Date(2015, 1, 1));

        List<Attendee> attendees = new ArrayList<Attendee>();

        Attendee a = new Attendee();
        a.setRsvp(true);
        a.setEvent(e);
        a.setUser(user);

        Attendee a2 = new Attendee();
        a2.setRsvp(true);
        a2.setEvent(e);
        a2.setUser(user2);

        attendees.add(a);
        attendees.add(a2);

        e.setAttendees(attendees);
        assertEquals(2, e.getAttendees().size());
    }


}
