package com.revature.cachemoney.backend.beans.repositories;

import com.revature.cachemoney.backend.beans.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    /**
     * JpaRepository creates a concrete implementation for you based on the naming
     * scheme of the function itself.
     */
    /**
     * Retrieve a User by their email.
     *
     * @param email
     * @return a User object associated with email
     */


}
