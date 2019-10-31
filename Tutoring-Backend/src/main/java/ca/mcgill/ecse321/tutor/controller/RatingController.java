package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.RatingDto;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RatingController {
    @Autowired
    private RatingService service;

    @PostMapping("/rating/newRating")
    public RatingDto createRating (@RequestParam Integer stars,
                                   @RequestParam String comment,
                                   @RequestParam Student student,
                                   @RequestParam TutoringSession tutoringSession) throws IllegalArgumentException {
        Rating newRating = service.createRating(stars, comment, student,
                tutoringSession.getTutor(), tutoringSession);
        return convertToDto(newRating);
    }

    @GetMapping("/rating/{ratingId}")
    public RatingDto getRatingById(@PathVariable int ratingId) {
        return convertToDto(service.getRating(ratingId));
    }

    @GetMapping("/rating/student{student}Ratings")
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
