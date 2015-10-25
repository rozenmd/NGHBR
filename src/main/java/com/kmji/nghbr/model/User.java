package com.kmji.nghbr.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.IndexColumn;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="APP_USER")
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
		
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@Column(name="EMAIL", nullable=true)
	private String email;

	@ManyToOne
	@JoinColumn(name = "SUBURB", nullable=true)
	private Suburb suburb;

	@Column(name="FACEBOOK_ID", nullable=true)
	private String facebookId;

	@Column(name="POINTS", nullable=true)
	private int points = 0;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner")
	private List<Item> ownedItems;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="borrower")
	private List<Item> borrowedItems;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="borrower")
	private Set<BorrowRequest> sentBorrowRequests;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner")
	private Set<BorrowRequest> recievedBorrowRequests;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="borrower")
	private Set<ReturnRequest> sentReturnRequests;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner")
	private Set<ReturnRequest> recievedReturnRequests;

	public Set<ReturnRequest> getSentReturnRequests() {
		return sentReturnRequests;
	}

	public void setSentReturnRequests(Set<ReturnRequest> sentReturnRequests) {
		this.sentReturnRequests = sentReturnRequests;
	}

	public Set<ReturnRequest> getRecievedReturnRequests() {
		return recievedReturnRequests;
	}

	public void setRecievedReturnRequests(Set<ReturnRequest> recievedReturnRequests) {
		this.recievedReturnRequests = recievedReturnRequests;
	}

	public Set<BorrowRequest> getSentBorrowRequests() {
		return sentBorrowRequests;
	}

	public void setSentBorrowRequests(Set<BorrowRequest> sentBorrowRequests) {
		this.sentBorrowRequests = sentBorrowRequests;
	}

	public Set<BorrowRequest> getRecievedBorrowRequests() {
		return recievedBorrowRequests;
	}

	public void setRecievedBorrowRequests(Set<BorrowRequest> recievedBorrowRequests) {
		this.recievedBorrowRequests = recievedBorrowRequests;
	}

	@Column(name="STATE", nullable=false)
	private String state=State.ACTIVE.getState();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Suburb getSuburb() {
		return suburb;
	}

	public void setSuburb(Suburb suburb) {
		this.suburb = suburb;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public List<Item> getOwnedItems() {
		return ownedItems;
	}

	public void setOwnedItems(List<Item> ownedItems) {
		this.ownedItems = ownedItems;
	}

	public List<Item> getBorrowedItems() {
		return borrowedItems;
	}

	public void setBorrowedItems(List<Item> borrowedItems) {
		this.borrowedItems = borrowedItems;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public String getFacebookId() { return facebookId; }

	public void setFacebookId(String facebookId) { this.facebookId = facebookId; }

	public Collection<GrantedAuthority> getAuthorities() {
		//make everyone ROLE_USER
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority grantedAuthority = new GrantedAuthority() {
			//anonymous inner type
			public String getAuthority() {
				return "ROLE_USER";
			}
		};
		grantedAuthorities.add(grantedAuthority);
		return grantedAuthorities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles + ", facebookId=" + facebookId
				+ ", suburb=" + suburb +"]";
	}

	
}
