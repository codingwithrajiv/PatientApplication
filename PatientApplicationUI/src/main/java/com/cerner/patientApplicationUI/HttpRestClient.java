package com.cerner.patientApplicationUI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.cerner.model.Patient;
import com.cerner.validation.FieldValidation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpRestClient {

	public static final String POST_API_URL = "http://localhost:8080/patient";
	public static final String GET_API_URL = "http://localhost:8080/patient";
	public static final String GET_API_URL_BY_NAME= "http://localhost:8080/patient/name/";
	public static final String GET_API_URL_BY_ID= "http://localhost:8080/patient/id/";
	public static final String PUT_API_URL= "http://localhost:8080/patient/";
	public static final String DELETE_API_URL_BY_ID= "http://localhost:8080/patient/";
	
	public static List<Patient> findPatientByName(String patientName) throws IOException, InterruptedException {
		
		String url = GET_API_URL_BY_NAME+patientName;
		var request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application-json")
				.GET()
				.build();
		 var client = HttpClient.newHttpClient();
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 System.out.println(response.statusCode());
		 System.out.println(response.body());
		 ObjectMapper om = new ObjectMapper();
		 
		 return om.readValue(response.body(), new TypeReference<List<Patient>>() {
		});

	}

	public static Patient findPatientById(Long patientId) throws IOException, InterruptedException {

		String url = GET_API_URL_BY_ID+patientId;
		var request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application-json")
				.GET()
				.build();
		 var client = HttpClient.newHttpClient();
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 System.out.println(response.statusCode());
		 System.out.println(response.body());
		 ObjectMapper om = new ObjectMapper();
		 
		 return om.readValue(response.body(), new TypeReference<Patient>() {
		});

	}

	public static List<Patient> findAllPatient() throws IOException, InterruptedException {

		String url = GET_API_URL;
		var request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application-json")
				.GET()
				.build();
		 var client = HttpClient.newHttpClient();
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 System.out.println(response.statusCode());
		 System.out.println(response.body());
		 ObjectMapper om = new ObjectMapper();
		 
		 return om.readValue(response.body(), new TypeReference<List<Patient>>() {
		});
	}

	public static HttpResponse<String> deletePatient(String patientId) throws IOException, InterruptedException{
		
		String url = DELETE_API_URL_BY_ID+patientId;
		System.out.println(url);
		var request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application-json")
				.DELETE()
				.build();
		 var client = HttpClient.newHttpClient();
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 System.out.println(response.statusCode());
		 System.out.println(response.body());
		 return response;
		
	}
	
	public static HttpResponse<String> savePatient(Patient patient) throws IOException, InterruptedException {
		String url = POST_API_URL;
		ObjectMapper om = new ObjectMapper();
		//String requestBody = om.writeValueAsString(patient);
		
		   HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .header("Content-Type", "application/json")
	                .POST(HttpRequest.BodyPublishers.ofString(
	                        om.writeValueAsString(patient),
	                        StandardCharsets.UTF_8
	                ))
	                .build();
		
		
			/*
			 * var request = HttpRequest.newBuilder() .uri(URI.create(url))
			 * .header("Content-Type", "application-json") .POST(null) .build(); var client
			 * = HttpClient.newHttpClient();
			 */
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 System.out.println(response.statusCode());
		 System.out.println(response.body());
		// ObjectMapper om = new ObjectMapper();
		 
		 //return om.readValue(response.body(), new TypeReference<Patient>() {});
		 return response;
	}
	
	public static  HttpResponse<String> updatePatient(Patient patient) throws IOException, InterruptedException {

		String url = PUT_API_URL;
	
		ObjectMapper om = new ObjectMapper();
		//String requestBody = om.writeValueAsString(patient);
		
		   HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .header("Content-Type", "application/json")
	                .POST(HttpRequest.BodyPublishers.ofString(
	                        om.writeValueAsString(patient),
	                        StandardCharsets.UTF_8
	                ))
	                .build();
		
		
			/*
			 * var request = HttpRequest.newBuilder() .uri(URI.create(url))
			 * .header("Content-Type", "application-json") .POST(null) .build(); var client
			 * = HttpClient.newHttpClient();
			 */
		 var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 System.out.println(response.statusCode());
		 System.out.println(response.body());
		// ObjectMapper om = new ObjectMapper();
		 
		 //return om.readValue(response.body(), new TypeReference<Patient>() {});
		 return response;
	
	}
}