package com.kmji.nghbr.model;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class ItemTest {
	
	//Get the test specific file dir
	File dir = new File(System.getProperty("user.dir") 
			+ "/src/test/resources/" + this.getClass().getSimpleName());
	
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
	
	@Test
	public void imageShrinkTest() throws IOException{
		File imageFile = new File(dir, "hammer.png");
		
		FileInputStream fis = new FileInputStream(imageFile);
		BufferedImage img = ImageIO.read(fis);
		
		assertTrue(img.getHeight() == 90);
		assertTrue(img.getWidth() == 90);
		
		BufferedImage thumbImg = Scalr.resize(img, 
				Method.QUALITY, Mode.FIT_EXACT, 40,40, Scalr.OP_ANTIALIAS);
		
		assertTrue(thumbImg.getHeight() == 40);
		assertTrue(thumbImg.getWidth() == 40);
		
		
	}
	
	
}
