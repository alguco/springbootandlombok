package com.springboot.lombok.bootstrap;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.springboot.lombok.model.Transaction;
import com.springboot.lombok.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Causes lombok to generate a logger field.
@Slf4j
// Causes lombok to generate a constructor with 1 parameter for each field that requires special handling.
@RequiredArgsConstructor
@Component
public class DefaultTransactionLoader implements CommandLineRunner {

    private final TransactionService transactionService;
    private final Faker faker;

    @Override
    public void run(String... args) {
        loadTransactionsData();
    }

    private void loadTransactionsData() {
        if (transactionService.getTransactionsCount() == 0) {
            log.info("Saving the default transactions into the database.");
            for (int x = 0; x < 5; x++) {
                transactionService.save(createNewTransaction());
            }
        } else {
            log.info("Default transactions are already present in the database.");
        }
    }

    private Transaction createNewTransaction() {
        final int randomNumber = new Random().nextInt(10 - 5 + 1) + 5;
        return Transaction.builder()
        		.id(faker.number().numberBetween(50,10000))
                .number(faker.code().isbn10())
               
                .amount(faker.number().numberBetween(50, 20000))
              
               
                .designation(faker.address().country())
                .hour(LocalDateTime.now().minusHours(randomNumber)
                        .minus(Period.ofWeeks(randomNumber)))
                .build();
    }
}
