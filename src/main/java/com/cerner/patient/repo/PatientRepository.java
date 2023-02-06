package com.cerner.patient.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.cerner.patient.Patient;

/**
 * 
 * The PatientRepository is an interface that extends the JpaRepository. It
 * provides methods to perform various CRUD operations related to the Patient
 * entity.
 * 
 * @author Cerner
 * @version 1.0
 * @since 1.0
 */

public interface PatientRepository extends JpaRepository<Patient, Long> {

	 List<Patient> findByFirstName(String firstName);

}
