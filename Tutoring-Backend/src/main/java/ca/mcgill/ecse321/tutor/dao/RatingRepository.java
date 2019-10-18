package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Rating;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ratings", path = "ratings")
public interface RatingRepository extends CrudRepository<Rating, Integer> {
  
	Rating findRatingById(Integer Id);
	
}
