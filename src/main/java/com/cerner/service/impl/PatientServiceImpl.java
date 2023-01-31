package com.cerner.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Addresses;
import com.cerner.patient.ContactNoDetails;
import com.cerner.patient.Patient;
import com.cerner.patient.repo.PatientRepository;
import com.cerner.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	  private  PatientRepository patientRepository;

	@Override
	public List<Patient> findAllPatient() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> findById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Patient existingPatient = patientRepository.findById(id).
				orElseThrow(() -> new RecordNotFoundException("Patient with Id : " + id + " does not exist"));
		
		return Optional.of(existingPatient);
		
	    //return patientRepository.findById(id);
	}

	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(Patient patient) {
		// TODO Auto-generated method stub
	    Patient existingPatient = patientRepository.findById(patient.getPatientId()).orElse(null);
	    if (patient.getPatientId() == null) {
            return null;
        }
	    
        if (existingPatient == null) {
            return null;
        }     
        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        existingPatient.setGender(patient.getGender());
    
        existingPatient.setAddresses(patient.getAddresses());
        existingPatient.setContactNoDetails(patient.getContactNoDetails());
      
        Patient savedPatient = patientRepository.save(existingPatient);
        return savedPatient;
       // return patientRepository.save(existingPatient);
	}

	@Override
	public void deletePatient(Long id) {
		// TODO Auto-generated method stub
		patientRepository.deleteById(id);
	}

	@Override
	public Optional<Patient> findByName(String firstName) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
		Patient existingPatient = patientRepository.findByFirstName(firstName).
				orElseThrow(() -> new RecordNotFoundException("Patient with Id : " + firstName + " does not exist"));
		
		return Optional.of(existingPatient);
		
		//return patientRepository.findByFirstName(firstName);
	}

}
