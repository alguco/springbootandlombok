package com.springboot.lombok.bootstrap;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.springboot.lombok.model.Customer;
import com.springboot.lombok.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Causes lombok to generate a logger field.
@Slf4j
// Causes lombok to generate a constructor with 1 parameter for each field that requires special handling.
@RequiredArgsConstructor
@Component
public class DefaultCustomerLoader implements CommandLineRunner {

    private final CustomerService customerService;
    private final Faker faker;

    @Override
    public void run(String... args) {
        loadCustomersData();
    }

    private void loadCustomersData() {
        if (customerService.getCustomersCount() == 0) {
            log.info("Saving the default customers into the database.");
            for (int x = 0; x < 5; x++) {
                customerService.save(createNewCustomer());
            }
        } else {
            log.info("Default customers are already present in the database.");
        }
    }

    private Customer createNewCustomer() {
        final int randomNumber = new Random().nextInt(10 - 5 + 1) + 5;
        return Customer.builder()
                .author(faker.customer().author())
                .title(faker.customer().title())
                .publisher(faker.customer().publisher())
                .name(faker.customer().name())
                .phone(faker.number().phoneBetween(50, 100))
                .publishedOn(LocalDateTime.now().minusHours(randomNumber)
                        .minus(Period.ofWeeks(randomNumber)))
                .build();
    }
}
