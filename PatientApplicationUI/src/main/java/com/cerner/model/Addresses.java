package com.cerner.model;

public class Addresses {

	//private Long Id;
	private String addressType;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	public Addresses( String addressType, String streetAddress, String city, String state, String zipCode,
			String country) {
		super();
		//Id = id;
		this.addressType = addressType;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	
	public Addresses() {
		super();
	}



	/*
	 * public Long getId() { return Id; }
	 * 
	 * public void setId(Long id) { Id = id; }
	 */
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
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

	@Override
	public String toString() {
		return "Address [ addressType=" + addressType + ", streetAddress=" + streetAddress + ", city="
				+ city + ", state=" + state + ", zipCode=" + zipCode + ", country=" + country + "]";
	}
	
	
}
