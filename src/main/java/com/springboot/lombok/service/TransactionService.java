package com.springboot.lombok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lombok.model.Transaction;
import com.springboot.lombok.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void save(final Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public long getTransactionsCount() {
        return transactionRepository.count();
    }

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(final int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public List<Transaction> getAllTransactionsByGenre(final String genre) {
        return transactionRepository.findTransactionByDesignation(genre);
    }

    public List<Transaction> getAllTransactionsByQuantityGreaterThanEqual(final int quantity) {
        return transactionRepository.findTransactionByAmountGreaterThanEqual(quantity);
    }

	public List<Transaction> getAllTransactionsByDesignation(String designation) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> getAllTransactionsByAmountGreaterThanEqual(int amount) {
		// TODO Auto-generated method stub
		return null;
	}
}
