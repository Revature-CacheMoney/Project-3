package com.revature.cachemoney.backend.beans.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getAccountId() {
        Account account = new Account("checking", "new account");
        assertNull(account.getAccountId());
        account.setAccountId(1);
        assertEquals(1, account.getAccountId());
    }

    @Test
    void getType() {
        Account account = new Account("checking", "new account");
        assertEquals("checking", account.getType());
    }

    @Test
    void getBalance() {
        Account account = new Account("checking", "new account");
        assertEquals(0.00, account.getBalance());
        account.setBalance(1.50);
        assertEquals(1.50, account.getBalance());
    }

    @Test
    void getName() {
        Account account = new Account("checking", "new account");
        assertEquals("new account", account.getName());
    }

    @Test
    void getUser() {
        User user = new User("user", "model", "email@gmail.com", "password", "userModel");
        Account account = new Account("checking", "new account");
        account.setUser(user);
        assertEquals(user.toString(), account.getUser().toString());

    }

    @Test
    void setAccountId() {
        Account account = new Account("checking", "new account");
        assertNull(account.getAccountId());
        account.setAccountId(1);
        assertNotNull(account.getAccountId());
        assertEquals(1, account.getAccountId());
    }

    @Test
    void setType() {
        Account account = new Account();
        assertNull(account.getType());
        account.setType("savings");
        assertNotNull(account.getType());
        assertEquals("savings", account.getType());

    }

    @Test
    void setBalance() {
        Account account = new Account();
        assertNull(account.getBalance());
        account.setBalance(1.50);
        assertNotNull(account.getBalance());
        assertEquals(1.50, account.getBalance());
    }

    @Test
    void setName() {
        Account account = new Account();
        assertNull(account.getName());
        account.setName("my new account");
        assertNotNull(account.getName());
        assertEquals("my new account", account.getName());
    }

    @Test
    void setUser() {
        Account account = new Account();
        User user = new User();
        assertNull(account.getAccountId());
        account.setUser(user);
        assertNotNull(account.getUser());
        assertEquals(user.toString(), account.getUser().toString());
    }

    @Test
    void testToString() {
        Account account = new Account();
        String expectedString = "Account(accountId=null, type=null, balance=null, name=null, user=null)";
        assertEquals(expectedString, account.toString());
        account.setAccountId(1);
        expectedString = "Account(accountId=1, type=null, balance=null, name=null, user=null)";
        assertEquals(expectedString, account.toString());
        account.setType("checking");
        expectedString = "Account(accountId=1, type=checking, balance=null, name=null, user=null)";
        assertEquals(expectedString, account.toString());
        account.setBalance(250.99);
        expectedString = "Account(accountId=1, type=checking, balance=250.99, name=null, user=null)";
        assertEquals(expectedString, account.toString());
        account.setName("secret account");
        expectedString = "Account(accountId=1, type=checking, balance=250.99, name=secret account, user=null)";
        assertEquals(expectedString, account.toString());
        account.setUser(new User());
        expectedString = "Account(accountId=1, type=checking, balance=250.99, name=secret account, " +
                "user=User(userId=null, firstName=null, lastName=null, email=null, username=null, password=null))";
        assertEquals(expectedString, account.toString());


    }
}