package com.cerner.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Addresses;
import com.cerner.patient.ContactNoDetails;
import com.cerner.patient.Patient;
import com.cerner.service.PatientService;

@WebMvcTest(PatientController.class)
class PatientControllerTesting {

	@Autowired
	private PatientController patientController;
	@MockBean
	private PatientService patientService;

	@Test
	public void testFindAllPatient() {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();

		Addresses address = new Addresses();

		address.setId(1L);
		address.setAddressType("Home");
		address.setStreetAddress("CITY Street");
		address.setState("ABC");
		address.setCity("CITY");
		address.setState("CITY");
		address.setZipCode("23847");
		address.setCountry("India");

		Addresses address2 = new Addresses();

		address2.setId(2L);
		address2.setAddressType("Office");
		address2.setStreetAddress("CITY Street");
		address2.setState("ABC");
		address2.setCity("CITY");
		address2.setState("CITY");
		address2.setZipCode("23847");
		address2.setCountry("India");

		addressList.add(address);
		addressList.add(address2);

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();

		ContactNoDetails contactNoDetails = new ContactNoDetails();
		contactNoDetails.setId(1L);
		contactNoDetails.setCountyCode("91");
		contactNoDetails.setTeleNo("23895739485");
		contactNoDetails.setTeleType("Home");

		ContactNoDetails contactNoDetails2 = new ContactNoDetails();
		contactNoDetails2.setId(2L);
		contactNoDetails2.setCountyCode("91");
		contactNoDetails2.setTeleNo("23895739485");
		contactNoDetails2.setTeleType("Office");

		teleList.add(contactNoDetails);
		teleList.add(contactNoDetails2);

		patient.setContactNoDetails(teleList);

		Patient patient2 = new Patient();
		patient2.setPatientId(2L);
		patient2.setFirstName("Rajiv");
		patient2.setLastName("Singh");
		patient2.setGender("MALE");
		patient2.setDateOfBirth(new Date());

		List<Addresses> addressList2 = new ArrayList<>();
		addressList2.add(new Addresses(1L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList2.add(new Addresses(2L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient2.setAddresses(addressList2);

		List<ContactNoDetails> teleList2 = new ArrayList<>();
		teleList2.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList2.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient2.setContactNoDetails(teleList2);

		List<Patient> patient3 = new ArrayList<>();
		patient3.add(patient);
		patient3.add(patient2);

		Mockito.when(patientService.findAllPatient()).thenReturn(patient3);
		assertThat(patientController.findAllPatient()).isEqualTo(patient3);

	}

	@Test
	public void testFindPatientById() throws RecordNotFoundException {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientService.findById(1L)).thenReturn(patientOptional);
		assertThat(patientController.findPatientById(1L)).isEqualTo(patientOptional);

	}

	@Test
	public void testFindByName() throws RecordNotFoundException {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		//Optional<Patient> patientOptional = Optional.of(patient);
		
		List<Patient> patientList = new ArrayList<>();
		patientList.add(patient);
		//patient3.add(patient2);

		Mockito.when(patientService.findByName("Rajiv")).thenReturn(patientList);

		assertThat(patientController.findPatientByName("Rajiv")).isEqualTo(patientList);

	}

	@Test
	public void testSavePatient() {

		Date date = new Date();
		Patient patient = new Patient(1L, "Rajiv", "Singh,", "MALE", date);
		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Mockito.when(patientService.savePatient(patient)).thenReturn(patient);

		assertThat(patientController.savePatient(patient)).isEqualTo(patient);

		Patient actual = patientController.savePatient(patient);
		assertEquals(patient.getFirstName(), actual.getFirstName());
		assertEquals(patient.getLastName(), actual.getLastName());
		assertEquals(patient.getGender(), actual.getGender());
		assertEquals(patient.getContactNoDetails(), actual.getContactNoDetails());
		assertEquals(patient.getAddresses(), actual.getAddresses());
		assertEquals(patient.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(patient.getPatientId(), actual.getPatientId());

		assertEquals(patient.getAddresses().get(0).getId(), actual.getAddresses().get(0).getId());
		assertEquals(patient.getAddresses().get(0).getCity(), actual.getAddresses().get(0).getCity());
		assertEquals(patient.getAddresses().get(0).getStreetAddress(), actual.getAddresses().get(0).getStreetAddress());
		assertEquals(patient.getAddresses().get(0).getAddressType(), actual.getAddresses().get(0).getAddressType());
		assertEquals(patient.getAddresses().get(0).getState(), actual.getAddresses().get(0).getState());
		assertEquals(patient.getAddresses().get(0).getZipCode(), actual.getAddresses().get(0).getZipCode());
		assertEquals(patient.getAddresses().get(0).getCountry(), actual.getAddresses().get(0).getCountry());

		assertEquals(patient.getContactNoDetails().get(0).getId(), actual.getContactNoDetails().get(0).getId());
		assertEquals(patient.getContactNoDetails().get(0).getCountyCode(),
				actual.getContactNoDetails().get(0).getCountyCode());
		assertEquals(patient.getContactNoDetails().get(0).getTeleType(),
				actual.getContactNoDetails().get(0).getTeleType());
		assertEquals(patient.getContactNoDetails().get(0).getTeleNo(), actual.getContactNoDetails().get(0).getTeleNo());

	}

	@Test
	public void testUpdatePatient() throws RecordNotFoundException {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "office", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientService.findById(1L)).thenReturn(patientOptional);

		patient.setFirstName("martin");

		Mockito.when(patientService.updatePatient(patient)).thenReturn(patient);

		assertThat(patientController.updatePatient(patient)).isEqualTo(patient);

	}

	@Test
	public void testDeletePatient() throws RecordNotFoundException {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "Office", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "Home", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientService.findById(1L)).thenReturn(patientOptional);
		patientController.deletePatient(1L);
		Mockito.when(patientService.findById(patient.getPatientId())).thenReturn(patientOptional);

	}

}
