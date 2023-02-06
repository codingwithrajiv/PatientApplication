package com.cerner.patientApplicationUI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cerner.model.Addresses;
import com.cerner.model.ContactNoDetails;
import com.cerner.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {


	public static final String POSTS_API_URL = "http://localhost:8080/patient";

	public static void main(String[] args) throws IOException, InterruptedException {
       
    	HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       // System.out.println(response.body());
        
        // parse JSON
       ObjectMapper mapper = new ObjectMapper();
       // List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {});

        // posts.forEach(post -> {
        //     System.out.println(post.toString());
        // });
       // posts.forEach(System.out::println);
       
      // fetchPatientList();
       
		/*
		 * FieldValidation fieldV = new FieldValidation();
		 * 
		 * boolean abr = fieldV.validateOnlyAlphabet("RAjiv");
		 * 
		 * System.out.println(abr);
		 */
       
       Patient patient = new Patient();
		//patient.setPatientId(12L);
		patient.setFirstName("UpdatedRajiv");
		patient.setLastName("Singh");
		patient.setGender("MALE");
		Date date = new Date();
		patient.setDateOfBirth(date);

		List<Addresses> addressList = new ArrayList<>();
		addressList.add(new Addresses("Home", "STREET", "LOCALITY", "JH", "345654", "Ind"));
		addressList.add(new Addresses("Home", "STREET", "LOCALITY", "JH", "345654", "Ind"));

		patient.setAddresses(addressList);

		List<ContactNoDetails> teleList = new ArrayList<>();
		teleList.add(new ContactNoDetails( "Home", "91", "39842643875"));
		teleList.add(new ContactNoDetails( "OFFICE", "915", "39842"));

		patient.setContactNoDetails(teleList);
       
       HttpRestClient http = new HttpRestClient();
       
       //http.deletePatient("5");
      // http.findAllPatient();
       //http.findPatientById(4L);
      //http.findPatientByName("Raj");
       //http.updatePatient(patient);
       http.savePatient(patient);
	}
	
	public static void fetchPatientList() throws IOException, InterruptedException {
	     String serviceUrl = "http://localhost:8080/patient";
		 var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).header("Content-Type", "application-json").GET().build();
		 var client = HttpClient.newHttpClient();
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 ObjectMapper om = new ObjectMapper();
		 System.out.println(response.body());

	}


}
