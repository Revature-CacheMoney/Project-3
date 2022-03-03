package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.BackendApplication;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.revature.cachemoney.backend.bones.AppUtils.Log;

@Service
public class AccountsService {

    private final AccountRepo accountRepo;

    @Autowired
    public AccountsService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    // GET all accounts
    public List<Account> getAllAccounts() {

        return accountRepo.findAll();
    }

    // GET account by ID
    public Optional<Account> getAccountByID(Integer id) {

        return accountRepo.findById(id);
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

        TransactionRepo trns = BackendApplication.trnsRepository;
        ArrayList<Transaction> res = (ArrayList<Transaction>) trns.findByAccountId(id);
        Log(">");
        return res;
    }
}
