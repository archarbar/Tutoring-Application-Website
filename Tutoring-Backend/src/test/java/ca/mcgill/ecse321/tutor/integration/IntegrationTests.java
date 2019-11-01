package ca.mcgill.ecse321.tutor.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.testng.annotations.Test;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.service.BookingService;

public class IntegrationTests {
	
	@Autowired
	BookingRepository bookingRepository; // Not needed because we query it from other teams
	@Autowired
	BookingService bookingService;
	
	// Use case 1: The system shall allow a potential tutor to submit their job application by submitting his first name, last name, highest level of education, phone number, email and resume.
	// Use case 2: The system shall allow a verified tutor to create an account with the approved courses from the application by setting his password, availabilities, and hourly rate.
	// Use case 3: The system shall allow a tutor with an account to modify his availabilities. 
	// Use case 4: The system shall allow a tutor with an account to modify his course offerings (adding courses requires manager approval). 
	
	
	
	
	

}
