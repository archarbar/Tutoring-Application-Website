
package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.RatingRepository;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Tutor;

@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepository;

	@Transactional
	public Rating createRating(Integer ratingId, Integer stars, String comment, Tutor tutor, Student student) {
		Rating rating = new Rating();
		rating.setRatingId(ratingId);
		rating.setStars(stars);
		rating.setComment(comment);
		rating.setTutor(tutor);
		rating.setStudent(student);
		return rating;
	}

	@Transactional
	public Rating getRating(Integer ratingId) {
		Rating rating = ratingRepository.findRatingById(ratingId);
		return rating;
	}

}
