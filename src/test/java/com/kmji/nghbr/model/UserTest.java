package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class UserTest {
	
	//Get the test specific file dir
	File dir = new File(System.getProperty("user.dir") 
				+ "/src/test/resources/" + this.getClass().getSimpleName());
	
	@Test
	public void userTest(){
		User u = new User();
		
		u.setId(1);
		assertEquals(u.getId(), 1);
		
		u.setEmail("imran@example.com");
		assertEquals(u.getEmail(), "imran@example.com");
		
		u.setFacebookId("12345676sdfsdf");
		assertEquals(u.getFacebookId(),"12345676sdfsdf");
		
		u.setFirstName("imran");
		assertEquals(u.getFirstName(), "imran");
		
		u.setLastName("khanh");
		assertEquals(u.getLastName(), "khanh");
		
		u.setPassword("12345");
		assertEquals(u.getPassword(), "12345");
		
		u.setPoints(12);
		assertEquals(u.getPoints(), 12);
		
		u.setSsoId("1234");
		assertEquals(u.getSsoId(), "1234");
		
		u.setState("ALIVE");
		assertEquals(u.getState(), "ALIVE");
		
	}

}
