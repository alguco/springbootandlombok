package com.springboot.lombok.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.lombok.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findTransactionByDesignation(String designation);

    List<Transaction> findTransactionByAmountGreaterThanEqual(int amount);
}
