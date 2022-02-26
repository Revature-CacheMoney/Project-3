package com.revature.cachemoney.backend.beans.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.cachemoney.backend.beans.models.User;


/**
 * Handles data persistance CRUD
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
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
    Optional<User> findByEmail(String email);
    

	


}