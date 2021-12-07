package com.robert.springtutorial.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    // dependency injection
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.list();
    }

    @PostMapping  // we map the body of the request to an object named student of class Student
    public void AddStudent(@RequestBody Student student) {
        studentService.insert(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long id) {
        studentService.delete(id);
    }

    @PutMapping(path="{studentId}")
    public void editStudent(@PathVariable("studentId") long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String email) {
        studentService.edit(id, name, email);
    }
}
