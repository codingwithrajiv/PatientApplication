package com.cerner.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cerner.exception.CustomExceptionHandler;
import com.cerner.exception.ErrorResponse;
import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Addresses;
import com.cerner.patient.ContactNoDetails;
import com.cerner.patient.Patient;
import com.cerner.patient.repo.PatientRepository;
import com.cerner.service.impl.PatientServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceTests {

	@Autowired
	private PatientService patientService;

	@MockBean
	private PatientRepository patientRepository;

	@Test
	public void findAllPatientTest() {

		Date date = new Date();
		System.out.println("Hello There" + date);
		when(patientRepository.findAll()).thenReturn(Stream.of(new Patient(011L, "Rajiv", "Ranjan,", "MALE", date),
				new Patient(0114L, "Rajiv4", "Ranjan4,", "MALE", date)).collect(Collectors.toList()));
		assertEquals(2, patientService.findAllPatient().size());
	}

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
		addressList.add(new Addresses(1L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Patient patient2 = new Patient();
		patient2.setPatientId(1L);
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

		Mockito.when(patientRepository.findAll()).thenReturn(patient3);

		assertThat(patientService.findAllPatient()).isEqualTo(patient3);

	}

	@Test
	public void testSavePatient() {

		Date date = new Date();
		Patient patient = new Patient(1L, "Rajiv", "Singh,", "MALE", date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Mockito.when(patientService.savePatient(patient)).thenReturn(patient);

		assertThat(patientRepository.save(patient)).isEqualTo(patient);

		Patient actual = patientService.savePatient(patient);
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
		assertEquals(patient.getAddresses().get(0).getHouseNO(), actual.getAddresses().get(0).getHouseNO());
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
	public void testFindById() throws RecordNotFoundException {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientRepository.findById(1L)).thenReturn(patientOptional);

		assertThat(patientService.findById(1L)).isEqualTo(patientOptional);

	}

	@Test
	public void testUpdatePatient() {

		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("Rajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses(1L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientRepository.findById(1L)).thenReturn(patientOptional);

		patient.setFirstName("martin");
		Mockito.when(patientRepository.save(patient)).thenReturn(patient);

		assertThat(patientService.updatePatient(patient)).isEqualTo(patient);
	}

	@Test
	public void testDeletePatient() {

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
		address.setHouseNO("2B");
		address.setStreetAddress("CITY Street");
		address.setState("ABC");
		address.setCity("CITY");
		address.setState("Jh");
		address.setCountry("India");
		address.setZipCode("23847");

		Addresses address2 = new Addresses();

		address2.setId(2L);
		address2.setHouseNO("2B");
		address2.setStreetAddress("CITY Street");
		address2.setState("ABC");
		address2.setCity("CITY");
		address2.setState("CITY");
		address2.setCountry("India");
		address2.setZipCode("23847");

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

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientRepository.existsById(patient.getPatientId())).thenReturn(false);
		patientService.deletePatient(1L);
		assertFalse(patientRepository.existsById(patient.getPatientId()));

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
		addressList.add(new Addresses(1L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses(2L, "12", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails(1L, "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails(2L, "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);

		Optional<Patient> patientOptional = Optional.of(patient);

		Mockito.when(patientRepository.findByFirstName("Rajiv")).thenReturn(patientOptional);

		assertThat(patientService.findByName("Rajiv")).isEqualTo(patientOptional);

	}

	@Test
	public void testGetPatientById_RecordNotFoundException() {
		//Given
		Long patientId = 100L;
	     when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
		
		RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
			patientService.findById(patientId);
			});
			CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
			ResponseEntity<ErrorResponse> response = customExceptionHandler.handleException(exception);
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		    assertEquals("Patient with Id : " + patientId + " does not exist", response.getBody().getMessage());
		
	}

}
