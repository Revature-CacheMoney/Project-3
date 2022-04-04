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
        System.out.println(transferRequest.getDestinationAccount());
//        Account destinationAccount = accountRepo.getById(transferRequest.getDestinationAccount().getAccountId());
//        Account sourceAccount = accountRepo.getById(transferRequest.getSourceAccount().getAccountId());
//        transferRequest.setDestinationAccount(destinationAccount);
//        transferRequest.setSourceAccount(sourceAccount);
//        return this.transferRequestRepo.save(transferRequest);
        return null;
    }

    public List<TransferRequest> findByRequestedUser(int userId) {
        return this.transferRequestRepo.findByRequestedUser(userId);
    }

    public List<TransferRequest> findByRequestingUser(int userId) {
        return this.transferRequestRepo.findByRequestingUser(userId);
    }

    public void delete(TransferRequest transferRequest) {
        // TODO restrict to requested or requesting users
        this.transferRequestRepo.delete(transferRequest);
    }

    public Transfer acceptTransfer(TransferRequest transferRequest) {
        // TODO enforce that request is to an account I own and that all accounts exist
        // TODO enforce that request is for a positive amount
        // TODO change account balances
        return this.transferRepo.save(transferRequest.toTransfer());
    }
}
