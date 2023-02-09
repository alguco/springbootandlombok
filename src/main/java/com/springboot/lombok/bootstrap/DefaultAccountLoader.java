package com.springboot.lombok.bootstrap;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.springboot.lombok.model.Account;
import com.springboot.lombok.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Causes lombok to generate a logger field.
@Slf4j
// Causes lombok to generate a constructor with 1 parameter for each field that requires special handling.
@RequiredArgsConstructor
@Component
public class DefaultAccountLoader implements CommandLineRunner {

    private final AccountService accountService;
    private final Faker faker;

    @Override
    public void run(String... args) {
        loadAccountsData();
    }

    private void loadAccountsData() {
        if (accountService.getAccountsCount() == 0) {
            log.info("Saving the default accounts into the database.");
            for (int x = 0; x < 5; x++) {
                accountService.save(createNewAccount());
            }
        } else {
            log.info("Default accounts are already present in the database.");
        }
    }

    private Account createNewAccount() {
        final int randomNumber = new Random().nextInt(10 - 5 + 1) + 5;
        return Account.builder()
                .clientId(faker.code().isbn10())
                .accountNumber(faker.code().asin())
                .residence(faker.address().city())
                .designation(faker.address().country())
                .amount(faker.number().numberBetween(50, 20000))
                .createdOn(LocalDateTime.now().minusHours(randomNumber)
                        .minus(Period.ofWeeks(randomNumber)))
                .build();
    }
}
