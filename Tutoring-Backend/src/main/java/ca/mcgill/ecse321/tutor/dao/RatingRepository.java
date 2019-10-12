package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import ca.mcgill.ecse321.tutor.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
	Rating findRatingById(Integer Id);
	List<Rating> findByTutor(Tutor tutor);
	List<Rating> findByStudent(Student student);
}
