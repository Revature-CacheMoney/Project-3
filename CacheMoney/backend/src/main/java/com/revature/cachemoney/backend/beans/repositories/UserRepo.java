package com.revature.cachemoney.backend.beans.repositories;

import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Handles data persistance CRUD
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    /**
     * Retrieve a User by their email.
     * 
     * @param email
     * @return a User object associated with email
     */
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
