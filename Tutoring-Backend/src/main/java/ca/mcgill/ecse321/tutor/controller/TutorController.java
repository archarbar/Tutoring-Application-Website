package ca.mcgill.ecse321.tutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.TutorDto;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class TutorController {
    @Autowired
    private TutorService service;

    @PostMapping("/tutor")
    public TutorDto createTutor(@RequestParam("tutorFirstName") String tutorFirstName,
                                @RequestParam("tutorLastName") String tutorLastName,
                                @RequestParam("tutorEmail") String tutorEmail,
                                @RequestParam("tutorPassword") String tutorPassword)
                                throws IllegalArgumentException {
        Tutor newTutor = service.createTutor(tutorFirstName, tutorLastName, tutorEmail, tutorPassword);
        return convertToDto(newTutor);
    }

    @GetMapping("/tutor/{tutorId}")
    public TutorDto getTutorById(@PathVariable int tutorId) {
        return convertToDto(service.getTutor(tutorId));
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
