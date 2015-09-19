package com.kmji.nghbr.controller;


import org.springframework.stereotype.Controller;
import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.Message;
import com.kmji.nghbr.service.MessageService;

@Controller
public class MessageBoardController extends AbstractController {

	@Autowired
	MessageService messageService;


	@RequestMapping(value = { "/messageboard" }, method = RequestMethod.GET)
	public String messageBoardPage(ModelMap model) {
		return "user/messageboard";
	}

	/*Retrieve message data from user and send to database  */
	@RequestMapping(value = "/postmessage",  method = RequestMethod.POST)
	public String postmessage(@ModelAttribute("message") Message message, ModelMap model) {
		try {
			message.setPostCode(2036);
			message.setUsername("Peter");
			message.setDate(new Date());
			messageService.save(message);
			model.addAttribute("message", message);
			
		} catch(Exception e) {

		}
		return "user/messageboard";


	}


}




