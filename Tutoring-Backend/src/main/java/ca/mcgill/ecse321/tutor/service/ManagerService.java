package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.model.Manager;

public class ManagerService {

	@Autowired
	ManagerRepository managerRepository;

//	@Transactional
//	public Manager createManager(String firstName, String lastName, String email) {
//		if (firstName == null) {
//			throw new IllegalArgumentException("A first name needs to be specified!");
//		}
//		if (lastName == null) {
//			throw new IllegalArgumentException("A last name needs to be specified!");
//		}
//		if (email == null) {
//			throw new IllegalArgumentException("An email needs to be specified!");
//		}
//		Manager manager = new Manager();
//		manager.setFirstName(firstName);
//		manager.setLastName(lastName);
//		manager.setEmail(email);
//		managerRepository.save(manager);
//		return manager;
//	}

	@Transactional
	public Manager getManager(Integer managerId) {
		Manager manager = managerRepository.findManagerById(managerId);
		return manager;
	}

	@Transactional
	public List<Manager> getAllManagers(){
		return toList(managerRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}