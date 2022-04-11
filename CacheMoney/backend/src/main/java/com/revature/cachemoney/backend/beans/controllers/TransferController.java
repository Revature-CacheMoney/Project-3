package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("transfer")
public class TransferController {
    private TransferService transferService;
    private JwtUtil jwtUtil;

    @Autowired
    public TransferController(TransferService transferService, JwtUtil jwtUtil) {
        this.transferService = transferService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(value = "destination")
    // TODO @RequireJwt
    public List<Transfer> findByDestinationUser(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId) throws ResponseStatusException {
        if (this.jwtUtil.validateToken(token, userId)) {
            return this.transferService.findByDestinationUser(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please provide a valid token");
        }
    }

    @GetMapping(value = "source")
    // TODO @RequireJwt
    public List<Transfer> findBySourceUser(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId) throws ResponseStatusException {
        if (this.jwtUtil.validateToken(token, userId)) {
            return this.transferService.findBySourceUser(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please provide a valid token");
        }
    }

    @PostMapping
    // TODO @RequireJwt
    public Transfer saveTransfer(@RequestBody Transfer transfer,
                                 @RequestHeader(name = "token") String token,
                                 @RequestHeader(name = "userId") Integer userId) throws ResponseStatusException {
        if (this.jwtUtil.validateToken(token, userId)) {
            return this.transferService.save(transfer, userId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please provide a valid token");
        }
    }
}
