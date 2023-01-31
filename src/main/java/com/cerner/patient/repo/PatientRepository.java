package com.cerner.patient.repo;

import org.springframework.stereotype.Repository;

import com.cerner.patient.Patient;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Optional<Patient> findByFirstName(String firstName);

}
