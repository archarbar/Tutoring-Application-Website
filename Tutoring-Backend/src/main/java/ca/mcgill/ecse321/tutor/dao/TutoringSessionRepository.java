package ca.mcgill.ecse321.tutor.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public interface TutoringSessionRepository extends CrudRepository<TutoringSession, Integer>{

}


