package com.kmji.nghbr.controller;


import com.kmji.nghbr.model.Event;
import com.kmji.nghbr.model.Suburb;
import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.EventService;
import com.kmji.nghbr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController extends AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @RequestMapping(value={"/events"}, method = RequestMethod.GET)
    public String events(ModelMap model){
        if (getPrincipal() != "anonymousUser") {
            User user = userService.findBySso(getPrincipal());
            model.addAttribute("user", user);

            Suburb suburb = user.getSuburb();

            List<Event> events = suburb.getEvents();
            model.addAttribute("events", events);

            List<String> eventJSON = new ArrayList<String>();
            for (Event event : events) {
                eventJSON.add(event.getJSONString());
            }
            model.addAttribute("eventsJSON", eventJSON.toString());

            return "event/events";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value={"/events/{id}"}, method = RequestMethod.GET)
    public String events(@PathVariable String id, ModelMap model){

        Event event = eventService.findById(Integer.parseInt(id));

        model.addAttribute("event", event);

        return "event/show";

    }

    @ResponseBody
    @RequestMapping(value = "/events/new", method = RequestMethod.POST)
    public Event newEvent(@RequestBody Event requestEvent, HttpServletRequest request) {
        User user = userService.findBySso(getPrincipal());

        Event newEvent = new Event();

        newEvent.setTitle(requestEvent.getTitle());
        newEvent.setDescription(requestEvent.getDescription());
        newEvent.setStart(requestEvent.getStart());
        newEvent.setEnd(requestEvent.getEnd());
        newEvent.setOwner(user);
        newEvent.setSuburb(user.getSuburb());

        eventService.saveOrUpdate(newEvent);

        return newEvent;
    }


}
