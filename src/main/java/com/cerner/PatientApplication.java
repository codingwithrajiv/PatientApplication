package com.cerner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Patient Management System application.
 *
 * @author Cerner Corporation
 * @version 1.0
 */
@SpringBootApplication
public class PatientApplication {

	/**
	 * The main method that starts the application.
	 *
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}
}
