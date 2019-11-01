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
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Person;
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
    
    @GetMapping("/notifications/{notificationId}")
    public NotificationDto getNotificationById(@PathVariable("notificationId") String notificationId) {
        return convertToDto(service.getNotification(Integer.parseInt(notificationId)));
    }

    @GetMapping(value= {"/notifications/tutor", "/notifications/tutor/"})
    public List<NotificationDto> getNotificationByTutor(@RequestParam("tutorId") String tutorId) {
    	List<Notification> allNotifications = service.getAllNotifications();
    	List<NotificationDto> notificationDtos = new ArrayList<>();
    	
    	for (Notification notification: allNotifications) {
    		if (notification.getTutor().getId().equals(Integer.parseInt(tutorId))) {
    			notificationDtos.add(convertToDto(notification));
    		}
    	}
        return notificationDtos;
    }
    
    @PostMapping(value= {"/notification/new", "/notification/new/"})
    public NotificationDto createNotification(@RequestParam("tutorId") String tutorId, @RequestParam("bookingId") String bookingId) {
    	Booking b = bookingService.getBookingById(Integer.parseInt(bookingId));
    	Tutor t = tutorService.getTutor(Integer.parseInt(tutorId));
    	return convertToDto(service.createNotification(b, t));
    }

    private NotificationDto convertToDto(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("This notification does not exist!");
        return new NotificationDto(notification.getBooking(), notification.getTutor(), notification.getId());
    }
}
