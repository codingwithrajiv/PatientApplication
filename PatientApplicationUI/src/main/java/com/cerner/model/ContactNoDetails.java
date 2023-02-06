package com.cerner.model;

public class ContactNoDetails {

	
	//private Long id;
	private String teleType;
	private String countyCode;
	private String teleNo;
	
	public ContactNoDetails(String teleType, String countyCode, String teleNo) {
		super();
		//this.id = id;
		this.teleType = teleType;
		this.countyCode = countyCode;
		this.teleNo = teleNo;
	}

	
	public ContactNoDetails() {
		super();
	}



	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 */

	public String getTeleType() {
		return teleType;
	}

	public void setTeleType(String teleType) {
		this.teleType = teleType;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	@Override
	public String toString() {
		return "ContactNoDetails [ teleType=" + teleType + ", countyCode=" + countyCode + ", teleNo="
				+ teleNo + "]";
	}
	
	
	
}
