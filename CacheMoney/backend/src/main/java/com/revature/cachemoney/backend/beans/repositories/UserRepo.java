package com.revature.cachemoney.backend.beans.repositories;

import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to handle data persistence CRUD tied to a User.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    /**
     * FIND a User given its username.
     * 
     * @param username to find
     * @return User object if it exists
     */
    Optional<User> findByUsername(String username);
}
