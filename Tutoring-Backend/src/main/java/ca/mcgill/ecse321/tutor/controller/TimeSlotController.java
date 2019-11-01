package ca.mcgill.ecse321.tutor.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {

    @Autowired
    private TimeSlotService service;
    
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

    @GetMapping("/timeslot/{timeSlotId}")
    public TimeSlotDto getTimeSlotById(@PathVariable String timeSlotId) {
        return convertToDto(service.getTimeSlotById(Integer.parseInt(timeSlotId)));
    }

    @GetMapping("/timeslot/booking/{booking}")
    public TimeSlotDto getTimeSlotByBooking(@RequestParam Booking booking) {
        return convertToDto(service.getTimeSlotByBooking(booking));
    }

    @GetMapping("/timeslot/tutoringsession/{tutoringSession}")
    public TimeSlotDto getTimeSlotByTutoringSession(@RequestParam TutoringSession tutoringSession) {
        return convertToDto(service.getTimeSlotByTutoringSession(tutoringSession));
    }

    private TimeSlotDto convertToDto(TimeSlot timeSlot) {
        if (timeSlot == null) throw new IllegalArgumentException("This timeSlot does not exist!");
        return new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(), timeSlot.getDayOfTheWeek(), timeSlot.getId());
    }
}
