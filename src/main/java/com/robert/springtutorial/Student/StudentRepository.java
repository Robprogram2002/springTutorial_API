package com.robert.springtutorial.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //@Query("Select s from Student s where s.email = ?1")
    <S extends Student> Optional<S> findStudentByEmail(String email);

    //@Query("SELECT s FROM Student s WHERE s.email = ?1")
    //Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.name = ?1 AND s.bornDate <= ?2")
    List<Student> selectStudentWhereNameAndDateGreaterOrEqual(
            String name, LocalDate bornDate);

    @Query(
            value = "SELECT * FROM student WHERE name = :name AND born_date <= :bornDate",
            nativeQuery = true)
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("name") String name,
            @Param("bornDate") LocalDate bornDate);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int  deleteStudentById(Long id);
}
