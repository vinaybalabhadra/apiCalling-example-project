package com.ApiCallingExample.demo.controller;

import com.ApiCallingExample.demo.model.Student;
import com.ApiCallingExample.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // build create student REST API
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student)
    {
        return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    //build get All Students REST API
    @GetMapping
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    // build get Student by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId)
    {
        return new ResponseEntity<Student>(studentService.getStudentById(studentId),HttpStatus.OK);
    }

    /*  -- can send only student details only. But sending HttpStatus is good along with the object. so sent as shown above.
    the below Get Student by ID REST API also works fine.

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable("id") long studentId)
    {
        return studentService.getStudentById(studentId);
    }
    */

    // build update student REST API
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") long studentId)
    {
        return new ResponseEntity<Student>(studentService.updateStudent(student,studentId),HttpStatus.OK);
    }

    // build delete student REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long studentId)
    {
        // delete the student
        studentService.deleteStudent(studentId);

        //send the response to client
        return new ResponseEntity<String>("Student with given Id is deleted successfully",HttpStatus.OK);
    }

}
