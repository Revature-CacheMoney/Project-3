package com.revature.cachemoney.backend.beans.repositories;

import java.util.List;

import com.revature.cachemoney.backend.beans.models.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	List<Transaction> findByAccountId(Integer acctId);
}
