package com.revature.cachemoney.backend.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.cachemoney.backend.beans.models.User_Account;

@Repository
public interface User_AccountRepo extends JpaRepository<User_Account, Integer>{

}
