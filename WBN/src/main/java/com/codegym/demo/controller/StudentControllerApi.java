package com.codegym.demo.controller;

import com.codegym.demo.message.SearchStudentByFirstName;
import com.codegym.demo.model.Student;
import com.codegym.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class StudentControllerApi {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<?> getListStudent(){
    List<Student> students = (List<Student>) studentService.findAll();
        if (students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        Optional<Student> student = studentService.findById(id);
        if(!student.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @PostMapping("/student")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    @PutMapping("student/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, @PathVariable Long id){
        Optional<Student> student1 = studentService.findById(id);
        if(!student1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        student1.get().setFirstName(student.getFirstName());
        student1.get().setLastName(student.getLastName());
        student1.get().setEmail(student.getEmail());
        studentService.save(student1.get());

        return new ResponseEntity<>(student1, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        Optional<Student> student = studentService.findById(id);
        if(!student.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("student/search-by-name")
    public ResponseEntity<?> searchStudentByFirstName(@RequestBody SearchStudentByFirstName studentForm){
        if (studentForm.getFirstName() == "" || studentForm.getFirstName() == null){
            List<Student> students = (List<Student>) studentService.findAll();
            if (students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        List<Student> students = (List<Student>) studentService.findStudentByFirstName(studentForm.getFirstName());
        if (students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }
}
