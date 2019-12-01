package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.ManagerDto;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.service.ManagerService;

/**
 * This class provides controller methods for the manager class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService service;

    /**
     * API call to retrieve a manager using its id
     * 
     * @param managerId The ID of the manager
     * @return A manager DTO
     */
    
    @GetMapping(value={"/manager/{managerId}"})
    public ManagerDto getManagerById(@PathVariable String managerId) {
        return convertToDto(service.getManager(Integer.parseInt(managerId)));
    }
    
    /**
     * API call to retrieve all managers
     * 
     * @return A manager DTO
     */
    
    @GetMapping(value= {"/managers", "/managers/"})
    public List<ManagerDto> getAllManagers(){
    	List<ManagerDto> mDtos = new ArrayList<ManagerDto>();
    	for(Manager manager: service.getAllManagers()) {
    		mDtos.add(convertToDto(manager));
    	}
    	return mDtos;
    }
    
    /**
     * Method to convert a manager object into a manager DTO
     * 
     * @param manager A manager object
     * @return A manager DTO instance
     */

    private ManagerDto convertToDto(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("This manager does not exist!");
        return new ManagerDto(manager.getId());
    }

}
