package com.revature.cachemoney.backend.beans.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.services.AccountService;
import com.revature.cachemoney.backend.beans.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
    @MockBean
    private TransactionService transactionService;

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private final TransactionController transactionController = new TransactionController(transactionService, mapper);



    @Test
    void getTransactionByID() throws JsonProcessingException {
        Transaction transaction = new Transaction();

        when(transactionService.getTransactionById(
                1,
               1)).thenReturn(Optional.of(transaction));

        ResponseEntity<String> response = transactionController.getTransactionByID("1",1, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());



    }

    @Test
    void deleteTransactionById() {
        Transaction transaction = new Transaction();

        when(transactionService.deleteTransactionById(
                1,
                1))
                .thenReturn(true);

        ResponseEntity<String> response = transactionController.deleteTransactionById("1",1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(transactionService.deleteTransactionById(
                1,
                1))
                .thenReturn(false);
        response = transactionController.deleteTransactionById("1",1, 1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());



    }
}