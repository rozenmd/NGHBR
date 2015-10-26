package com.kmji.nghbr.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.kmji.nghbr.model.Item;
import com.kmji.nghbr.model.Suburb;
import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.ItemService;
import com.kmji.nghbr.service.UserService;
import com.kmji.nghbr.util.ItemValidator;

@Controller
@PropertySource(value = { "classpath:application.properties" })
public class ItemController extends AbstractController implements ServletContextAware{
	
	@Autowired
    UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	private Environment env;
	
	@Autowired
    ItemValidator fileValidator;
	
	private ServletContext servletContext;

	@RequestMapping(value={"/items"}, method = RequestMethod.GET)
	public String itemPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("items", user.getOwnedItems());
		model.addAttribute("label", "Items you own");
		return "item/listitems";

	}
	
	@RequestMapping(value={"/items/borrowed"}, method = RequestMethod.GET)
	public String borrowedItemPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("items", user.getBorrowedItems());
		model.addAttribute("label", "Items you've borrowed");
		return "item/listitems";
		
	}
	
	@RequestMapping(value={"/items/search"}, method = RequestMethod.GET)
	public String itemSearchPage(ModelMap model){
		User user = userService.findBySso(getPrincipal());
		Suburb suburb = user.getSuburb();
		List<User> suburbUsers = suburb.getResidents();
		List<Item> items = new ArrayList<Item>();
		
		for(User u : suburbUsers){
			if(!u.equals(user)){ //don't add users own items lol
				for (Item i : u.getOwnedItems()){
					if(i.getOwner().equals(i.getBorrower())){ //only show items not already borrowed
						items.add(i);
					}
				}
			}
		}
		
		model.addAttribute("user", user);
		model.addAttribute("items", items);
		model.addAttribute("label", "Items you own");
		return "item/searchitems";

	}
	
	@RequestMapping(value={"/additem"}, method = RequestMethod.GET)
	public String addItemPageGets(ModelMap model){
		Item item = new Item();
		model.addAttribute("itemForm", item);
		return "item/additem";
	}
	
	@RequestMapping(value={"/additem"}, method = RequestMethod.POST)
	public String addItemPagePost(@ModelAttribute("itemForm") @Valid Item itemForm, 
			BindingResult result, ModelMap model) throws IOException{
		if(result.hasErrors()){
			Item item = new Item();
			model.addAttribute("itemForm", item);
			return "item/additem";
		}
		
		User user = userService.findBySso(getPrincipal());
		itemForm.setOwner(user);
		itemForm.setBorrower(user);
		user.getOwnedItems().add(itemForm);
		itemService.saveOrUpdate(itemForm);
		
		System.out.println("Fetching Image From User...");
		MultipartFile mpf = itemForm.getImageFile();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(mpf.getBytes());
		BufferedImage img = ImageIO.read(bis);
		BufferedImage thumbImg = Scalr.resize(img, 
				Method.QUALITY, Mode.FIT_EXACT, 90,90, Scalr.OP_ANTIALIAS);
		
		String imagePath = env.getProperty("item.image.path") + "/" + user.getId() + "/" + itemForm.getId()+".jpg";
		File imagePathFile = new File(imagePath);
		
		if(!imagePathFile.exists()){
			imagePathFile.getParentFile().mkdirs();
		}
		
		File out = new File(imagePath);
		ImageIO.write(thumbImg, "jpg", out);
		
		return "redirect:/items";
	}
	
	@RequestMapping(value={"/items/edit/{itemId}"}, method = RequestMethod.GET)
	public String editItemPageGets(ModelMap model, @PathVariable int itemId){
		Item item = itemService.findById(itemId);
		model.addAttribute("itemForm", item);
		return "item/edititem";
	}
	
	@RequestMapping(value={"/items/edit/{itemId}"}, method = RequestMethod.POST)
	public String editItemPagePost(@ModelAttribute("itemForm") @Valid Item itemForm, 
			BindingResult result, ModelMap model, @PathVariable int itemId) throws IOException{
		
		Item item = itemService.findById(itemId);
		
		if(result.hasErrors()){
			model.addAttribute("itemForm", item);
			return "item/edititem";
		}
		
		User user = userService.findBySso(getPrincipal());
		
		item.setName(itemForm.getName());
		item.setDescription(itemForm.getDescription());
		item.setStartDate(itemForm.getStartDate());
		item.setEndDate(itemForm.getEndDate());
		
		itemService.saveOrUpdate(item);
		
		System.out.println("Fetching Image From User...");
		MultipartFile mpf = itemForm.getImageFile();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(mpf.getBytes());
		BufferedImage img = ImageIO.read(bis);
		BufferedImage thumbImg = Scalr.resize(img, 
				Method.QUALITY, Mode.FIT_EXACT, 90,90, Scalr.OP_ANTIALIAS);
		
		String imagePath = env.getProperty("item.image.path") + "/" + user.getId() + "/" + itemForm.getId()+".jpg";
		File imagePathFile = new File(imagePath);
		
		if(!imagePathFile.getParentFile().exists()){
			imagePathFile.getParentFile().mkdirs();
		}
		
		File out = new File(imagePath);
		ImageIO.write(thumbImg, "jpg", out);
		
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
	
	@InitBinder("itemForm")
	protected void initBinderFileBucket(WebDataBinder binder) {
	    binder.setValidator(fileValidator);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	

}
