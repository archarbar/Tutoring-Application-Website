package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.NotificationDto;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;

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


    private NotificationDto convertToDto(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("This notification does not exist!");
        return new NotificationDto(notification.getBooking(), notification.getTutor(), notification.getId());
    }
}
