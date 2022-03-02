package com.revature.cachemoney.backend.beans.repositories;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.cachemoney.backend.beans.models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByAccountId(Integer acctId);
	
}
