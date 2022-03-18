package com.revature.cachemoney.backend.beans.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for Transaction requests.
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

    /**
     * Service method to GET *ALL* Transactions.
     * 
     * @return List of Transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    /**
     * Service method to GET a Transactions by Transaction's ID.
     * 
     * @param transactionId of Transaction to find
     * @param userId        to verify the User is associated with the Transaction
     * @return Transaction associated with the User
     */
    public Optional<Transaction> getTransactionById(Integer transactionId, Integer userId) {
        Optional<Transaction> returnTransaction = transactionRepo.findById(transactionId);
        
        if (returnTransaction.isPresent()) {
            if (Objects.equals(returnTransaction.get().getAccount().getUser().getUserId(), userId)) {
                return transactionRepo.findById(transactionId);
            }
        }

        return Optional.empty();
    }

    /**
     * Service method to DELETE a Transaction by Transaction's ID.
     * 
     * @param transactionId of Transaction to delete
     * @param userId        to verify the User is associated with the Transaction
     * @return (true | false) if the User is associated with the Transaction
     */
    public Boolean deleteTransactionById(Integer transactionId, Integer userId) {
        Optional<Transaction> returnTransaction = transactionRepo.findById(transactionId);

        if (returnTransaction.isPresent()) {
            if (Objects.equals(returnTransaction.get().getAccount().getUser().getUserId(), userId)) {
                transactionRepo.deleteById(transactionId);

                return true;
            }
        }

        return false;
    }

    /**
     *
     * ******************STRICTLY FOR TESTING PURPOSES*********************
     *
     * */
    public void deleteAllTransactions(){
        transactionRepo.deleteAll();
    }
}
