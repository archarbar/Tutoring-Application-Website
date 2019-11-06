package ca.mcgill.ecse321.tutor.controller;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.service.TutorService;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {

	@Autowired
	private TimeSlotService service;
	@Autowired
	private TutorService tutorService;
	@Autowired
	private BookingService bookingService;	
	@Autowired
	private TutoringSessionService tutoringSessionService;

	@PostMapping("/timeslot/new")
	public TimeSlotDto createTimeSlot(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, 
			@RequestParam("dayOfTheWeek") String weekDay) {
		// parse string into time and dayoftheweek enum 
		Time st = Time.valueOf(startTime);
		Time et = Time.valueOf(endTime);
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.valueOf(weekDay.toUpperCase().trim());    	
		return convertToDto(service.createTimeSlot(st, et, dayOfTheWeek));
	}
	
	// USE CASE 3

	@PostMapping("/timeslot/{tutorId}/new")
	public void addTimeSlotForTutor(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, 
			@RequestParam("dayOfTheWeek") String weekDay,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
		tutorService.addTimeSlotForTutor(tutor, startTime, endTime, weekDay);
	}
	
	// USE CASE 3

	@PostMapping("/timeslot/{tutorId}/delete")
	public void removeTimeSlotForTutor(@RequestParam("timeSlotId") String timeSlotId,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
		tutorService.removeTimeSlotForTutor(tutor, Integer.parseInt(timeSlotId));
	}

	@GetMapping("/timeslot/{timeSlotId}")
	public TimeSlotDto getTimeSlotById(@PathVariable String timeSlotId) {
		return convertToDto(service.getTimeSlotById(Integer.parseInt(timeSlotId)));
	}


	@GetMapping("/timeslot/booking/{booking}")
	public TimeSlotDto getTimeSlotByBooking(@RequestParam String bookingId) {
		return convertToDto(service.getTimeSlotByBooking(bookingService.getBookingById(Integer.parseInt(bookingId))));
	}

	@GetMapping("/timeslot/tutoringsession/{tutoringSession}")
	public TimeSlotDto getTimeSlotByTutoringSession(@RequestParam String tutoringSessionId) {
		return convertToDto(service.getTimeSlotByTutoringSession(tutoringSessionService.getTutoringSessionById(Integer.parseInt(tutoringSessionId))));
	}

	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) throw new IllegalArgumentException("This timeSlot does not exist!");
		return new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(), timeSlot.getDayOfTheWeek(), timeSlot.getId());
	}
}
