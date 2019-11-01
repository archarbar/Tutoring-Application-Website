package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
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
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;
    
    @Autowired
    private TutorService tutorService;
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/notification/{notificationId}")
    public NotificationDto getNotificationById(@PathVariable int notificationId) {
        return convertToDto(service.getNotification(notificationId));
    }

    @GetMapping("/notifications/tutor/{tutor}")
    public List<NotificationDto> getNotificationByTutor(@RequestParam Tutor tutor) {
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : service.getNotificationsByTutor(tutor)) {
            notificationDtos.add(convertToDto(notification));
        }
        return notificationDtos;
    }
    
    @PostMapping(value= {"/notification/new", "/notification/new/"})
    public NotificationDto createNotification(@RequestParam("tutor") String tutorId, @RequestParam("booking") String bookingId) {
    	Tutor tutor = tutorService.getTutor(Integer.parseInt(tutorId));
    	Booking booking = bookingService.getBookingById(Integer.parseInt(bookingId));
    	return convertToDto(service.createNotification(booking, tutor));
    }


    private NotificationDto convertToDto(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("This notification does not exist!");
        return new NotificationDto(notification.getBooking(), notification.getTutor(), notification.getId());
    }
}
