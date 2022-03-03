package com.revature.cachemoney.backend.beans.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.User;


/**
 * Handles data persistance CRUD
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{

	//public List<Transaction> getTransactions();
	
}
