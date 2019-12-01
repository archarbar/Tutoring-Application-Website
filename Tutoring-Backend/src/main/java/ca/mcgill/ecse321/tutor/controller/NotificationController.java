package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.NotificationDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {
	
	@Autowired
	private BookingService bookingService;

    @Autowired
    private NotificationService service;

    @Autowired 
    private TutorService tutorService;    
    
    @GetMapping("/notification/{notificationId}")
    public NotificationDto getNotificationById(@PathVariable("notificationId") String notificationId) {
        return convertToDto(service.getNotification(Integer.parseInt(notificationId)));
    }

    @GetMapping(value= {"/notification/tutor/{tutorId}"})
    public List<NotificationDto> getNotificationByTutor(@PathVariable("tutorId") String tutorId) {
    	List<NotificationDto> notificationDtos = new ArrayList<>();  	
    	for (Notification notification: service.getAllNotifications()) {
    		if (notification.getTutor().getId().equals(Integer.parseInt(tutorId))) {
    			notificationDtos.add(convertToDto(notification));
    		}
    	}
        return notificationDtos;
    }
    
    @PostMapping(value= {"/notification/new", "/notification/new/"})
    public NotificationDto createNotification(@RequestParam("tutorId") String tutorId, @RequestParam("bookingId") String bookingId) {    	
    	return convertToDto(service.createNotification(bookingService.getBookingById(Integer.parseInt(bookingId)), tutorService.getTutorById(Integer.parseInt(tutorId))));
    }
    
    @PostMapping("/notification/new")
    public void createNotificationFromBooking(@PathVariable("tutorId") String tutorId, @RequestBody String bookingId) {
    	// Upon receiving a new booking, the tutor point of view creates a new notification.
    	Booking booking = bookingService.getBookingById(Integer.parseInt(bookingId));
    	Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
    	service.createNotification(booking, tutor);    	
    }

    private NotificationDto convertToDto(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("This notification does not exist!");
        return new NotificationDto(notification.getBooking(), notification.getTutor(), notification.getId());
    }
}
