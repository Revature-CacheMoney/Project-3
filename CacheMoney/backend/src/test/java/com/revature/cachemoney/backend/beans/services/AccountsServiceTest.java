package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

class AccountsServiceTest {
    @Autowired
    AccountsService accountsService;

    @BeforeAll
    public static void dataInit(){

    }


    @Test
    void getAllAccounts() {
    }

    @Test
    void getAccountByID() {
    }

    @Test
    void postAccount() {



    }

    @Test
    void deleteAccountById() {
    }

    @Test
    void getTransactionsById() {
    }

    @Test
    void testGetAllAccounts() {
    }

    @Test
    void testGetAccountByID() {
    }

    @Test
    void testPostAccount() {
    }

    @Test
    void testDeleteAccountById() {
    }

    @Test
    void testGetTransactionsById() {
    }
}