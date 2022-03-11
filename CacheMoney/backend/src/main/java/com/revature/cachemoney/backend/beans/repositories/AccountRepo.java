package com.revature.cachemoney.backend.beans.repositories;

import java.util.List;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    List<Account> findByUserId(User userId);
}
