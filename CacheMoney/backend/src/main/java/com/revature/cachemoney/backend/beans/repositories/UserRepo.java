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
    Optional<User> findByUsername(String username);

}
