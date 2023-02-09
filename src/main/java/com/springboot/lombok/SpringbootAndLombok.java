package com.springboot.lombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
// Causes lombok to generate a logger field.
@Slf4j
public class SpringbootAndLombok {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAndLombok.class, args);
        log.info("Springboot and lombok application started successfully.");
    }
}
