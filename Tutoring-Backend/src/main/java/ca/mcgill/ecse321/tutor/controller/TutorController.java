package ca.mcgill.ecse321.tutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.dto.TutorDto;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class TutorController {
    @Autowired
    private TutorService service;
    
    @Autowired
    private TutorRepository repository;

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
    
    //USE CASE 2
    @PutMapping("/tutor/approved/")
    public TutorDto setAccount(@RequestParam("tutorId") String tutorId,
    						   @RequestParam("password") String tutorPassword,
    						   @RequestParam("hourlyRate") String hourlyRate) throws IllegalArgumentException{
    	Tutor tutor = service.approvedTutor(tutorId, tutorPassword, hourlyRate);
    	//TODO: add service call to add timeslots
    	return convertToDto(tutor);
    }
    
    //USE CASE 11
    @GetMapping("/tutors/{courseId}/hourlyRate")
    public String getHourlyRates(@RequestParam("courseId") String courseId) throws IllegalArgumentException{
    	String hourlyRates = service.getHourlyRates(courseId);
    	return hourlyRates;
    }


    private TutorDto convertToDto(Tutor tutor) {
        if (tutor == null) throw new IllegalArgumentException("Tutor must be specified!");
        return new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getEmail(), tutor.getManager(), tutor.getIsApproved());
    }
}
