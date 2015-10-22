package com.kmji.nghbr.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APP_POSTCODE_DB")
public class Postcode implements Serializable {

	@Id
	@Column(name = "SUBURB_POSTCODE", nullable = false)
	private int postcode;

	@Id
	@Column(name = "SUBURB_SUBURB")
	private String suburb;

	@Column(name = "SUBURB_STATE")
	private String state;

	@Column(name = "SUBURB_DC")
	private String dc;

	@Column(name = "SUBURB_TYPE")
	private String type;

	@Column(name = "SUBURB_LAT")
	private double lat;

	@Column(name = "SUBURB_LON")
	private double lon;


	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setId(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
}
