package com.springboot.lombok.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
// Lombok annotations
// Causes lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go.
@Data
// Causes lombok to implement the Builder design pattern for the Pojo class.
// Usage can be seen in DefaultBookLoader.java -> createNewBook() method.
@Builder
// Causes lombok to generate a constructor with no parameters.
@NoArgsConstructor
// // Causes lombok to generate a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
// Spring framework annotation
@Component
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "number", nullable = false)
    String number;
    @Column(name = "amount")
    int amount;
   
   
   
    
    @Column(name = "date")
    Date date;
    @Column(name = "designation", nullable = false)
    String designation;
    @Column(name = "hour", nullable = false)
    LocalDateTime hour;
}
