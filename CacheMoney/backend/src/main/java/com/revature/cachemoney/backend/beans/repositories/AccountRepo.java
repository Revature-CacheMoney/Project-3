package com.revature.cachemoney.backend.beans.repositories;

import com.revature.cachemoney.backend.beans.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Handles data persistance CRUD
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
}
