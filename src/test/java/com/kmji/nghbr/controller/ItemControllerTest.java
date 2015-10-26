package com.kmji.nghbr.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.kmji.nghbr.service.UserService;
import com.kmji.nghbr.service.ItemService;
import com.kmji.nghbr.service.MockItemServiceImpl;
import com.kmji.nghbr.service.MockUserServiceImpl;

public class ItemControllerTest {
	
	@Test
	public void itemPageTest(){
		
		ItemController ic = new MockItemController();
		ic.setUserService(new MockUserServiceImpl());
		ic.setItemService(new MockItemServiceImpl());
		
		ModelMap mm = new ModelMap();
		
		String rpath = ic.itemPage(mm);
		
		assertEquals(rpath, "item/listitems");
		assertTrue(mm.containsAttribute("user"));
		assertTrue(mm.containsAttribute("items"));
		
		assertTrue(mm.containsValue(ic.userService.findBySso("12345")));
		
	}
	
	class MockItemController extends ItemController{

		protected String getPrincipal(){
			return "12345";
		}
	}
	
}
