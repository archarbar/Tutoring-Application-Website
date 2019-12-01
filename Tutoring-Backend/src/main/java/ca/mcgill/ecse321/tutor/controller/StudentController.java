package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.StudentDto;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides controller methods for the student class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    /**
     * API call to retrieve a student using its id
     * 
     * @param studentId The ID of the student
     * @return A student DTO
     */
    
    @GetMapping("/student/{studentId}")
    public StudentDto getStudentById(@PathVariable("studentId") String studentId) {
        return convertToDto(service.getStudentById(Integer.parseInt(studentId)));
    }
    
    /**
     * API call to retrieve a student using its email
     * 
     * @param email The email of the student
     * @return A student DTO
     */

    @GetMapping("/student/{email}")
    public StudentDto getStudentByEmail(@PathVariable("email") String email) {
        return convertToDto(service.getStudentByEmail(email));
    }

    /**
     * API call to retrieve a student using its first name
     * 
     * @param first The first name of the student
     * @return A student DTO
     */
    
    @GetMapping("/student/{first}")
    public StudentDto getStudentByFirstName(@PathVariable ("first")String first){
        return convertToDto(service.getStudentByFirstName(first));
    }
    
    /**
     * API call to retrieve a student using its last name
     * 
     * @param last The last name of the student
     * @return A student DTO
     */

    @GetMapping("/student/{last}")
    public StudentDto getStudentByLastName(@PathVariable ("last")String last){
        return convertToDto(service.getStudentByLastName(last));
    }
    
    /**
     * Method to convert a student object into a student DTO
     * 
     * @param student A student object
     * @return A student DTO
     */

    private StudentDto convertToDto(Student student) {
        if (student == null) throw new IllegalArgumentException("This student does not exist!");
        return new StudentDto(student.getFirstName(), student.getLastName(), student.getEmail(), student.getId());
    }
}
