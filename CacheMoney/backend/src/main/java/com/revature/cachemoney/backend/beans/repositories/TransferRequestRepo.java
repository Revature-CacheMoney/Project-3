package com.revature.cachemoney.backend.beans.repositories;

import com.revature.cachemoney.backend.beans.models.TransferRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRequestRepo extends JpaRepository<TransferRequest, Integer> {

    // TODO remove this later
    public List<TransferRequest> findAll();

    public TransferRequest save(TransferRequest transferRequest);

    @Query("FROM TransferRequest tr WHERE tr.destinationAccount.user.userId = :userId")
    public List<TransferRequest> findByRequestedUser(int userId);

    @Query("FROM TransferRequest tr WHERE tr.sourceAccount.user.userId = :userId")
    public List<TransferRequest> findByRequestingUser(int userId);

    public void delete(TransferRequest transferRequest);
}
