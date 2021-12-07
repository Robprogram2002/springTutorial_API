package com.robert.springtutorial.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckIfExistsByEmail() {
        // given
        String email = "something@something.com";
        Student newStudent = new Student(22,"Jonh", email,
                LocalDate.of(2002,8,17),"Math");
        underTest.save(newStudent);

        //when
        boolean expect = underTest.checkIfExistsByEmail(email);

        //then
        assertThat(expect).isTrue();
    }

}