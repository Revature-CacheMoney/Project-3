package com.revature.cachemoney.backend.beans.services;

import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    // DEPOSIT transaction by account_id
    public void depositToAccount(Account targetAccount, Double amount) {
        targetAccount.setBalance(targetAccount.getBalance() + amount);
    }

    // WITHDRAW from account by account_id
    // do we want a minimum amount that can be withdrawn, or a minimum bill that can
    // be returned
    public void withdrawFromAccount(Account targetAccount, Double amount) {
        targetAccount.setBalance(targetAccount.getBalance() - amount);
    }

    // TRANSFER between ONE user's accounts by account_id
    public void transferBetweenAccountsOfOneUser(Account source, Account target, Double amount) {
        source.setBalance(source.getBalance() - amount);
        target.setBalance(target.getBalance() + amount);
    }

    // SEND money from user's own account to another account owned by ANOTHER user.
    // user initiating transfer CANNOT take money from other user, only send to
    // other user
    // NOT MVP, just setting up stretch goal if there's time
    public void sendToAccountOfDifferentUser(User sender, User receiver, Account sendFromAccount,
            Account receiveToAccount, Double amount) {
        sendFromAccount.setBalance(sendFromAccount.getBalance() - amount);
        receiveToAccount.setBalance(receiveToAccount.getBalance() + amount);
    }

    // GET all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    // GET transaction by transaction id
    public Optional<Transaction> getTransactionById(Integer transactionId, Integer userId) {
        if (transactionRepo.getById(transactionId).getAccountId().getUserId().getUser_id() == userId) {
            return transactionRepo.findById(transactionId);
        }

        return Optional.empty();
    }

    // POST a transaction
    public Boolean postTransaction(Transaction transaction, Integer userId) {
        if (transaction.getAccountId().getUserId().getUser_id() == userId) {
            transactionRepo.save(transaction);

            return true;
        }

        return false;
    }

    // DELETE a transaction by ID
    public Boolean deleteTransactionById(Integer transactionId, Integer userId) {
        if (transactionRepo.getById(transactionId).getAccountId().getUserId().getUser_id() == userId) {
            transactionRepo.deleteById(transactionId);

            return true;
        }

        return false;
    }
}
