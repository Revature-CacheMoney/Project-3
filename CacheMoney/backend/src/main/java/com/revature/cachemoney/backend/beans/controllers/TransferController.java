package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {
    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping(value = "transfer/{userId}")
    public List<Transfer> findByUser(@PathVariable int userId) {
        return this.transferService.findByUser(userId);
    }

    @PostMapping("transfer")
    public Transfer saveTransfer(@RequestBody Transfer transfer) {
        return this.transferService.save(transfer);
    }
}
