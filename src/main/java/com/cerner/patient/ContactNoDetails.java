package com.cerner.patient;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@Override
	public String toString() {
		return "ContactNoDetails [teleType=" + teleType + ", countyCode=" + countyCode + ", teleNo=" + teleNo + "]";
	}

}
