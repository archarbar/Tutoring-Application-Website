package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.NotificationDto;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping("/notification/{notificationId}")
    public NotificationDto getNotificationById(@PathVariable int notificationId) {
        return convertToDto(service.getNotification(notificationId));
    }


    private NotificationDto convertToDto(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("This notification does not exist!");
        return new NotificationDto(notification.getBooking(), notification.getTutor(), notification.getId());
    }
}
