package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.model.Manager;

@Service
public class ManagerService {

	@Autowired
	ManagerRepository managerRepository;

	@Transactional
	public Manager createManager() {
		Manager manager = new Manager();
		managerRepository.save(manager);
		return manager;
	}

	@Transactional
	public Manager getManager(Integer managerId) {
		if (managerId == null) {
			throw new IllegalArgumentException("A manager ID needs to be specified!");
		}
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