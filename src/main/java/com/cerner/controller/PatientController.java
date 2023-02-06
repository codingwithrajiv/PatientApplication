package com.cerner.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.exception.RecordNotFoundException;
import com.cerner.patient.Patient;
import com.cerner.service.PatientService;

/**
 * 
 * The {@code PatientController} class implements the REST API for managing
 * patients.
 * 
 * @author Cerner Corporation
 * 
 * @version 1.0
 * 
 * @since 2020-06-01
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	/**
	 * 
	 * This method retrieves a list of all patients.
	 * 
	 * @return a list of all patients
	 */
	@GetMapping
	public List<Patient> findAllPatient() {
		return patientService.findAllPatient();
	}

	/**
	 * 
	 * This method retrieves a patient by its ID.
	 * 
	 * @param id the ID of the patient to retrieve
	 * @return an optional patient if found, otherwise an empty optional
	 * @throws RecordNotFoundException if the patient does not exist
	 */
	@GetMapping("id/{id}")
	public Optional<Patient> findPatientById(@PathVariable("id") Long id) throws RecordNotFoundException {
		return patientService.findById(id);
	}

	/**
	 * 
	 * This method retrieves a patient by its name.
	 * 
	 * @param firstName the first name of the patient to retrieve
	 * @return an optional patient if found, otherwise an empty optional
	 * @throws RecordNotFoundException if the patient does not exist
	 */
	@GetMapping("/name/{firstName}")
	public  List<Patient> findPatientByName(@PathVariable("firstName") String firstName)
			throws RecordNotFoundException {
		return patientService.findByName(firstName);
	}

	/**
	 * 
	 * This method saves a new patient.
	 * 
	 * @param patient the patient to save
	 * @return the saved patient
	 */
	@PostMapping
	public Patient savePatient(@RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}

	/**
	 * 
	 * This method updates an existing patient.
	 * 
	 * @param patient the patient to update
	 * @return the updated patient
	 * @throws RecordNotFoundException 
	 */
	@PutMapping
	public Patient updatePatient(@RequestBody Patient patient) throws RecordNotFoundException {
		return patientService.updatePatient(patient);
	}

	/**
	 * 
	 * This method deletes a patient by its ID.
	 * 
	 * @param id the ID of the patient to delete
	 */
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
	}
}
