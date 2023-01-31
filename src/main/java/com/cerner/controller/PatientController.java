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

@RestController
@RequestMapping("/patient")
public class PatientController {
 
	@Autowired
	private  PatientService patientService;


    @GetMapping
    public List<Patient> findAllPatient() {
        return patientService.findAllPatient();
    }
    
    @GetMapping("id/{id}")
    public Optional<Patient> findPatientById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return patientService.findById(id);
    }

    @GetMapping("/name/{firstName}")
    public Optional<Patient> findPatientByName(@PathVariable("firstName") String firstName) throws RecordNotFoundException {
        return patientService.findByName(firstName);
    }
    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") Long id) {
    	patientService.deletePatient(id);
    }
	
}
