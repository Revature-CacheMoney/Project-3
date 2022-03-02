package com.revature.cachemoney.backend.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.cachemoney.backend.beans.models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
