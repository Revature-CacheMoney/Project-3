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
public class AccountsService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public AccountsService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    // GET all accounts
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    // GET account by ID
    public Optional<Account> getAccountByID(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUserId().getUser_id() == userId) {
            return accountRepo.findById(accountId);
        }

        return Optional.empty();
    }

    // POST an account
    public void postAccount(Account account) {
        accountRepo.save(account);
    }

    // DELETE an account
    public void deleteAccountById(Integer id) {
        accountRepo.deleteById(id);
    }

    // GET transaction by ID
    public List<Transaction> getTransactionsById(Integer id) {
        ArrayList<Transaction> res = (ArrayList<Transaction>) transactionRepo.findByAccountId(id);
        return res;
    }
}
