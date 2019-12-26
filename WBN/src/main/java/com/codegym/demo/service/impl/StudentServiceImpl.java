package com.codegym.demo.service.impl;

import com.codegym.demo.model.Student;
import com.codegym.demo.repository.StudentRepository;
import com.codegym.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findStudentByFirstName(String firstName) {
        return studentRepository.findStudentByFirstName(firstName);
    }
}
