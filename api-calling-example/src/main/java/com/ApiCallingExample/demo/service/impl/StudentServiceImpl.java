package com.ApiCallingExample.demo.service.impl;

import com.ApiCallingExample.demo.exception.ResourceNotFoundException;
import com.ApiCallingExample.demo.model.Student;
import com.ApiCallingExample.demo.repository.StudentRepository;
import com.ApiCallingExample.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public Student getStudentById(long id) {
        /*  -- we can use this format or can use the below lambda expression format
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
            return student.get();
        else
            throw new ResourceNotFoundException("Student","Id",id);
        */
        return studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student","Id",id));
    }


    @Override
    public Student updateStudent(Student student, long id) {
        //getting the student details with given id
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student","Id",id));

        //updating the student details
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // saving updated student in database
        studentRepository.save(existingStudent);
        return existingStudent;
    }

    @Override
    public void deleteStudent(long id) {
        // check whether student with given id is present or not
        studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student","Id",id));

        // delete the student if student is present with given id
        studentRepository.deleteById(id);
    }
}
