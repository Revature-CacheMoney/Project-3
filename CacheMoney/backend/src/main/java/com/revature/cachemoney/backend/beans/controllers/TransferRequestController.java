package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.models.TransferRequest;
import com.revature.cachemoney.backend.beans.services.TransferRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("request")
public class TransferRequestController {
    TransferRequestService transferRequestService;

    @Autowired
    public TransferRequestController(TransferRequestService transferRequestService) {
        this.transferRequestService = transferRequestService;
    }

    @PostMapping
    @RequireJwt
    public TransferRequest save(@RequestBody TransferRequest transferRequest) throws ResponseStatusException {
        return this.transferRequestService.save(transferRequest);
    }

    @GetMapping("source")
    @RequireJwt
    public List<TransferRequest> findByRequestingUser(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId) {
        return this.transferRequestService.findByRequestingUser(userId);
    }

    @GetMapping("destination")
    @RequireJwt
    public List<TransferRequest> findByRequestedUser(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId) {
        return this.transferRequestService.findByRequestedUser(userId);
    }

    @PostMapping("accept/{requestId}")
    @RequireJwt
    public Transfer acceptTransfer(@PathVariable int requestId,
                                   @RequestHeader(name = "token") String token,
                                   @RequestHeader(name = "userId") Integer userId) throws ResponseStatusException {
        return this.transferRequestService.acceptTransfer(requestId, userId);
    }

    @DeleteMapping("{requestId}")
    @RequireJwt
    public void deleteTransfer(@PathVariable int requestId,
                               @RequestHeader(name = "token") String token,
                               @RequestHeader(name = "userId") Integer userId) throws ResponseStatusException {
        this.transferRequestService.delete(requestId, userId);
    }
}
