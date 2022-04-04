package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.models.TransferRequest;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransferRepo;
import com.revature.cachemoney.backend.beans.repositories.TransferRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRequestService {
    TransferRequestRepo transferRequestRepo;
    TransferRepo transferRepo;
    AccountRepo accountRepo;

    @Autowired
    public TransferRequestService(TransferRequestRepo transferRequestRepo, TransferRepo transferRepo, AccountRepo accountRepo) {
        this.transferRequestRepo = transferRequestRepo;
        this.transferRepo = transferRepo;
        this.accountRepo = accountRepo;
    }

    public TransferRequest save(TransferRequest transferRequest) {
        Account destinationAccount = accountRepo.getById(transferRequest.getDestinationAccount().getAccountId());
        Account sourceAccount = accountRepo.getById(transferRequest.getSourceAccount().getAccountId());
        // check if destination is different from source
        if(destinationAccount!=sourceAccount) {
            // check if destination and source exists
            if(destinationAccount!=null && sourceAccount!=null) {
                transferRequest.setDestinationAccount(destinationAccount);
                transferRequest.setSourceAccount(sourceAccount);
                return this.transferRequestRepo.save(transferRequest);
            }
        }
        // if source and/or destination is the same or null
        // TODO some form of error handling
        return null;
    }

    public List<TransferRequest> findByRequestedUser(int userId) {
        return this.transferRequestRepo.findByRequestedUser(userId);
    }

    public List<TransferRequest> findByRequestingUser(int userId) {
        return this.transferRequestRepo.findByRequestingUser(userId);
    }

    public void delete(int requestId) {
        // TODO actually check for correct userIDs
        int userId = 1;
        TransferRequest transferRequest = this.transferRequestRepo.findById(requestId);
        int destinationUser = transferRequest.getDestinationAccount().getUser().getUserId();
        int sourceUser = transferRequest.getSourceAccount().getUser().getUserId();
        // only involved users can delete the request
        if(destinationUser == userId || sourceUser == userId) {
            System.out.println(transferRequest);
            this.transferRequestRepo.delete(transferRequest);
        }
    }

    public Transfer acceptTransfer(TransferRequest transferRequest) {
        // TODO enforce that request is to an account I own and that all accounts exist
        // TODO enforce that request is for a positive amount
        // TODO change account balances
        return this.transferRepo.save(transferRequest.toTransfer());
    }
}
