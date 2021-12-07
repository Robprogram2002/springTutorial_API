package com.robert.springtutorial.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


// we convert the class in a bean so spring register it
// additionally we tell to spring that this class represent a service
// (is the same but is better for semantic)
@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
    }

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public void insert(Student student) {
        Optional<Student> existStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (existStudent.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void delete(long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("Student not found with id :" + studentId);
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    public boolean checkIfExistsByEmail(String email) {
        var student = studentRepository.findStudentByEmail(email);
        return student.isPresent();
    }

    @Transactional
    public void edit(long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(("student not found with id: " + studentId)));

        if (name != null && !"".equals(name) && !student.getName().equals(name) ) {
            student.setName(name);
        }

        if (email != null && !"".equals(email) && !student.getEmail().equals(email) ) {
            Optional<Student> existStudent = studentRepository.findStudentByEmail(email);
            if (existStudent.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
