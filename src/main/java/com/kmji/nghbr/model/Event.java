package com.kmji.nghbr.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="APP_EVENT")
public class Event {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="DESCRIPTION", nullable=true)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOST_ID")
    private User host;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SURBURB_ID")
    private Suburb suburb;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="START_DATE")
    private java.util.Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="END_DATE")
    private java.util.Date endDate;


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

    public User getHost() {
        return host;
    }

    public void setOwner(User host) {
        this.host = host;
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

    public Suburb getSuburb() { return suburb; }
    public void setSuburb(Suburb suburb) { this.suburb = suburb; }

}
