package com.codegym.demo.repository;

import com.codegym.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Iterable<Student> findStudentByFirstName(String firstName);

//    void delete(Long id);
}
