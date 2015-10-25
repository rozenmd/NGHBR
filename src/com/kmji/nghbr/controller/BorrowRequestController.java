package com.kmji.nghbr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kmji.nghbr.model.BorrowRequest;
import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.BorrowRequestService;
import com.kmji.nghbr.service.ItemService;
import com.kmji.nghbr.service.UserService;

@Controller
@PropertySource(value = { "classpath:application.properties" })
public class BorrowRequestController extends AbstractController {

	@Autowired
    UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	BorrowRequestService borrowRequestService;
	
	@RequestMapping(value={"/borrowrequests/sent"}, method = RequestMethod.GET)
	public String requestsSentPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("borrowRequests", user.getSentBorrowRequests());
		model.addAttribute("label", "requests you sent");
		return "item/listborrowrequests";

	}
	
	@RequestMapping(value={"/borrowrequests/recieved"}, method = RequestMethod.GET)
	public String requestsRecievedPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("borrowRequests", user.getRecievedBorrowRequests());
		model.addAttribute("label", "requests you have recieved");
		return "item/listborrowrequests";
		
	}
	
	@RequestMapping(value={"/borrowrequest/{borrowRequestId}"}, method = RequestMethod.GET)
	public String requestApprovePage(ModelMap model, @PathVariable int borrowRequestId){
		User user = userService.findBySso(getPrincipal());
		BorrowRequest borrowRequest = borrowRequestService.findById(borrowRequestId);
		Item item = borrowRequest.getItem();		
		model.addAttribute("user", user);
		model.addAttribute("item", item);
		model.addAttribute("borrowRequestForm", borrowRequest);
		
		if(user.equals(borrowRequest.getOwner()) && !borrowRequest.getApproved()){
			return "item/approveitem";
		} else {
			return "redirect:/borrowrequests/recieved";
		}

	}
	
	@RequestMapping(value={"/borrowrequest/{borrowRequestId}"}, method = RequestMethod.POST)
	public String requestApprovePagePost(@ModelAttribute("borrowRequestForm") BorrowRequest borrowRequestForm, 
			BindingResult result, ModelMap model, @PathVariable int borrowRequestId){
		
		User user = userService.findBySso(getPrincipal());
		BorrowRequest br = borrowRequestService.findById(borrowRequestId);
		Item item = br.getItem();
		
		if(result.hasErrors()){		
			model.addAttribute("user", user);
			model.addAttribute("item", item);
			model.addAttribute("borrowRequestForm", br);
			
			return "item/approveitem";
		}
		
		br.setApproved(borrowRequestForm.getApproved());
		br.setResponseMessage(borrowRequestForm.getResponseMessage());
		
		borrowRequestService.saveOrUpdate(br);
		
		//Change borrower of item
		if(br.getApproved()){
			User borrower = br.getBorrower();
			System.out.println("Borrower: " + borrower.getFirstName());
			item.setBorrower(borrower);
			itemService.saveOrUpdate(item);
		}
		
		return "redirect:/items";
	}
	
	@RequestMapping(value={"/items/borrow/{itemId}"}, method = RequestMethod.GET)
	public String itemPage(ModelMap model, @PathVariable int itemId){
		User user = userService.findBySso(getPrincipal());
		Item item = itemService.findById(itemId);
		BorrowRequest borrowRequest = new BorrowRequest();
				
		model.addAttribute("user", user);
		model.addAttribute("item", item);
		model.addAttribute("borrowRequestForm", borrowRequest);
		
		return "item/borrowitem";

	}
	
	@RequestMapping(value={"/items/borrow/{itemId}"}, method = RequestMethod.POST)
	public String editItemPagePost(@ModelAttribute("borrowRequestForm") BorrowRequest borrowRequestForm, 
			BindingResult result, ModelMap model, @PathVariable int itemId){
		
		User user = userService.findBySso(getPrincipal());
		Item item = itemService.findById(itemId);
		
		if(result.hasErrors()){
			BorrowRequest borrowRequest = new BorrowRequest();
			
			model.addAttribute("user", user);
			model.addAttribute("item", item);
			model.addAttribute("borrowRequestForm", borrowRequest);
			
			return "item/borrowitem";
		}
		
		borrowRequestForm.setItem(item);
		borrowRequestForm.setBorrower(user);
		borrowRequestForm.setOwner(item.getOwner());
		
		
		borrowRequestService.saveOrUpdate(borrowRequestForm);
		
		return "redirect:/borrowrequests/sent";
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
