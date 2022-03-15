package com.revature.cachemoney.backend.beans.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.Transfer;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.services.AccountService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    
    @MockBean
    private AccountService accountService;

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private final AccountController accountController = new AccountController(accountService, mapper);

    
    private User user;
    private Account account;
    
    @BeforeEach
    void populateData() {
        user = new User("Jon", "Snow", "jonsnow@gmail.com", "theonetrueking", "jonsnowking");
        user.setUserId(1);

        
        account = new Account("checking", "jonsaccount"); 
        account.setUser(user);
    }

    @AfterEach
    void unpopulateData(){
        user = null;
        account = null;

    }

    @Test
    void getAccountByID() throws JsonProcessingException {

        when(accountService.getAccountByID(1, 1)).thenReturn(Optional.of(account));
        ResponseEntity<String> response = accountController.getAccountByID("test", 1,1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(accountService.getAccountByID(1, 1)).thenReturn(Optional.empty());
        response = accountController.getAccountByID("test", 1,1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    void postAccount() throws Exception {

        when(accountService.postAccount(account, user.getUserId())).thenReturn(true);
        ResponseEntity<String> response = accountController.postAccount("test", user.getUserId(), account);
        assertEquals(HttpStatus.OK,response.getStatusCode());


        when(accountService.postAccount(account, user.getUserId())).thenReturn(false);
        response = accountController.postAccount("test", user.getUserId(), account);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());


    }

    @Test
    void deleteAccountById() {
        when(accountService.deleteAccountById(account.getAccountId(), user.getUserId())).thenReturn(true);
        ResponseEntity<String> response = accountController.deleteAccountById("test", user.getUserId(), account.getAccountId());
        assertEquals(HttpStatus.OK,response.getStatusCode());

        when(accountService.deleteAccountById(account.getAccountId(), user.getUserId())).thenReturn(false);
        response = accountController.deleteAccountById("test", user.getUserId(), account.getAccountId());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

    }

    @Test
    void getTransactionsById() throws JsonProcessingException {

        when(accountService.getTransactionsById(account.getAccountId(), user.getUserId())).thenReturn(new LinkedList<Transaction>());
        ResponseEntity<String> response = accountController.getTransactionsById("test", user.getUserId(), account.getAccountId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deposit() {
        Transaction transaction = new Transaction();
        when(accountService.depositToAccount(user.getUserId(), transaction)).thenReturn(true);
        ResponseEntity<String> response = accountController.deposit("test", user.getUserId(), transaction);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        when(accountService.depositToAccount(user.getUserId(), transaction)).thenReturn(false);
        response = accountController.deposit("test", user.getUserId(), transaction);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    void withdraw() {
        Transaction transaction = new Transaction();
        when(accountService.withdrawFromAccount(user.getUserId(), transaction)).thenReturn(true);
        ResponseEntity<String> response = accountController.withdraw("test", user.getUserId(), transaction);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        when(accountService.withdrawFromAccount(user.getUserId(), transaction)).thenReturn(false);
        response = accountController.withdraw("test", user.getUserId(), transaction);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    void transfer() {
        Transfer sourceTransfer = new Transfer();

        when(accountService.transferBetweenAccountsOfOneUser(
                user.getUserId(),sourceTransfer.getSourceAccountId(),
                sourceTransfer.getDestinationAccountId(),
                sourceTransfer.getTransaction()))
                .thenReturn(true);
        ResponseEntity<String> response = accountController.transfer("test", user.getUserId(), sourceTransfer);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        when(accountService.transferBetweenAccountsOfOneUser(
                user.getUserId(),sourceTransfer.getSourceAccountId(),
                sourceTransfer.getDestinationAccountId(),
                sourceTransfer.getTransaction()))
                .thenReturn(false);
        response = accountController.transfer("test", user.getUserId(), sourceTransfer);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
}