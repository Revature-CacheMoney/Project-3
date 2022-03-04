package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.revature.cachemoney.backend.bones.AppUtils.Log;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    // DEPOSIT transaction by account_id
    public void depositToAccount(Account targetAccount, Double amount) {

    }

    // WITHDRAW from account by account_id
    // do we want a minimum amount that can be withdrawn, or a minimum bill that can be returned
    public void withdrawFromAccount(Account targetAccount, Double amount) {

    }

    // TRANSFER between ONE user's accounts by account_id
    // I don't think we need a User in the method signature, but I've added it for now
    public void transferBetweenUserAccounts(User user, Account source, Account target, Double amount) {
    }

    // GET all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    // GET transaction by transaction id
    public Optional<Transaction> getAccountById(Integer id) {
        return transactionRepo.findById(id);
    }

    // POST a transaction
    public void postTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    // DELETE a transaction
    // do we need this, even when bank accounts are closed, there's always a transaction history
    public void deleteTransactionById(Integer id) {
        transactionRepo.deleteById(id);
    }



}
