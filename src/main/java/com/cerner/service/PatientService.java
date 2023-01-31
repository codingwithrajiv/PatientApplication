package com.cerner.service;

import java.util.List;
import java.util.Optional;

import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Patient;

public interface PatientService {
	
	    List<Patient> findAllPatient();
	    Optional<Patient> findById(Long id) throws RecordNotFoundException;
	    Optional<Patient> findByName(String firstName) throws RecordNotFoundException;
	    Patient savePatient(Patient patient);
	    Patient updatePatient(Patient patient);
	    void deletePatient(Long id);
}
