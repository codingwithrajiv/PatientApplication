package com.cerner.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "patienttb")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id", referencedColumnName ="patientId")
    private List<Addresses> addresses = new ArrayList<>();

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "fk_id", referencedColumnName ="patientId")
    private List<ContactNoDetails> contactNoDetails = new ArrayList<>();

	
	
	public List<Addresses> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Addresses> addresses) {
		this.addresses = addresses;
	}

	public List<ContactNoDetails> getContactNoDetails() {
		return contactNoDetails;
	}

	public void setContactNoDetails(List<ContactNoDetails> contactNoDetails) {
		this.contactNoDetails = contactNoDetails;
	}

	private String firstName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;

	public Patient() {
		super();
	}

	public Patient(Long patientId, String firstName, String lastName, String gender, Date dateOfBirth) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}


	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}

}
