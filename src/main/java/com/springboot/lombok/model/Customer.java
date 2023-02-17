package com.springboot.lombok.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "custumer")
// Lombok annotations
// Causes lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go.

// Causes lombok to implement the Builder design pattern for the Pojo class.
// Usage can be seen in DefaultBookLoader.java -> createNewBook() method.

// Causes lombok to generate a constructor with no parameters.

// // Causes lombok to generate a constructor with 1 parameter for each field in your class.

// Spring framework annotation
@Component

public class Customer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "CNI", nullable = false)
    String CNI;
    @Column(name = "name")
    String name;
    @Column(name = "phone")
    int phone;
    @Column(name = "email")
    String email;

}


