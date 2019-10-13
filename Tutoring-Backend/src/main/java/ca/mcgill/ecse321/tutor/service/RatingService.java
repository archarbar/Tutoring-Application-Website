
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
	public Rating createTutorRating(Integer ratingId, Integer stars, String comment, Tutor tutor) {
		Rating rating = new Rating();
		rating.setRatingId(ratingId);
		rating.setStars(stars);
		rating.setComment(comment);
		rating.setTutor(tutor);
		return rating;
	}
	
	@Transactional
	public Rating createStudentRating(Integer ratingId, Integer stars, String comment, Student student) {
		Rating rating = new Rating();
		rating.setRatingId(ratingId);
		rating.setStars(stars);
		rating.setComment(comment);
		rating.setStudent(student);
		return rating;
	}
	
	@Transactional
	public Rating getrating(Integer ratingId) {
		Rating rating = ratingRepository.findRatingById(ratingId);
		return rating;
	}
	
//	@Transactional
//	public List<Rating> getAllTutorRatings(Tutor tutor) {
//		return toList(ratingRepository.findByTutor(tutor));
//	}
	
//	//	@Transactional
//	public List<Rating> getAllStudentRatings(Student student) {
//	return toList(ratingRepository.findByStudent(student));
//}
	
//	private <T> List<T> toList(Iterable<T> iterable){
//		List<T> resultList = new ArrayList<T>();
//		for (T t : iterable) {
//			resultList.add(t);
//		}
//		return resultList;
//	}
}
