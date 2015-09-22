package com.kmji.nghbr.controller;


import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController extends AbstractController {

    @Autowired
    UserService userService;

    @RequestMapping(value={"/events"}, method = RequestMethod.GET)
    public String events(ModelMap model){
        User user = userService.findBySso(getPrincipal());
        model.addAttribute("user", user);
        return "event/events";

    }

}
