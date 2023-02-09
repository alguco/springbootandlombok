package com.springboot.lombok.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.lombok.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAccountByAccountType(String accountType);

    List<Account> findAccountByAmountGreaterThanEqual(int amount);
}
