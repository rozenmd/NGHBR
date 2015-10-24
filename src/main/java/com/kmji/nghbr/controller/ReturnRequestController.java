package com.kmji.nghbr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kmji.nghbr.model.BorrowRequest;
import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.ReturnRequest;
import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.BorrowRequestService;
import com.kmji.nghbr.service.ItemService;
import com.kmji.nghbr.service.ReturnRequestService;
import com.kmji.nghbr.service.UserService;

@Controller
@PropertySource(value = { "classpath:application.properties" })
public class ReturnRequestController extends AbstractController {
	
	@Autowired
    UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	BorrowRequestService borrowRequestService;
	
	@Autowired
	ReturnRequestService returnRequestService;
	
	@RequestMapping(value={"/returnrequests/sent"}, method = RequestMethod.GET)
	public String requestsSentPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("returnRequests", user.getSentReturnRequests());
		model.addAttribute("label", "items you returned");
		return "item/listreturnrequests";

	}
	
	@RequestMapping(value={"/returnrequests/recieved"}, method = RequestMethod.GET)
	public String requestsRecievedPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("returnRequests", user.getRecievedReturnRequests());
		model.addAttribute("label", "items returned to you");
		return "item/listreturnrequests";

	}
	
	@RequestMapping(value={"/returnrequest/{returnRequestId}"}, method = RequestMethod.GET)
	public String requestApprovePage(ModelMap model, @PathVariable int returnRequestId){
		User user = userService.findBySso(getPrincipal());
		ReturnRequest returnRequest = returnRequestService.findById(returnRequestId);
		Item item = returnRequest.getItem();		
		model.addAttribute("user", user);
		model.addAttribute("item", item);
		model.addAttribute("returnRequestForm", returnRequest);
		
		if(user.equals(returnRequest.getOwner()) && returnRequest.getOwnerScore() < 0 ){
			return "item/acceptreturnitem";
		} else {
			return "redirect:/returnrequests/recieved";
		}

	}
	
	@RequestMapping(value={"/returnrequest/{returnRequestId}"}, method = RequestMethod.POST)
	public String requestApprovePagePost(@ModelAttribute("returnRequestForm") ReturnRequest returnRequestForm, 
			BindingResult result, ModelMap model, @PathVariable int returnRequestId){
		
		User user = userService.findBySso(getPrincipal());
		ReturnRequest rr = returnRequestService.findById(returnRequestId);
		Item item = rr.getItem();
		
		if(result.hasErrors()){		
			model.addAttribute("user", user);
			model.addAttribute("item", item);
			model.addAttribute("returnRequestForm", rr);
			
			return "item/acceptreturnitem";
		}
		
		rr.setOwnerMessage(returnRequestForm.getOwnerMessage());
		rr.setOwnerScore(returnRequestForm.getOwnerScore());
		
		User borrower = item.getBorrower();
		borrower.setPoints(borrower.getPoints() + returnRequestForm.getOwnerScore());
		returnRequestService.saveOrUpdate(rr);
		userService.save(borrower);

		item.setBorrower(item.getOwner());
		itemService.saveOrUpdate(item);
		
		return "redirect:/returnrequests/recieved";
	}
	
	@RequestMapping(value={"/items/return/{itemId}"}, method = RequestMethod.GET)
	public String itemReturnPage(ModelMap model, @PathVariable int itemId){
		User user = userService.findBySso(getPrincipal());
		Item item = itemService.findById(itemId);
		ReturnRequest returnRequest = new ReturnRequest();
				
		model.addAttribute("user", user);
		model.addAttribute("item", item);
		model.addAttribute("returnRequestForm", returnRequest);
		
		if(user.equals(item.getBorrower())){
			return "item/returnitem";
		} else {
			return "redirect:/items/borrowed";
		}

	}
	
	@RequestMapping(value={"/items/return/{itemId}"}, method = RequestMethod.POST)
	public String itemReturnPagePost(@ModelAttribute("returnRequestForm") ReturnRequest returnRequestForm, 
			BindingResult result, ModelMap model, @PathVariable int itemId){
		
		User user = userService.findBySso(getPrincipal());
		Item item = itemService.findById(itemId);
		
		if(result.hasErrors()){
			ReturnRequest returnRequest = new ReturnRequest();
			
			model.addAttribute("user", user);
			model.addAttribute("item", item);
			model.addAttribute("returnRequestForm", returnRequest);
			
			return "item/borrowitem";
		}
		
		returnRequestForm.setItem(item);
		returnRequestForm.setBorrower(user);
		returnRequestForm.setOwner(item.getOwner());
		
		User owner = item.getOwner();
		owner.setPoints(owner.getPoints() + returnRequestForm.getBorrowerScore());
		
		returnRequestService.saveOrUpdate(returnRequestForm);
		userService.save(owner);
		
		return "redirect:/returnrequests/sent";
	}
}
