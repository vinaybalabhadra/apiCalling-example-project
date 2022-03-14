package com.ApiCallingExample.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "students_table")   // If this annotation is not given, jpa gives name of class as table name
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // Long is class type here, not a primitive datatype

    @Column(name = "first_Name", nullable = false) // If this annotation is not given, jpa gives name of variable as column name
    private String firstName;

    @Column(name = "last_Name")   // by default, nullable is true
    private String lastName;

    @Column(name = "email")
    private String email;

    public Student(){     // default constructor is mandatory since hibernate uses it while creating the objects dynamically (occurs internally)

    }

    public Student(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
