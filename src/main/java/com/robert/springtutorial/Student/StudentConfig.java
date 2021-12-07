package com.robert.springtutorial.Student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository) {
//        return args -> {
//            Student robert = new Student("Robert", "robert@martz.com",
//                    LocalDate.of(1872, Month.MARCH,17) ,"Literature");
//            Student david = new Student("David", "david@martz.com",
//                    LocalDate.of(2000, Month.JANUARY,23), "Physics");
//
//            repository.saveAll(List.of(robert, david));
//        };
//    }
}
