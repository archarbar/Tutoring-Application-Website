package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.TutoringSessionDto;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class TutoringSessionController {

    @Autowired
    private TutoringSessionService service;

    @PostMapping("/tutoringSession/new")
    public TutoringSessionDto createTutoringSession (@RequestParam TutoringSession newTutoringSession){
        TutoringSession tutoringSession = service.createTutoringSession(newTutoringSession.getSessionDate(), newTutoringSession.getTutor(),
                newTutoringSession.getRoom(), newTutoringSession.getBooking(), newTutoringSession.getTimeSlot());
        return convertToDto(tutoringSession);
    }

    @GetMapping("/tutoringSession/{tutoringSessionId}")
    public TutoringSessionDto getTutoringSessionById(@PathVariable int tutoringSessionId) {
        return convertToDto(service.getTutoringSessionById(tutoringSessionId));
    }

    private TutoringSessionDto convertToDto(TutoringSession tutoringSession) {
        if (tutoringSession == null) throw new IllegalArgumentException("This tutoringSession does not exist!");
        return new TutoringSessionDto(tutoringSession.getSessionDate(), tutoringSession.getTutor(), tutoringSession.getRoom(),
                tutoringSession.getTimeSlot(), tutoringSession.getBooking(), tutoringSession.getId());
    }
}
