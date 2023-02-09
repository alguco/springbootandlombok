package com.springboot.lombok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lombok.model.Account;
import com.springboot.lombok.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository AccountRepository;

    public void save(final Account Account) {
        AccountRepository.save(Account);
    }

    public long getAccountsCount() {
        return AccountRepository.count();
    }

    public Iterable<Account> getAllAccounts() {
        return AccountRepository.findAll();
    }

    public Optional<Account> getAccountById(final int AccountId) {
        return AccountRepository.findById(AccountId);
    }

    public List<Account> getAllAccountsByAccountType(final String accountType) {
        return AccountRepository.findAccountByAccountType(accountType);
    }

    public List<Account> getAllAccountsByAmountGreaterThanEqual(final int amount) {
        return AccountRepository.findAccountByAmountGreaterThanEqual(amount);
    }
}
