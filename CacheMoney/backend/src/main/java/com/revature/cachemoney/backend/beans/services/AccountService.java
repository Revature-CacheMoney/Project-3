package com.revature.cachemoney.backend.beans.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    // GET all accounts
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    // GET account by ID
    public Optional<Account> getAccountByID(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUser().getUserId() == userId) {
            return accountRepo.findById(accountId);
        }

        return Optional.empty();
    }

    // POST an account
    public Boolean postAccount(Account account, Integer userId) {
        if (account.getUser().getUserId() == userId) {
            accountRepo.save(account);

            return true;
        }

        return false;
    }

    // DELETE an account
    public Boolean deleteAccountById(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUser().getUserId() == userId) {
            accountRepo.deleteById(accountId);

            return true;
        }

        return false;
    }

    // GET transaction by ID
    public List<Transaction> getTransactionsById(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUser().getUserId() == userId) {
            return (ArrayList<Transaction>) transactionRepo.findByAccount(accountRepo.getById(accountId));
        }

        return null;
    }
}
