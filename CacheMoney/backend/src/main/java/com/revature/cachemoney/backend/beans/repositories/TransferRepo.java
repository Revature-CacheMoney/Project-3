package com.revature.cachemoney.backend.beans.repositories;


import com.revature.cachemoney.backend.beans.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepo extends JpaRepository<Transfer, Integer> {

    @Query("FROM Transfer t WHERE t.destinationAccount.user.userId = :userId")
    public List<Transfer> findByUser(int userId);

    public Transfer save(Transfer transfer);
}
