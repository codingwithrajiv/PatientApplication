package com.cerner.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * The class ContactNoDetails represents the telephone number details of a
 * patient. It contains the following fields - Id - Id of the record. It is
 * generated automatically. teleType - type of telephone number, for example,
 * home, work etc. countyCode - country code of the telephone number. teleNo -
 * telephone number.
 * 
 * @author cerner
 * @version 1.0
 */

@Entity
@Table(name = "contactnotb")
public class ContactNoDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String teleType;
	private String countyCode;
	private String teleNo;

	public ContactNoDetails() {
		super();
	}

	public ContactNoDetails(Long id, String teleType, String countyCode, String teleNo) {
		super();
		Id = id;
		this.teleType = teleType;
		this.countyCode = countyCode;
		this.teleNo = teleNo;
	}

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

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
