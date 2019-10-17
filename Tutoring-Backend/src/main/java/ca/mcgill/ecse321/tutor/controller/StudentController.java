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

    @GetMapping("/student/{student}")
    public StudentDto getStudentById(@PathVariable Student student) {
        return convertToDto(service.getStudentById(student.getId()));
    }

    @GetMapping("/student/{student}")
    public StudentDto getStudentByEmail(@PathVariable Student student) {
        return convertToDto(service.getStudentByEmail(student.getEmail()));
    }

    @GetMapping("/student/{student}")
    public StudentDto getStudentByName(@PathVariable Student student) {
        return convertToDto(service.getStudentByName(student.getFirstName(), student.getLastName()));
    }


    private StudentDto convertToDto(Student student) {
        if (student == null) throw new IllegalArgumentException("This student does not exist!");
        return new StudentDto(student.getFirstName(), student.getLastName(), student.getEmail(), student.getId());
    }
}
