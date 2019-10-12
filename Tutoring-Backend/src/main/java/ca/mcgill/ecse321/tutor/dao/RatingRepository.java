package main.java.ca.mcgill.ecse321.tutor.dao;

import java.util.List;
import main.java.ca.mcgill.ecse321.tutor.model.Rating;
import main.java.ca.mcgill.ecse321.tutor.model.Student;
import main.java.ca.mcgill.ecse321.tutor.model.Tutor;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
	Rating findRatingById(Integer Id);
	List<Rating> findByTutor(Tutor tutor);
	List<Rating> findByStudent(Student student);
}
