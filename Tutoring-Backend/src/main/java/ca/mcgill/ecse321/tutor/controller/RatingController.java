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

/**
 * This class provides controller methods for the rating class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class RatingController {
	@Autowired
	private RatingService service;
	@Autowired
	private TutoringSessionService tutoringSessionService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * API call to post a new rating
	 * 
	 * @param stars The number of stars on 5
	 * @param comment A comment
	 * @param studentId The ID of the student
	 * @param tutoringSessionId The ID of the tutoring session
	 * @return A rating DTO
	 * @throws IllegalArgumentException
	 */

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
	
	/**
	 * API call to retrieve a rating using its id
	 * 
	 * @param ratingId The ID of the rating
	 * @return A rating DTO
	 */

	@GetMapping("/rating/{ratingId}")
	public RatingDto getRatingById(@PathVariable String ratingId) {
		return convertToDto(service.getRating(Integer.parseInt(ratingId)));
	}
	
	/**
	 * API call to retrieve all ratings
	 * 
	 * @return A rating DTO
	 */

	@GetMapping(value= {"/ratings", "/ratings/"})
	public List<RatingDto> getAllRatings() {
		List<RatingDto> ratings = new ArrayList<RatingDto>();
		for (Rating rating: service.getAllRatings()) {
			ratings.add(convertToDto(rating));
		}
		return ratings;
	}
	
	/**
	 * API call to retrieve all ratings from a specific student
	 * 
	 * @param studentId The ID of the student
	 * @return A student DTO
	 */

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
	
	/**
	 * Method to convert a rating object into a rating DTO
	 * 
	 * @param rating A rating object
	 * @return A rating DTO
	 */

	private RatingDto convertToDto(Rating rating) {
		if (rating == null) throw new IllegalArgumentException("Rating must be specified!");
		return new RatingDto(rating.getStars(), rating.getComment(), rating.getStudent(),
				rating.getTutor(), rating.getTutoringSession(), rating.getId());
	}
}
