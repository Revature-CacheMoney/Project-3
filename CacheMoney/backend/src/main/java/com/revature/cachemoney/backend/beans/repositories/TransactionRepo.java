package com.revature.cachemoney.backend.beans.repositories;

import java.util.List;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to handle data persistence CRUD tied to a Transaction.
 */
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	/**
	 * FIND some number of Transactions associated with a given Account.
	 * Only need to provided the Account's ID in the object.
	 * 
	 * @param account to find
	 * @return List of Transactions
	 */
	List<Transaction> findByAccount(Account account);
}
