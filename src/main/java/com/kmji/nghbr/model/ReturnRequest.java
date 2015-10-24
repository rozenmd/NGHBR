package com.kmji.nghbr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="APP_RETURN_REQ")
public class ReturnRequest{
	
	@Id
	@Column(name="BORROW_REQUEST_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BORROWER_ID")
	private User borrower;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OWNER_ID")
	private User owner;
	
	@Column(name="BORROWER_MESSAGE", nullable=true)
	private String borrowerMessage; 
	
	@Column(name="OWNER_MESSAGE", nullable=true)
	private String ownerMessage;
	
	@Column(name="BORROWER_SCORE", nullable=true)
	private int borrowerScore = -1;
	
	@Column(name="OWNER_SCORE", nullable=true)
	private int ownerScore = -1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getBorrowerMessage() {
		return borrowerMessage;
	}

	public void setBorrowerMessage(String borrowerMessage) {
		this.borrowerMessage = borrowerMessage;
	}

	public String getOwnerMessage() {
		return ownerMessage;
	}

	public void setOwnerMessage(String ownerMessage) {
		this.ownerMessage = ownerMessage;
	}

	public int getBorrowerScore() {
		return borrowerScore;
	}

	public void setBorrowerScore(int borrowerScore) {
		this.borrowerScore = borrowerScore;
	}

	public int getOwnerScore() {
		return ownerScore;
	}

	public void setOwnerScore(int ownerScore) {
		this.ownerScore = ownerScore;
	}

}
