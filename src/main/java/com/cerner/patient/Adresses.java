package com.cerner.patient;

public class Adresses {

	private String houseNO;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	public Adresses(String houseNO, String streetAddress, String city, String state, String zipCode, String country) {
		super();
		this.houseNO = houseNO;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public String getHouseNO() {
		return houseNO;
	}

	public void setHouseNO(String houseNO) {
		this.houseNO = houseNO;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
