package ca.mcgill.ecse321.tutor.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.ManagerDto;
import ca.mcgill.ecse321.tutor.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Student;
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
	
	
    @GetMapping(value= {"/timeslots", "/timeslots/"})
    public List<TimeSlotDto> getAllTimeSlots(){
    	List<TimeSlotDto> tSDtos = new ArrayList<TimeSlotDto>();
    	for(TimeSlot timeSlot: service.getAllTimeSlots()) {
    		tSDtos.add(convertToDto(timeSlot));
    	}
    	return tSDtos;
    }   
    
	@GetMapping("/timeslot/{timeSlotId}")
	public TimeSlotDto getTimeSlotById(@PathVariable String timeSlotId) {
		return convertToDto(service.getTimeSlotById(Integer.parseInt(timeSlotId)));
	}
	
	@GetMapping("/timeslot/tutor/{tutorId}")
	public ArrayList<TimeSlotDto> getTimeSlotByTutor(@PathVariable String tutorId) {
		ArrayList<TimeSlotDto> returnTimeSlot = new ArrayList<>();
		ArrayList<TimeSlot> timeSlots = service.getTimeSlotByTutor(Integer.parseInt(tutorId));
		for (TimeSlot timeSlot : timeSlots) {
			returnTimeSlot.add(convertToDto(timeSlot));
		}
		return returnTimeSlot;
	}

	@PostMapping("/timeslot/new")
	public TimeSlotDto createTimeSlot(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, 
			@RequestParam("weekDay") String weekDay) 
					 {
		// parse string into time and dayoftheweek enum 
		Time st = Time.valueOf(startTime);
		Time et = Time.valueOf(endTime);
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.valueOf(weekDay.toUpperCase().trim());    	
		return convertToDto(service.createTimeSlot(st, et, dayOfTheWeek));
	}
	
	// USE CASE 3

	@PostMapping("/timeslot/tutor/new")
	public void addTimeSlotForTutor(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, 
			@RequestParam("dayOfTheWeek") String weekDay,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
//		Set<Tutor> tutorSet = new HashSet<Tutor>();
		tutorService.addTimeSlotForTutor(tutor, startTime, endTime, weekDay);
	}
	
	// USE CASE 3

	@DeleteMapping("/timeslot/tutor/delete")
	public void removeTimeSlotForTutor(@RequestParam("timeSlotId") String timeSlotId,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
		tutorService.removeTimeSlotForTutor(tutor, Integer.parseInt(timeSlotId));
	}
	

	@GetMapping("/timeslot/booking/{bookingId}")
	public TimeSlotDto getTimeSlotByBooking(@RequestParam String bookingId) {
		return convertToDto(service.getTimeSlotByBooking(bookingService.getBookingById(Integer.parseInt(bookingId))));
	}

	@GetMapping("/timeslot/tutoringsession/{tutoringSessionId}")
	public TimeSlotDto getTimeSlotByTutoringSession(@RequestParam String tutoringSessionId) {
		return convertToDto(service.getTimeSlotByTutoringSession(tutoringSessionService.getTutoringSessionById(Integer.parseInt(tutoringSessionId))));
	}

	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) throw new IllegalArgumentException("This timeSlot does not exist!");
		return new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(), timeSlot.getDayOfTheWeek(), timeSlot.getId());
	}
}
