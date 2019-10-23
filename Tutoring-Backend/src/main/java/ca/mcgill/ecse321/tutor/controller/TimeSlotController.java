package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {

    @Autowired
    private TimeSlotService service;

    @GetMapping("/timeSlot/{timeSlotId}")
    public TimeSlotDto getTimeSlotById(@PathVariable int timeSlotId) {
        return convertToDto(service.getTimeSlot(timeSlotId));
    }

    private TimeSlotDto convertToDto(TimeSlot timeSlot) {
        if (timeSlot == null) throw new IllegalArgumentException("This timeSlot does not exist!");
        return new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(), timeSlot.getDayOfTheWeek(), timeSlot.getId());
    }
}
