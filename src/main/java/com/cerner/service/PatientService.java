package com.cerner.service;

import java.util.List;
import java.util.Optional;

import com.cerner.patient.Patient;

public interface PatientService {
	
	    List<Patient> findAllPatient();
	    Optional<Patient> findById(Long id);
	    Optional<Patient> findByName(String firstName);
	    Patient savePatient(Patient patient);
	    Patient updatePatient(Patient patient);
	    void deletePatient(Long id);
}
