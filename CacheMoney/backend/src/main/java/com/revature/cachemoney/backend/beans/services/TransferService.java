package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.repositories.TransferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    private TransferRepo repo;

    @Autowired
    public TransferService (TransferRepo repo) {
        this.repo = repo;
    }

    public List<Transfer> findByUser(int userId) {
        return this.repo.findByUser(userId);
    }

    public Transfer save(Transfer transfer) { return this.repo.save(transfer); }
}
