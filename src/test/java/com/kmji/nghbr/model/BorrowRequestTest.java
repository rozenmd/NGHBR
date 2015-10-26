package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class BorrowRequestTest {
	
	//Get the test specific file dir
	File dir = new File(System.getProperty("user.dir") 
				+ "/src/test/resources/" + this.getClass().getSimpleName());
		
	@Test
	public void borrowRequestTest(){
		
		BorrowRequest br = new BorrowRequest();
		
		br.setApproved(true);
		assertTrue(br.getApproved());
		
		br.setId(1);
		assertEquals(br.getId(), 1);
		
		br.setMessage("hello");
		assertEquals(br.getMessage(),"hello");
		
		br.setResponseMessage("hello");
		assertEquals(br.getResponseMessage(),"hello");
		
		br.setEndDate(new java.util.Date(2015, 1, 1));
		assertEquals(br.getEndDate(), new java.util.Date(2015, 1, 1));
		
		br.setStartDate(new java.util.Date(2015, 1, 1));
		assertEquals(br.getStartDate(), new java.util.Date(2015, 1, 1));
	}
	
	@Test
	public void borrowRequestUserTest(){
		User owner = new User();
		owner.setId(1);
		
		User borrower = new User();
		borrower.setId(2);
		
		BorrowRequest br = new BorrowRequest();
		
		br.setOwner(owner);
		assertEquals(br.getOwner(), owner);
		
		br.setBorrower(borrower);
		assertEquals(br.getBorrower(), borrower);
	}
	
	@Test
	public void borrowRequestItemTest(){
		Item i = new Item();
		i.setId(1);
		
		BorrowRequest br = new BorrowRequest();
		
		br.setItem(i);
		assertEquals(br.getItem(), i);
	}

}
