package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.RatingDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;
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
    @Autowired
    private BookingService bookingService;

    @PostMapping(value= {"/rating/new", "/rating/new/"})
    public RatingDto createRating (@RequestParam("stars") String stars,
                                   @RequestParam("comment") String comment,
                                   @RequestParam("studentId") String studentId,
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
    
    @GetMapping(value= {"/ratings", "/ratings/"})
    public List<RatingDto> getAllRatings() {
    	List<RatingDto> ratings = new ArrayList<RatingDto>();
        for (Rating rating: service.getAllRatings()) {
        	ratings.add(convertToDto(rating));
        }
        return ratings;
    }

    @GetMapping("/rating/student/{studentId}")
    public List<RatingDto> getAllRatingsForStudent(@PathVariable("studentId") String studentId) {
        List<RatingDto> ratings = new ArrayList<>();
        Student student = studentService.getStudentById(Integer.parseInt(studentId));
        for (Rating rating : service.getAllRatings()) {
            if (rating.getStudent().getId() == student.getId())
                ratings.add(convertToDto(rating));
        }
        return ratings;
    }

    @GetMapping("/rating/student/booking/tutor")
    public List<RatingDto> getStudentRatingByBooking(@RequestParam("bookingId") String bookingId) {
    	Booking booking = bookingService.getBookingById(Integer.parseInt(bookingId));
    	List<RatingDto> ratings = new ArrayList<RatingDto>();
    	for(Student student: booking.getStudent()) {
    		for (Rating rating: student.getRating()) {
    			ratings.add(convertToDto(rating));
    		}
    	}
    	return ratings;
    }

    private RatingDto convertToDto(Rating rating) {
        if (rating == null) throw new IllegalArgumentException("Rating must be specified!");
        return new RatingDto(rating.getStars(), rating.getComment(), rating.getStudent(),
                rating.getTutor(), rating.getTutoringSession(), rating.getId());
    }
}
