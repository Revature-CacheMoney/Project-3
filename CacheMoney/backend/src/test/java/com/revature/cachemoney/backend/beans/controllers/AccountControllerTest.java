package com.revature.cachemoney.backend.beans.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    
    @MockBean
    private AccountService accountService;
    @MockBean
    private JwtUtil jwtUtil;
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private final AccountController accountController = new AccountController(accountService, mapper);
    
    @Autowired
    private MockMvc mockMvc;
    
    private User user;
    private Account account;
    
    @BeforeEach
    void populateData() {
        user = new User("Jon", "Snow", "jonsnow@gmail.com", "theonetrueking", "jonsnowking");
        user.setUserId(1);
        
        account = new Account("checking", "jonsaccount"); 
        account.setUser(user);
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void getAccountByID() {
    }

    @Test
    void postAccount() throws Exception {
        when(jwtUtil.generateToken(1)).thenReturn("test");
        when(accountService.postAccount(account, user.getUserId())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .header("token", "test")
                         .header("userId", user.getUserId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(account)))
                         .andExpect(MockMvcResultMatchers.status().isOk());
        
//        when(accountService.postAccount(account, user.getUserId())).thenReturn(false);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts").header("token", jwtUtil.generateToken(1))
//                .header("userId", user.getUserId()))
//                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAccountById() {
    }

    @Test
    void getTransactionsById() {
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void transfer() {
    }
}