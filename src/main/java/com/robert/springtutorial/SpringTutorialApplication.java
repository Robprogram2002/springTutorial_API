package com.robert.springtutorial;

import com.robert.springtutorial.Authentication.AuthUser;
import com.robert.springtutorial.Authentication.Role;
import com.robert.springtutorial.Service.AuthUserService;
import com.robert.springtutorial.Student.*;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTutorialApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentCardRepository studentCardRepository) {
        return args -> {
            Student robert = new Student("Robert", "robert@martz.com",
                    LocalDate.of(1872, Month.MARCH,17) ,"Literature");
            Student david = new Student("David", "david@martz.com",
                    LocalDate.of(2000, Month.JANUARY,23), "Physics");

            studentRepository.saveAll(List.of(robert, david));

            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(firstName + " " + lastName,email,
                    LocalDate.of(1982, Month.MARCH,17),"Physics");

            student.addBook(
                    new Book("Clean Code", LocalDateTime.now().minusDays(4)));

            student.addBook(
                    new Book("Think and Grow Rich", LocalDateTime.now()));

            student.addBook(
                    new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

            StudentCard studentCard = new StudentCard(student, "123456789", 175,4,"Math");

            student.setStudentCard(studentCard);

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 1L),
                    student,
                    new Course("Computer Science", "IT"),
                    LocalDateTime.now()
            ));

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 2L),
                    student,
                    new Course("Amigoscode Spring Data JPA", "IT"),
                    LocalDateTime.now().minusDays(18)
            ));

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 2L),
                    student,
                    new Course("Amigoscode Spring Data JPA", "IT"),
                    LocalDateTime.now().minusDays(18)
            ));



            studentRepository.save(student);

            studentRepository.findById(1L)
                    .ifPresent(s -> {
                        System.out.println("fetch book lazy...");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> {
                            System.out.println(
                                    s.getName() + " borrowed " + book.getBookName());
                        });
                    });

        };
    }

    @Bean
    CommandLineRunner run(AuthUserService authUserService) {
        return args -> {
            authUserService.saveRole(new Role(null,"ROLE_USER"));
            authUserService.saveRole(new Role(null,"ROLE_ADMIN"));
            authUserService.saveRole(new Role(null,"ROLE_MANAGER"));
            authUserService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

            authUserService.saveAuthUser(new AuthUser(null, "robertmartz",
                    "passwordSegura", "Robert", new ArrayList<>()));
            authUserService.saveAuthUser(new AuthUser(null, "danielRoss",
                    "passwordSegura", "Daniel Roswell", new ArrayList<>()));
            authUserService.saveAuthUser(new AuthUser(null, "antoinegerard",
                    "passwordSegura", "Antoine Gerard", new ArrayList<>()));
            authUserService.saveAuthUser(new AuthUser(null, "lopezgarcia",
                    "passwordSegura", "Lopez García", new ArrayList<>()));

            authUserService.addRoleToUser("robertmartz", "ROLE_USER");
            authUserService.addRoleToUser("danielRoss", "ROLE_MANAGER");
            authUserService.addRoleToUser("antoinegerard", "ROLE_ADMIN");
            authUserService.addRoleToUser("antoinegerard", "ROLE_SUPER_ADMIN");
            authUserService.addRoleToUser("lopezgarcia", "ROLE_USER");
            authUserService.addRoleToUser("lopezgarcia", "ROLE_MANAGER");
        };
    }

}
