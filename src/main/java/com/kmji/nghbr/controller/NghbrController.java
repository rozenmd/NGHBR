package com.kmji.nghbr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NghbrController extends AbstractController {


	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		if (getPrincipal() != "anonymousUser") {
			return "redirect:/profile";
		} else {
			model.addAttribute("greeting", "Hi, Welcome to mysite");
			return "home";
		}
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "redirect:/";
	}

	@RequestMapping(value = "/navBar", method = RequestMethod.GET)
	public String navBar(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "navBar";
	}

	@RequestMapping(value = "/head", method = RequestMethod.GET)
	public String head() {
		return "head";
	}


}
