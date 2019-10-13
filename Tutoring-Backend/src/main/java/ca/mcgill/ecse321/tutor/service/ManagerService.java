package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.dao.ManagerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManagerService {
	
	@Autowired
	ManagerRepository managerRepository;

}