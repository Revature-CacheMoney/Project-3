package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.models.TransferRequest;
import com.revature.cachemoney.backend.beans.services.TransferRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public TransferRequest save(@RequestBody TransferRequest transferRequest) {
        return this.transferRequestService.save(transferRequest);
    }

    @GetMapping("source/{userId}")
    public List<TransferRequest> findByRequestingUser(@PathVariable int userId) {
        return this.transferRequestService.findByRequestingUser(userId);
    }

    @GetMapping("destination/{userId}")
    public List<TransferRequest> findByRequestedUser(@PathVariable int userId) {
        return this.transferRequestService.findByRequestedUser(userId);
    }

    @PostMapping("accept/{requestId}")
    //@RequireJwt
    public Transfer acceptTransfer(@PathVariable int requestId,
                                   //@RequestHeader(name = "token") String token,
                                   @RequestHeader(name = "userId") Integer userId) {
        return this.transferRequestService.acceptTransfer(requestId, userId);
    }

    @DeleteMapping("{requestId}")
    public void deleteTransfer(@PathVariable int requestId) {
        this.transferRequestService.delete(requestId);
    }
}
