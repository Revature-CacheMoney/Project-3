package com.revature.cachemoney.backend.beans.services;

import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for Account requests.
 * 
 * @author Alvin Frierson
 */
@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    // GET all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    // GET transaction by transaction id
    public Optional<Transaction> getTransactionById(Integer transactionId, Integer userId) {
        if (transactionRepo.getById(transactionId).getAccount().getUser().getUserId() == userId) {
            return transactionRepo.findById(transactionId);
        }

        return Optional.empty();
    }

    // DELETE a transaction by ID
    public Boolean deleteTransactionById(Integer transactionId, Integer userId) {
        if (transactionRepo.getById(transactionId).getAccount().getUser().getUserId() == userId) {
            transactionRepo.deleteById(transactionId);

            return true;
        }

        return false;
    }
}
