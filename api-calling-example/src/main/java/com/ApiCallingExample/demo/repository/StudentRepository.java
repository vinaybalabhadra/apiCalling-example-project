package com.ApiCallingExample.demo.repository;

import com.ApiCallingExample.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
