package com.revature.cachemoney.backend.beans.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountsServiceTest {
    @Autowired
    AccountsService accountsService;
    
    @Autowired
    private UsersService usersService;
    
    private static List<User> userList;

    @BeforeAll
    public static void dataInit(){
        userList = new LinkedList<>();
        
        User user1 = new User("martin", "smith", "martin.smith@gmail.com", "martinpassword", "martinrules");
        
        userList.add(user1);
    }


    @Test
    void getAllAccounts() {
    }

    @Test
    void getAccountByID() {
    }

    @Test
    void postAccount() {
        for (User user : userList) {
            Account testChecking = new Account("checking");
            testChecking.setUserId(user);

            Account testIncorrectType = new Account("blahblah");
            testIncorrectType.setUserId(user);

            assertEquals(false, accountsService.postAccount(testIncorrectType));
            assertEquals(true, accountsService.postAccount(testChecking));
        }
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