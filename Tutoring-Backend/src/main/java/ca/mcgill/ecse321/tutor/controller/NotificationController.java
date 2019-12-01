package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.NotificationDto;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.service.TutorService;

/**
 * This class provides controller methods for the notification class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {
	
	@Autowired
	private BookingService bookingService;

    @Autowired
    private NotificationService service;

    @Autowired 
    private TutorService tutorService;
    
    /**
     * API call to retrieve a notification using its id
     * 
     * @param notificationId The ID of the notification
     * @return A notification DTO
     */
    
    @GetMapping("/notification/{notificationId}")
    public NotificationDto getNotificationById(@PathVariable("notificationId") String notificationId) {
        return convertToDto(service.getNotification(Integer.parseInt(notificationId)));
    }
    
    /**
     * API call to retrieve all notifications from a specific tutor
     * 
     * @param tutorId The ID of the tutor
     * @return A notification DTO
     */

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
    
    /**
     * API call to post a new notification
     * 
     * @param tutorId The ID of the tutor
     * @param bookingId The ID of the booking
     * @return A notification DTO
     */
    
    @PostMapping(value= {"/notification/new", "/notification/new/"})
    public NotificationDto createNotification(@RequestParam("tutorId") String tutorId, @RequestParam("bookingId") String bookingId) {    	
    	return convertToDto(service.createNotification(bookingService.getBookingById(Integer.parseInt(bookingId)), tutorService.getTutorById(Integer.parseInt(tutorId))));
    }
    
    /**
     * Method to convert a notification object into a notification DTO
     * 
     * @param notification A notification object
     * @return A notification DTO
     */

    private NotificationDto convertToDto(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("This notification does not exist!");
        return new NotificationDto(notification.getBooking(), notification.getTutor(), notification.getId());
    }
}
