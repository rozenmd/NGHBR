package com.kmji.nghbr.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="APP_USER")
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="SSO_ID", unique=true, nullable=true)
	private String ssoId;
	
	@Column(name="PASSWORD", nullable=true)
	private String password;
		
	@Column(name="FIRST_NAME", nullable=true)
	private String firstName;

	@Column(name="LAST_NAME", nullable=true)
	private String lastName;

	@Column(name="EMAIL", nullable=true)
	private String email;

	@Column(name="FACEBOOK_ID", nullable=false)
	private String facebookId;
	
	@Column(name="POSTCODE", nullable=false)
	private int postcode;
	
	@Column(name="POINTS", nullable=false)
	private int points;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner")
	private List<Item> ownedItems;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="borrower")
	private List<Item> borrowedItems;

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
	
	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int suburbId) {
		this.postcode = postcode;
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
				+ ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles + ", facebookId=" + facebookId +"]";
	}

	
}
