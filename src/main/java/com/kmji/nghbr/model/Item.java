package com.kmji.nghbr.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="APP_ITEM")
public class Item {
	
	@Id
	@Column(name="ITEM_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="DESCRIPTION", nullable=true)
	private String description;
	
	@Column(name="MIN_POINTS", nullable=true)
	private int minPoints;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OWNER_ID")
	private User owner;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BORROWER_ID", nullable=true)
	private User borrower;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private java.util.Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE", nullable=true)
	private java.util.Date endDate;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="item")
	private Set<BorrowRequest> borrowRequests;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="item")
	private Set<ReturnRequest> returnRequests;
	
	@Transient //Not mapped in hibernate
	private MultipartFile imageFile;
	
	public Set<ReturnRequest> getReturnRequests() {
		return returnRequests;
	}

	public void setReturnRequests(Set<ReturnRequest> returnRequests) {
		this.returnRequests = returnRequests;
	}

	public int getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(int minPoints) {
		this.minPoints = minPoints;
	}

	public Set<BorrowRequest> getBorrowRequests() {
		return borrowRequests;
	}

	public void setBorrowRequests(Set<BorrowRequest> borrowRequests) {
		this.borrowRequests = borrowRequests;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public MultipartFile  getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile  imageFile) {
		this.imageFile = imageFile;
	}

}
