package com.kmji.nghbr.model;


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

import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="APP_MESSAGE")
public class Message {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Textt", nullable=true)
	private String text;
	
	@Column(name="Username", nullable=false)
	private String username;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "User", nullable=true)
	private User user;
	
	@Column(name="PostCode", nullable=false)
	private int postCode;
	
	@Column(name="Date")
	private Date date;
	
	@Column(name="STATE")
	private String state=State.ACTIVE.getState();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPostCode() {
		return postCode;
	}
	
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", username=" + username + ", message text=" + text + ", state=" + state +"]";
		
	}

}
