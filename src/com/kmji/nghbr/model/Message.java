package com.kmji.nghbr.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Proxy;

import java.util.Date;


@Entity
@Proxy(lazy=false)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="APP_MESSAGE")
public class Message {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Text", nullable=true)
	private String text;

	
	@Column(name="PostCode", nullable=false)
	private int postCode;
	
	@Column(name="Date")
	private Date date;
	
	@Column(name="STATE")
	private String state=State.ACTIVE.getState();

     @Lazy(false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User", nullable=true)
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


    @Transactional
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
		return "Message [id=" + id + ", message text=" + text + ", state=" + state +"]";
		
	}

}
