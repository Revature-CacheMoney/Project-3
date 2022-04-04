package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TransferService {
    private TransferRepo transferRepo;
    private AccountRepo accountRepo;

    @Autowired
    public TransferService (TransferRepo transferRepo, AccountRepo accountRepo) {
        this.transferRepo = transferRepo;
        this.accountRepo = accountRepo;
    }

    public List<Transfer> findByDestinationUser(int userId) {
        return this.transferRepo.findByDestinationUser(userId);
    }

    public List<Transfer> findBySourceUser(int userId) {
        return this.transferRepo.findBySourceUser(userId);
    }

    public Transfer save(Transfer transfer) {
        int sourceId = transfer.getSourceAccount().getAccountId();
        transfer.setSourceAccount(accountRepo.getById(sourceId));
        if (transfer.getSourceAccount().getUser().getUserId() != 1) { // TODO change 1 to the user's id
            // TODO add some kind of error handling here
            System.out.println("No user found");
            return null;
        }
        int destId = transfer.getDestinationAccount().getAccountId();
        System.out.println("Before account is gotten from repo");
        try {
            transfer.setDestinationAccount(accountRepo.getById(destId));
            System.out.println(transfer.getDestinationAccount().toString());
        } catch (EntityNotFoundException e) {
            // TODO add some kind of error handling here
            return null;
        }
        double amount = transfer.getAmount();
        double balance = transfer.getSourceAccount().getBalance();
        if (amount > 0 && balance >= amount) {
            transfer.getSourceAccount().setBalance(balance - amount);
            double destBalance = transfer.getDestinationAccount().getBalance();
            transfer.getDestinationAccount().setBalance(destBalance + amount);
            return this.transferRepo.save(transfer);
        } else {
            // TODO show some error to front end like "Not enough money" or "Amount less than zero"
            return null;
        }
    }
}
