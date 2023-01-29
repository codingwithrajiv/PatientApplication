package com.cerner.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cerner.patient.Patient;
import com.cerner.patient.repo.PatientRepository;
import com.cerner.service.PatientService;
import com.cerner.service.impl.PatientServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {
	
	@Autowired
	private PatientServiceImpl patientServiceImpl;

	@MockBean
	private PatientRepository patientRepository;

	@Test
	public void findAllPatientTest() {
		System.out.println("Hello There");
		when(patientRepository.findAll()).thenReturn(Stream
				.of(new Patient(011L, "Rajiv","Ranjan,","MALE",null), new Patient(0114L, "Rajiv4","Ranjan4,","MALE",null)).collect(Collectors.toList()));
		assertEquals(2, patientServiceImpl.findAllPatient().size());
	}
	
	
	@Test
	public void findAllPatientTest2() {
		Patient patient = new Patient(0007L, null, null, null, null);
Mockito.when(patientServiceImpl.savePatient(patient)).thenReturn(patient);
	    
	    assertThat(patientServiceImpl.savePatient(patient)).isEqualTo(patient);
	}
	
	
	

}
