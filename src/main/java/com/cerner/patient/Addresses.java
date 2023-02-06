package com.cerner.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * The Addresses class represents a physical address of a patient. It includes
 * details such as the house number, street address, city, state, zip code, and
 * country. It is a part of the one to many relationship with the Patient
 * entity.
 * 
 * @author Cerner Corporation
 */
@Entity
@Table(name = "addresstb")
public class Addresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String addressType;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;

	public Addresses() {
		super();
	}

	public Addresses(Long id, String addressType, String streetAddress, String city, String state, String zipCode,
			String country) {
		super();
		Id = id;
		this.addressType = addressType;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

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

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
