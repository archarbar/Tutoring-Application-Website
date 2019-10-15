package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.RatingRepository;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

@Service
public class RatingService {
  @Autowired
  RatingRepository ratingRepository;

  @Transactional
  public Rating createRating(Integer stars, String comment, Student student, Tutor tutor, TutoringSession tutoringSession) {
    if (stars == null) {
      throw new IllegalArgumentException("A star rating needs to be specified!");
    }
    if (comment == null) {
      throw new IllegalArgumentException("A comment needs to be specified!");
    }
    Rating rating = new Rating();
    rating.setStars(stars);
    rating.setComment(comment);
    rating.setStudent(student);
    rating.setTutor(tutor);
    rating.setTutoringSession(tutoringSession);
    ratingRepository.save(rating);
    return rating;
  }

  @Transactional
  public Rating getRating(Integer ratingId) {
    Rating rating = ratingRepository.findRatingById(ratingId);
    return rating;
  }

  @Transactional
  public List<Rating> getAllRatings(){
    return toList(ratingRepository.findAll());
  }

  private <T> List<T> toList(Iterable<T> iterable){
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }

}
