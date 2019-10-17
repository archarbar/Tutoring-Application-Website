package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.TutorDto;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class TutorController {
    @Autowired
    private TutorService service;

    @PostMapping("/tutor/newTutor")
    public TutorDto createTutor(@RequestBody TutorDto tutor) throws IllegalArgumentException {
        Tutor newTutor = service.createTutor(tutor.getFirstName(), tutor.getLastName(), tutor.getEmail(), tutor.getPassword(),
                tutor.getManager());
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
