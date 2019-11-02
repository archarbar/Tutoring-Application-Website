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

import ca.mcgill.ecse321.tutor.dto.TutoringSessionDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.RoomService;
import ca.mcgill.ecse321.tutor.service.TutorService;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;

@CrossOrigin(origins = "*")
@RestController
public class TutoringSessionController {

    @Autowired
    private TutoringSessionService service;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private TutorService tutorService;
    

    @Autowired
    private RoomService roomService;

    @PostMapping("/tutoringsession/new")
    public TutoringSessionDto createTutoringSession(
    												@RequestParam("bookingId") String bookingId,
    												@RequestParam("roomId") String roomId, 
    												@RequestParam("tutorId") String tutorId){
        Booking booking = bookingService.getBookingById(Integer.parseInt(bookingId));
        Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
        Room room = roomService.getRoom(Integer.parseInt(roomId));       
    	TutoringSession tutoringSession = service.createTutoringSession(booking.getSpecificDate(),
                tutor, room, booking, booking.getTimeSlot());
        return convertToDto(tutoringSession);
    }

    @GetMapping("/tutoringsessions/{tutoringSessionId}")
    public TutoringSessionDto getTutoringSessionById(@PathVariable("tutoringSessionId") String tutoringSessionId) {
        return convertToDto(service.getTutoringSessionById(Integer.parseInt(tutoringSessionId)));
    }

    @GetMapping("/tutoringsessions/tutor/{tutor}")
    public List<TutoringSessionDto> getTutoringSessionByTutor(@PathVariable String tutorId) {
    	Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
        List<TutoringSessionDto> tutoringSessionDtos = new ArrayList<>();
        for (TutoringSession tutoringSession : service.getTutoringSessionByTutor(tutor)) {
            tutoringSessionDtos.add(convertToDto(tutoringSession));
        }
        return tutoringSessionDtos;
    }
    
    @GetMapping(value= {"/tutoringsessions", "/tutoringsessions/"})
    public List<TutoringSessionDto> getAllTutoringSessions(){
    	List<TutoringSessionDto> tutoringSessionDtos = new ArrayList<TutoringSessionDto>();
    	for (TutoringSession tutoringSession: service.getAllTutoringSessions()) {
    		tutoringSessionDtos.add(convertToDto(tutoringSession));
    	}    	
    	return tutoringSessionDtos;
    }

    private TutoringSessionDto convertToDto(TutoringSession tutoringSession) {
        if (tutoringSession == null) throw new IllegalArgumentException("This tutoringSession does not exist!");
        return new TutoringSessionDto(tutoringSession.getSessionDate(), tutoringSession.getTutor(), tutoringSession.getRoom(),
                tutoringSession.getTimeSlot(), tutoringSession.getBooking(), tutoringSession.getId());
    }
}
