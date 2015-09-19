package com.kmji.nghbr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.ItemService;
import com.kmji.nghbr.service.UserService;

@Controller
public class ItemController extends AbstractController {
	
	@Autowired
    UserService userService;
	
	@RequestMapping(value={"/items"}, method = RequestMethod.GET)
	public String itemPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("items", user.getOwnedItems());
		return "item/listitems";

	}
	
	

}
