package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public Transfer save(Transfer transfer, Integer userId) {
        int sourceId = transfer.getSourceAccount().getAccountId();
        transfer.setSourceAccount(accountRepo.getById(sourceId));
        if (transfer.getSourceAccount().getUser().getUserId() != userId) {
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
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            //return null;
        }
        double amount = transfer.getAmount();
        double balance = transfer.getSourceAccount().getBalance();
        if (amount > 0 && balance >= amount) {
            transfer.getSourceAccount().setBalance(balance - amount);
            double destBalance = transfer.getDestinationAccount().getBalance();
            transfer.getDestinationAccount().setBalance(destBalance + amount);
            System.out.println(transfer.toString());
            return this.transferRepo.save(transfer);
        } else {
            // TODO show some error to front end like "Not enough money" or "Amount less than zero"
            System.out.println("Not enough money to transfer for this request");
            return null;
        }
    }
}
