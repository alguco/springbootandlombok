package com.springboot.lombok.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lombok.exception.AccountNotFoundException;
import com.springboot.lombok.model.Account;
import com.springboot.lombok.service.AccountService;

import lombok.extern.slf4j.Slf4j;

// NOTE - We have left the implementation of HATEOAS principle for simplicity.

// Causes lombok to generate a logger field.
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class AccountController {

    @Autowired
    AccountService accountService;

    // URL - http://localhost:10093/api/accounts
    @GetMapping(value = "accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        log.info("Getting all Accounts from the dB.");
        final Iterable<Account> AccountIterable = accountService.getAllAccounts();
        final List<Account> Accounts = StreamSupport.stream(AccountIterable.spliterator(), false).collect(Collectors.toList());
        return new ResponseEntity<>(Accounts, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/account/id/1
    @GetMapping(value = "account/id/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(name = "id") final int AccountId) {
        log.info("Getting Account with Account-id= {} from the dB.", AccountId);
        final Account Account = accountService.getAccountById(AccountId)
                .orElseThrow(() -> new AccountNotFoundException("account with id= " + AccountId + "not found in the dB."));
        return new ResponseEntity<>(Account, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/account/accountType/Mythopoeia
    @GetMapping(value = "account/accountType/{accountType}")
    public ResponseEntity<List<Account>> getAccountsByAccountType(@PathVariable(name = "accountType") final String accountType) {
        log.info("Getting Account(s) foraccountType= {} from the dB.",accountType);
        final List<Account> Accounts = accountService.getAllAccountsByAccountType(accountType);
        return new ResponseEntity<>(Accounts, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/account/amount/52
    @GetMapping(value = "account/amount/{amount}")
    public ResponseEntity<List<Account>> getAccountsByQuantityGreaterThanEqual(
            @PathVariable(name = "amount") final int amount) {
        log.info("Getting Account(s) from the dB where amount is greater-than or equal to= {}.", amount);
        final List<Account> Accounts = accountService.getAllAccountsByAmountGreaterThanEqual(amount);
        return new ResponseEntity<>(Accounts, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/Account/save
    // Sample HTTP POST request body.
    /*
    {
        "author": "Vasdev Mohi",
        "accountType": "Ghazals",
        "publisher": "Central Sahitya Akademi",
        "title": "Cheque Account",
        "amount": 1,
        "publishedOn": "2020-09-11T11:11:36Z"
    }
    */
    @PostMapping(value = "account/save")
    public ResponseEntity<Void> save(@RequestBody final Account Account) {
        log.info("Saving Account with details= {} in the dB.", Account.toString());
        accountService.save(Account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
