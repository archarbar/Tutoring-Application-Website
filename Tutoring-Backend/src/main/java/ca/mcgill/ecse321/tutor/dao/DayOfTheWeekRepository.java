package main.java.ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import main.java.ca.mcgill.ecse321.tutor.model.DayOfTheWeek;

public interface DayOfTheWeekRepository extends CrudRepository<DayOfTheWeek, Integer>{
	
}
