package com.kmji.nghbr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.ItemService;
import com.kmji.nghbr.service.UserService;

@Controller
public class ItemController extends AbstractController {
	
	@Autowired
    UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value={"/items"}, method = RequestMethod.GET)
	public String itemPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("items", user.getOwnedItems());
		return "item/listitems";

	}
	
	@RequestMapping(value={"/additem"}, method = RequestMethod.GET)
	public String addItemPageGets(ModelMap model){
		Item item = new Item();
		model.addAttribute("itemForm", item);
		return "item/additem";
	}
	
	@RequestMapping(value={"/additem"}, method = RequestMethod.POST)
	public String addItemPagePost(@ModelAttribute("itemForm") Item item, BindingResult result, ModelMap model){
		if(result.hasErrors()){
			System.out.println("\n\n\n\n\nErrors!!!!!");
			System.out.println(result.getAllErrors());
			return "item/additem";
		}
		
		User user = userService.findBySso(getPrincipal());
		item.setOwner(user);
		item.setBorrower(user);
		user.getOwnedItems().add(item);
		itemService.saveOrUpdate(item);
		
		return "redirect:/items";
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	

}
