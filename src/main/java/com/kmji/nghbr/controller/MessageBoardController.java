package com.kmji.nghbr.controller;


import com.kmji.nghbr.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.Message;
import com.kmji.nghbr.service.MessageService;
import com.kmji.nghbr.service.UserService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageBoardController extends AbstractController {

	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/messageboard"}, method = RequestMethod.GET)
	public String itemPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		List<Message> messages = messageService.findByPostCode(user.getPostcode());
		model.addAttribute("messages",messages);
		return "user/messageboard";

	}

//	@RequestMapping(value = { "/messageboard" }, method = RequestMethod.GET)
//	public ModelAndView messageBoardPage() {
//		ModelAndView model = new ModelAndView("user/messageboard");
//
//		//model.addObject();
//		return model;
//
//	}

	/*Retrieve message data from user and send to database  */
	@RequestMapping(value = "/messageboard",  method = RequestMethod.POST)
	public String postmessage(@ModelAttribute("message") Message message, ModelMap model) {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				User user = userService.findBySso(getPrincipal());
				message.setPostCode(user.getPostcode());
				message.setUsername(user.getFirstName());
				message.setDate(new Date());
				messageService.save(message);
				model.addAttribute("message", message);
			}else{
				message.setPostCode(2036);
				message.setUsername("Peter");
				message.setDate(new Date());
				messageService.save(message);
				model.addAttribute("message", message);
			}

			
		} catch(Exception e) {

		}
		return "redirect:/messageboard";


	}


}




