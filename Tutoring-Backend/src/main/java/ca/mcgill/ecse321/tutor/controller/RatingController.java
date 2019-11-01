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

import ca.mcgill.ecse321.tutor.dto.RatingDto;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.RatingService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;

@CrossOrigin(origins = "*")
@RestController
public class RatingController {
    @Autowired
    private RatingService service;
    @Autowired
    private TutoringSessionService tutoringSessionService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/rating/new")
    public RatingDto createRating (@RequestParam("stars") String stars,
                                   @RequestParam("comment") String comment,
                                   @RequestParam("student") String studentId,
                                   @RequestParam("tutoringSessionId") String tutoringSessionId) throws IllegalArgumentException {
    	Student student = studentService.getStudentById(Integer.parseInt(studentId));
    	TutoringSession tutoringSession = tutoringSessionService.getTutoringSessionById(Integer.parseInt(tutoringSessionId));
        Rating newRating = service.createRating(Integer.parseInt(stars), comment, student,
                tutoringSession.getTutor(), tutoringSession);
        return convertToDto(newRating);
    }

    @GetMapping("/rating/{ratingId}")
    public RatingDto getRatingById(@PathVariable String ratingId) {
        return convertToDto(service.getRating(Integer.parseInt(ratingId)));
    }

    @GetMapping("/rating/student/{student}")
    public List<RatingDto> getAllRatingsForStudent(@PathVariable Student student) {
        List<RatingDto> ratings = new ArrayList<>();
        for (Rating rating : service.getAllRatings()) {
            if (rating.getStudent().getId() == student.getId())
                ratings.add(convertToDto(rating));
        }
        return ratings;
    }


    private RatingDto convertToDto(Rating rating) {
        if (rating == null) throw new IllegalArgumentException("Rating must be specified!");
        return new RatingDto(rating.getStars(), rating.getComment(), rating.getStudent(),
                rating.getTutor(), rating.getTutoringSession(), rating.getId());
    }
}
