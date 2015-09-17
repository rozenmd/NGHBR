package com.kmji.nghbr.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.Message;
import com.kmji.nghbr.service.MessageService;

@Controller
public class MessageBoardController extends AbstractController {

    @Autowired
    MessageService messageService;
    

	@RequestMapping(value = { "/messageboard" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
			Message message = new Message();
			message.setText("this is a post");
			messageService.save(message);
			model.addAttribute("message", message);
		return "user/messageboard";
	}


}


// create a test user



