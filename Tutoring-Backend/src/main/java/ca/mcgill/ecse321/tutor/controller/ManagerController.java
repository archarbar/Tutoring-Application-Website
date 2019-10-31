package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.ManagerDto;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.service.ManagerService;

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService service;

    @GetMapping(value={"/manager/{managerId}", "/manager"})
    public ManagerDto getManager(@PathVariable int managerId) {
        return convertToDto(service.getManager(managerId));
    }
    
    @GetMapping(value= {"/managers", "/managers/"})
    public List<ManagerDto> getAllManagers(){
    	List<ManagerDto> mDtos = new ArrayList<ManagerDto>();
    	for(Manager manager: service.getAllManagers()) {
    		mDtos.add(convertToDto(manager));
    	}
    	return mDtos;
    }       

    private ManagerDto convertToDto(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("This manager does not exist!");
        return new ManagerDto(manager.getId());
    }

}
