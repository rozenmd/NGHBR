package com.kmji.nghbr.model;

public class ReturnRequest{
	
	private Item item;
	
	private User borrower;
	
	private User owner;
	
	private java.util.Date startDate;
	
	private java.util.Date endDate;
	
	private String message; 
	
	private String approveMessage;
	
	private boolean approved = false;

}
