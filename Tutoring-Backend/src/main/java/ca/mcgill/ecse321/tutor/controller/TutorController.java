package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.TutorDto;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class TutorController {
    @Autowired
    private TutorService service;

    @PostMapping("/tutor/new")
    public TutorDto createTutor(@RequestParam("tutorFirstName") String tutorFirstName,
                                @RequestParam("tutorLastName") String tutorLastName,
                                @RequestParam("tutorEmail") String tutorEmail,
                                @RequestParam("tutorPassword") String tutorPassword)
                                throws IllegalArgumentException {
        Tutor newTutor = service.createTutor(tutorFirstName, tutorLastName, tutorEmail, tutorPassword);
        return convertToDto(newTutor);
    }

    @GetMapping("/tutor/{tutorId}")
    public TutorDto getTutorById(@PathVariable String tutorId) {
        return convertToDto(service.getTutor(Integer.parseInt(tutorId)));
    }

    @GetMapping("/login")
    public TutorDto login (@RequestParam("Email") String email, @RequestParam("Password") String password){
        if (service.getTutorByEmail(email).getPassword().contentEquals(password)){
            return convertToDto(service.getTutorByEmail(email));
        }
        else{
            throw new IllegalArgumentException("Wrong Password, try again.");
        }
    }


    private TutorDto convertToDto(Tutor tutor) {
        if (tutor == null) throw new IllegalArgumentException("Tutor must be specified!");
        return new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getEmail(), tutor.getManager(), tutor.getIsApproved());
    }
}
