package com.cerner.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Patient;
import com.cerner.patient.repo.PatientRepository;
import com.cerner.service.PatientService;

/**
 * 
 * PatientServiceImpl is a class which implements PatientService interface and
 * provides implementation for all the methods declared in the interface.
 * 
 * @author Cerner Corporation
 */
@Service
public class PatientServiceImpl implements PatientService {

	/**
	 * Autowired PatientRepository object to use all the methods from
	 * PatientRepository
	 */
	@Autowired
	private PatientRepository patientRepository;

	/**
	 * findAllPatient method is used to get all the details of the patients
	 * 
	 * @return List of Patient object
	 */
	@Override
	public List<Patient> findAllPatient() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	/**
	 * findById method is used to get the details of a particular patient by
	 * providing the id
	 * 
	 * @param id id of the patient
	 * @return Optional of Patient object
	 * @throws RecordNotFoundException throws the exception if patient with the
	 *                                 given id is not found
	 */
	@Override
	public Optional<Patient> findById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Patient existingPatient = patientRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Patient with Id : " + id + " does not exist"));

		return Optional.of(existingPatient);

		// return patientRepository.findById(id);
	}

	/**
	 * savePatient method is used to save the patient details in the database
	 * 
	 * @param patient object of patient with all the details
	 * @return object of patient with all the details
	 */
	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	/**
	 * updatePatient method is used to update the details of a particular patient by
	 * providing the id
	 * 
	 * @param patient object of patient with updated details
	 * @return object of patient with updated details
	 * @throws RecordNotFoundException 
	 */
	@Override
	public Patient updatePatient(Patient patient) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Patient existingPatient = patientRepository.findById(patient.getPatientId()).orElseThrow(() -> new RecordNotFoundException("Patient with Id : " + patient.getPatientId() + " does not exist"));
		
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

	/**
	 * deletePatient method is used to delete the details of a particular patient by
	 * providing the id
	 * 
	 * @param id id of the patient
	 */
	@Override
	public void deletePatient(Long id) {
		// TODO Auto-generated method stub
		patientRepository.deleteById(id);
	}

	/**
	 * findByName method is used to get the details of a particular patient by
	 * providing the id
	 * 
	 * @param Name of the patient
	 * @return Optional of Patient object
	 * @throws RecordNotFoundException throws the exception if patient with the
	 *                                 given id is not found
	 */
	@Override
	public List<Patient> findByName(String firstName) throws RecordNotFoundException {
		// TODO Auto-generated method stub

		List<Patient> existingPatient = patientRepository.findByFirstName(firstName);
				
		if(existingPatient==null || existingPatient.isEmpty()) {
			System.out.println("Hello Null????");
			throw new RecordNotFoundException("Patient with First Name : " + firstName + " does not exist");
		 
		}

		return existingPatient;
	}

}
