package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ReturnRequestTest {
	
	//Get the test specific file dir
	File dir = new File(System.getProperty("user.dir") 
				+ "/src/test/resources/" + this.getClass().getSimpleName());
		
	@Test
	public void returnRequestTest(){
		
		ReturnRequest rr = new ReturnRequest();
		
		rr.setId(1);
		assertEquals(rr.getId(), 1);
		
		rr.setOwnerMessage("hello");
		assertEquals(rr.getOwnerMessage(),"hello");
		
		rr.setBorrowerMessage("hello");
		assertEquals(rr.getBorrowerMessage(),"hello");
		
		rr.setOwnerScore(1);
		assertEquals(rr.getOwnerScore(), 1);
		
		rr.setBorrowerScore(2);
		assertEquals(rr.getBorrowerScore(), 2);

	}
	
	@Test
	public void returnRequestUserTest(){
		User owner = new User();
		owner.setId(1);
		
		User borrower = new User();
		borrower.setId(2);
		
		ReturnRequest rr = new ReturnRequest();
		
		rr.setOwner(owner);
		assertEquals(rr.getOwner(), owner);
		
		rr.setBorrower(borrower);
		assertEquals(rr.getBorrower(), borrower);
	}
	
	@Test
	public void borrowRequestItemTest(){
		Item i = new Item();
		i.setId(1);
		
		ReturnRequest rr = new ReturnRequest();
		
		rr.setItem(i);
		assertEquals(rr.getItem(), i);
	}

}
