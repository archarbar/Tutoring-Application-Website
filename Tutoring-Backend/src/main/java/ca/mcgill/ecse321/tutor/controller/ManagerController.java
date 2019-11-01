package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.ManagerDto;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService service;

    @GetMapping(value={"/manager/{managerId}"})
    public ManagerDto getManager(@PathVariable String managerId) {
        return convertToDto(service.getManager(Integer.parseInt(managerId)));
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
