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

import com.springboot.lombok.exception.TransactionNotFoundException;
import com.springboot.lombok.model.Transaction;
import com.springboot.lombok.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

// NOTE - We have left the implementation of HATEOAS principle for simplicity.

// Causes lombok to generate a logger field.
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // URL - http://localhost:10093/api/transactions
    @GetMapping(value = "transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        log.info("Getting all transactions from the dB.");
        final Iterable<Transaction> transactionIterable = transactionService.getAllTransactions();
        final List<Transaction> transactions = StreamSupport.stream(transactionIterable.spliterator(), false).collect(Collectors.toList());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/transaction/id/1
    @GetMapping(value = "transaction/id/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable(name = "id") final int transactionId) {
        log.info("Getting transaction with transaction-id= {} from the dB.", transactionId);
        final Transaction transaction = transactionService.getTransactionById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id= " + transactionId + "not found in the dB."));
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/transaction/designation/Mythopoeia
    @GetMapping(value = "transaction/designation/{designation}")
    public ResponseEntity<List<Transaction>> getTransactionsByDesignation(@PathVariable(name = "designation") final String designation) {
        log.info("Getting transaction(s) for designation= {} from the dB.", designation);
        final List<Transaction> transactions = transactionService.getAllTransactionsByDesignation(designation);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/transaction/amount/52
    @GetMapping(value = "transaction/amount/{amount}")
    public ResponseEntity<List<Transaction>> getTransactionsByAmountGreaterThanEqual(
            @PathVariable(name = "amount") final int amount) {
        log.info("Getting transaction(s) from the dB where amount is greater-than or equal to= {}.", amount);
        final List<Transaction> transactions = transactionService.getAllTransactionsByAmountGreaterThanEqual(amount);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/transaction/save
    // Sample HTTP POST request body.
    /*
    {
        "author": "Vasdev Mohi",
        "designation": "Ghazals",
        "publisher": "Central Sahitya Akademi",
        "title": "Cheque transaction",
        "amount": 1,
        "publishedOn": "2020-09-11T11:11:36Z"
    }
    */
    @PostMapping(value = "transaction/save")
    public ResponseEntity<Void> save(@RequestBody final Transaction transaction) {
        log.info("Saving transaction with details= {} in the dB.", transaction.toString());
        transactionService.save(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
