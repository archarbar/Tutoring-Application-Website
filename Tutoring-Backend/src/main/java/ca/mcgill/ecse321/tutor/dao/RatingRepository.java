package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
  
	Rating findRatingById(Integer Id);
	
}
