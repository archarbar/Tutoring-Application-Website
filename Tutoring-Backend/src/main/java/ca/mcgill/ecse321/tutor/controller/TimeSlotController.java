package ca.mcgill.ecse321.tutor.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.service.TutorService;

/**
 * This class provides controller methods for the timeslot class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {

	@Autowired
	private TimeSlotService service;
	@Autowired
	private TutorService tutorService;
	
	/**
	 * API call to get all timeslots
	 * 
	 * @return A timeslot DTO
	 */
	
    @GetMapping(value= {"/timeslots", "/timeslots/"})
    public List<TimeSlotDto> getAllTimeSlots(){
    	List<TimeSlotDto> tSDtos = new ArrayList<TimeSlotDto>();
    	for(TimeSlot timeSlot: service.getAllTimeSlots()) {
    		tSDtos.add(convertToDto(timeSlot));
    	}
    	return tSDtos;
    }   
    
    /**
     * API call to get a timeslot using its id
     * 
     * @param timeSlotId The ID of the timeslot
     * @return A timeslot DTO
     */
    
	@GetMapping("/timeslot/{timeSlotId}")
	public TimeSlotDto getTimeSlotById(@PathVariable String timeSlotId) {
		return convertToDto(service.getTimeSlotById(Integer.parseInt(timeSlotId)));
	}
	
	/**
	 * API call to get all the timeslots of a specific tutor
	 * 
	 * @param tutorId The ID of the tutor
	 * @return A timeslot DTO
	 */
	
	@GetMapping("/timeslot/tutor/{tutorId}")
	public ArrayList<TimeSlotDto> getTimeSlotByTutor(@PathVariable String tutorId) {
		ArrayList<TimeSlotDto> returnTimeSlot = new ArrayList<>();
		ArrayList<TimeSlot> timeSlots = service.getTimeSlotByTutor(Integer.parseInt(tutorId));
		for (TimeSlot timeSlot : timeSlots) {
			returnTimeSlot.add(convertToDto(timeSlot));
		}
		return returnTimeSlot;
	}
	
	/**
	 * API call to post a new timeslot
	 * 
	 * @param startTime The start time of the timeslot
	 * @param endTime The end time of the timeslot
	 * @param weekDay The day in the week
	 * @return A timeslot DTO
	 */

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
	
	/**
	 * API call to post a new timeslot for a specific tutor
	 * 
	 * @param startTime The start time of the timeslot
	 * @param endTime The end time of the timeslot
	 * @param weekDay The day in the week
	 * @param tutorId The ID of the tutor
	 */

	@PostMapping("/timeslot/tutor/new")
	public void addTimeSlotForTutor(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, 
			@RequestParam("dayOfTheWeek") String weekDay,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
		tutorService.addTimeSlotForTutor(tutor, startTime, endTime, weekDay);
	}
	
	/**
	 * API call to delete a specific timeslot for a tutor
	 * 
	 * @param timeSlotId The ID of the timeslot
	 * @param tutorId The ID of the tutor
	 */

	@DeleteMapping("/timeslot/tutor/delete")
	public void removeTimeSlotForTutor(@RequestParam("timeSlotId") String timeSlotId,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
		tutorService.removeTimeSlotForTutor(tutor, Integer.parseInt(timeSlotId));
	}
	
	/**
	 * Method to convert a timeslot object into a timeslot dto
	 * 
	 * @param timeSlot A timeslot object
	 * @return A timeslot DTO
	 */

	public TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) throw new IllegalArgumentException("This timeSlot does not exist!");
		return new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(), timeSlot.getDayOfTheWeek(), timeSlot.getId());
	}
}
