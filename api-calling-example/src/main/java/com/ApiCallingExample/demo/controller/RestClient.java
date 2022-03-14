package com.ApiCallingExample.demo.controller;

import com.ApiCallingExample.demo.model.Student;
import org.apache.catalina.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    // REST end-points
    private static final String GET_ALL_STUDENTS_API = "http://localhost:8080/api/student";  // URL should be same as in controller class
    private static final String GET_STUDENT_BY_ID_API = "http://localhost:8080/api/student/{id}";
    private static final String CREATE_STUDENT_API = "http://localhost:8080/api/student";
    private static final String UPDATE_STUDENT_API = "http://localhost:8080/api/student/{id}";
    private static final String DELETE_STUDENT_API = "http://localhost:8080/api/student/{id}";


    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        callGetAllStudentsAPI();
//        callGetStudentByIdAPI();
//        callSaveStudentAPI();
//        callUpdateStudentAPI();
//        callDeleteStudentAPI();
    }

    private static void callGetAllStudentsAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity= new HttpEntity<>("parameters",headers);
        ResponseEntity<String> response =
                restTemplate.exchange(GET_ALL_STUDENTS_API, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode()==HttpStatus.OK)
            System.out.println(response.getBody());
        else
            System.out.println("Bad response received");
    }

    private static void callGetStudentByIdAPI() {
        Map<String, Integer> param = new HashMap<>();  // Map for passing Id as input
        param.put("id", 1);  // id is path variable and 1 is id for which we want details to fetch

        Student student = restTemplate.getForObject(GET_STUDENT_BY_ID_API, Student.class, param);
        System.out.println(student.getFirstName() + ' ' + student.getLastName());
        System.out.println(student.getEmail());
    }

    private static void callSaveStudentAPI(){
        Student student = new Student("Satya","Veni","satya@gmail.com");
        ResponseEntity<Student> response = restTemplate.postForEntity(CREATE_STUDENT_API, student, Student.class);
        System.out.println(response.getBody());
    }

    private static void callUpdateStudentAPI(){
        Map<String,Integer> param= new HashMap<>();
        param.put("id",2);
        Student updatedStudent = new Student("Ranga","Raju","Ranga@gmail.com");
        restTemplate.put(UPDATE_STUDENT_API, updatedStudent, param);
        System.out.println("Details of Student updated successfully...");
    }

    private static void callDeleteStudentAPI(){
        Map<String,Integer> param = new HashMap<>();
        param.put("id",5);

        restTemplate.delete(DELETE_STUDENT_API,param);
        System.out.println("Deleted");
    }
}
