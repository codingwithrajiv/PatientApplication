package com.cerner.service;

import java.util.List;
import java.util.Optional;

import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Patient;

/**
 * 
 * This interface is used to declare the methods for the patient service
 * operations.
 * 
 * @author Cerner
 * @version 1.0
 */

public interface PatientService {

	/**
	 * This method is used to find all the patients.
	 * 
	 * @return list of patients
	 */
	List<Patient> findAllPatient();

	/**
	 * This method is used to find a patient by id.
	 * 
	 * @param id the id of the patient
	 * @return the patient
	 * @throws RecordNotFoundException if no patient is found with the provided id
	 */
	Optional<Patient> findById(Long id) throws RecordNotFoundException;

	/**
	 * This method is used to find a patient by name.
	 * 
	 * @param firstName the name of the patient
	 * @return the patient
	 * @throws RecordNotFoundException if no patient is found with the provided name
	 */
	List<Patient> findByName(String firstName) throws RecordNotFoundException;

	/**
	 * This method is used to save a patient.
	 * 
	 * @param patient the patient to save
	 * @return the saved patient
	 */
	Patient savePatient(Patient patient);

	/**
	 * This method is used to update a patient.
	 * 
	 * @param patient the patient to update
	 * @return the updated patient
	 * @throws RecordNotFoundException 
	 */
	Patient updatePatient(Patient patient) throws RecordNotFoundException;

	/**
	 * This method is used to delete a patient.
	 * 
	 * @param id the id of the patient to delete
	 */
	void deletePatient(Long id);

}
