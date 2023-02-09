package com.springboot.lombok.model;

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
@Table(name = "account")
// Lombok annotations
// Causes lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go.
@Data
// Causes lombok to implement the Builder design pattern for the Pojo class.
// Usage can be seen in DefaultAccountLoader.java -> createNewAccount() method.
@Builder
// Causes lombok to generate a constructor with no parameters.
@NoArgsConstructor
// // Causes lombok to generate a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
// Spring framework annotation
@Component
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "account_number", nullable = false)
    String accountNumber;
    @Column(name = "client_id", nullable = false)
    String clientId;
    @Column(name = "account_type")
    String accountType;
    @Column(name = "residence")
    String residence;
    @Column(name = "designation")
    String designation;
    @Column(name = "amount")
    int amount;
    @Column(name = "created_on")
    LocalDateTime createdOn;
    @Column(name = "updated_on")
    LocalDateTime updatedOn;
}
