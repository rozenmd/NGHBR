package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class ItemTest {
	
	@Test
	public void itemTest(){
		Item i = new Item();
		
		i.setId(1);
		assertEquals(i.getId(), 1);
		
		i.setEndDate(new Date(2015, 1, 1));
		assertEquals(i.getEndDate(), new Date(2015, 1, 1));
		
		i.setStartDate(new Date(2015, 1, 1));
		assertEquals(i.getStartDate(), new Date(2015, 1, 1));
		
		i.setDescription("hello");
		assertEquals(i.getDescription(), "hello");
		
		i.setName("hammer");
		assertEquals(i.getName(), "hammer");
		
		i.setMinPoints(10);
		assertEquals(i.getMinPoints(), 10);
		
	}
	
	@Test
	public void itemUserTest(){
		User owner = new User();
		owner.setId(1);
		owner.setFirstName("imran");
		owner.setLastName("khan");
		
		User borrower = new User();
		owner.setId(2);
		owner.setFirstName("khanh");
		owner.setLastName("nguyen");
		
		Item i = new Item();
		i.setId(1);
		i.setOwner(owner);
		
		Item i2 = new Item();
		i2.setId(2);
		i2.setOwner(owner);
		
		List itemList = new ArrayList<Item>();
		itemList.add(i);
		itemList.add(i2);
		owner.setOwnedItems(itemList);

		assertEquals(i.getOwner(), owner);
		assertEquals(i2.getOwner(), owner);
		
		i.setBorrower(borrower);
		assertEquals(i.getBorrower(), borrower);
		
		assertEquals(owner.getOwnedItems().get(0).getBorrower(), borrower);
		
	}
}
