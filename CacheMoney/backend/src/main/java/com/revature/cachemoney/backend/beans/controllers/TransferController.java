package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transfer")
public class TransferController {
    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping(value = "destination/{userId}")
    public List<Transfer> findByDestinationUser(@PathVariable int userId) {
        return this.transferService.findByDestinationUser(userId);
    }

    @GetMapping(value = "source/{userId}")
    public List<Transfer> findBySourceUser(@PathVariable int userId) {
        return this.transferService.findBySourceUser(userId);
    }

    @PostMapping
    //@RequireJwt
    public Transfer saveTransfer(@RequestBody Transfer transfer,
                                 //@RequestHeader(name = "token") String token,
                                 @RequestHeader(name = "userId") Integer userId) {
        return this.transferService.save(transfer, userId);
    }
}
