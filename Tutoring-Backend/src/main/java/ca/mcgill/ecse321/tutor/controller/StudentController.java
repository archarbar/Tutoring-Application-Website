package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.StudentDto;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/student/{studentId}")
    public StudentDto getStudentById(@PathVariable("studentId") String studentId) {
        return convertToDto(service.getStudentById(Integer.parseInt(studentId)));
    }

    @GetMapping("/student/{email}")
    public StudentDto getStudentByEmail(@PathVariable("email") String email) {
        return convertToDto(service.getStudentByEmail(email));
    }

    @GetMapping("/student/{first}")
    public StudentDto getStudentByFirstName(@PathVariable ("first")String first){
        return convertToDto(service.getStudentByFirstName(first));
    }

    @GetMapping("/student/{last}")
    public StudentDto getStudentByLastName(@PathVariable ("last")String last){
        return convertToDto(service.getStudentByLastName(last));
    }


    private StudentDto convertToDto(Student student) {
        if (student == null) throw new IllegalArgumentException("This student does not exist!");
        return new StudentDto(student.getFirstName(), student.getLastName(), student.getEmail(), student.getId());
    }
}
