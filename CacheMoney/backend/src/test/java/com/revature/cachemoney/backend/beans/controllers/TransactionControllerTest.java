/**
 * Unit testing of the TransactionController class.
 * Authors: David Alvarado, Brandon Perrien,
 *          Jeremiah Smith, Alvin Frierson,
 *          Trevor Hughes, Maja Wirkijowska,
 *          Ahmad Rawashdeh, Ibrahima Diallo,
 *          Brian Gardner, Jeffrey Lor,
 *          Mark Young.
 *
 */
package com.revature.cachemoney.backend.beans.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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


    /**
     *
     * Method test to check if we can retrieve
     * a transaction with a transactionId
     * and userId
     * */
    @Test
    void getTransactionByID() throws JsonProcessingException {
        // new transaction created
        Transaction transaction = new Transaction();

        // mocking transactionService to return a list of transactions
        when(transactionService.getTransactionById(
                1,
               1)).thenReturn(Optional.of(transaction));
        // getTransactionByID output saved to response for later use
        ResponseEntity<String> response = transactionController.getTransactionByID("1",1, 1);

        // Checking status code
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void deleteTransactionById() {
        // new transaction created
        Transaction transaction = new Transaction();

        // mocking transactionService to return true
        when(transactionService.deleteTransactionById(
                1,
                1))
                .thenReturn(true);
        // deleteTransactionById output saved to response for later use
        ResponseEntity<String> response = transactionController.deleteTransactionById("1",1, 1);
        // http response code checked
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Mocking a failed delete by the service
        when(transactionService.deleteTransactionById(
                1,
                1))
                .thenReturn(false);
        // response updated to the new output from deleteTransactionID method
        response = transactionController.deleteTransactionById("1",1, 1);
        // checking http response code
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());



    }
}