package com.cerner.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FieldValidation {

	 private Pattern onlyAlphabet;
	 private Pattern onlyNumeric;
	 private Pattern tenDigitOnly;
	 
	 
	 public FieldValidation() {
		 
		    onlyAlphabet = Pattern.compile("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$");
	        onlyNumeric = Pattern.compile("[0-9]+");  //"[0-9]+";
	        tenDigitOnly = Pattern.compile("^\\d{10}$");
	    }
	
	 public boolean validateOnlyAlphabet(String fieldValue) {
	
	        Matcher matcher = onlyAlphabet.matcher(fieldValue);
	        return matcher.matches();
	    }
	 
	 public boolean validateOnlyNumeric(String fieldValue) {
			
	        Matcher matcher = onlyNumeric.matcher(fieldValue);
	        return matcher.matches();
	    }
	 
	 public boolean validatePhoneNo(String phoneNo) {
			/*
			 * if (phoneNo == null || phoneNo.trim().isEmpty()) { return false; }
			 */
	        Matcher matcher = tenDigitOnly.matcher(phoneNo);
	        return matcher.matches();
	    }
	 
	 
}
