package com.robert.springtutorial.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCardRepository extends CrudRepository<StudentCard, Long> {

}
