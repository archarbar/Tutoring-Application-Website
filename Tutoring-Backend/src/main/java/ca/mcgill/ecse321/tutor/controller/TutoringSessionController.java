package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dto.TutoringSessionDto;
import ca.mcgill.ecse321.tutor.model.*;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TutoringSessionController {

	@Autowired
	private TutoringSessionService service;

	@Autowired
	private BookingRepository bookingRepository;

	@PostMapping("/tutoringSession/new")
	public TutoringSessionDto createTutoringSession(
			@RequestParam("bookingId") String bookingId,
			@RequestParam("booking2") Booking booking2,
			@RequestParam("room") Room room, 
			@RequestParam("tutor") Tutor tutor){
		Booking booking = bookingRepository.findBookingById(Integer.parseInt(bookingId));
		TutoringSession tutoringSession = service.createTutoringSession(booking.getSpecificDate(),
				tutor, room, booking, booking.getTimeSlot());
		return convertToDto(tutoringSession);
	}

	@GetMapping("/tutoringSession/{tutoringSessionId}")
	public TutoringSessionDto getTutoringSessionById(@PathVariable int tutoringSessionId) {
		return convertToDto(service.getTutoringSessionById(tutoringSessionId));
	}

	@GetMapping("/tutoringSession/tutor/{tutor}")
	public List<TutoringSessionDto> getTutoringSessionByTutor(@PathVariable Tutor tutor) {
		List<TutoringSessionDto> tutoringSessionDtos = new ArrayList<>();
		for (TutoringSession tutoringSession : service.getTutoringSessionByTutor(tutor)) {
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
