package com.revature.cachemoney.backend.beans.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Autowired
    private UserService userService;
    
    private User tempUser;

    private List<Account> validAccounts;

    @BeforeEach
    void populateDBWithUserandAccounts(){
        tempUser = new User("Hank", "Hill", "hankaccounthill@gmail.com", "abcd1234", "accounttest");
        userService.postUser(tempUser);

        // Create accounts (They dont currently have a valid user model under attribute "userId")
        Account checkingAcc = new Account("checking");
        Account savingsAcc = new Account("savings");
        Account checkingAccWithNickname = new Account("checking", "secret account");

        //populate userId with a user that exists in database
        tempUser.setUserId(1);
        checkingAcc.setUser(tempUser);
        savingsAcc.setUser(tempUser);
        checkingAccWithNickname.setUser(tempUser);

        // add accounts to list.
        validAccounts = new LinkedList<>();
        validAccounts.add(checkingAcc);
        validAccounts.add(savingsAcc);
        validAccounts.add(checkingAccWithNickname);

        // persist accounts to database
        for (Account validAcc : validAccounts){
            accountService.postAccount(validAcc, validAcc.getUser().getUserId());
        }

    }

    @AfterEach
    void deleteDBData(){

        accountService.deleteAllAccounts();
        userService.deleteAllUsers();

        // must be set to null because after models are persisted
        // these variables will now contain an ID value that was
        // generated by the database.

        validAccounts = null;
        tempUser = null;

    }



    @Test
    void getAllAccounts() {
        assertEquals(3, accountService.getAllAccounts().size());
    }

    @Test
    void getAccountByID() {

        List<Account> accountListFromDB = accountService.getAllAccounts();

        for (Account currAcc : accountListFromDB){
            assertEquals(
                    accountService.getAccountByID(currAcc.getAccountId(),
                            currAcc.getUser().getUserId()).get().toString(),
            currAcc.toString());
        }

        assertFalse(accountService.getAccountByID(0, 1).isPresent());

    }
//
//
//
//
//    @Test
//    void deleteAccountById() {
//        List<Account> accountListFromDB = accountService.getAllAccounts();
//
//        for (Account currAcc : accountListFromDB){
//            assertTrue(accountService.deleteAccountById(currAcc.getAccountId(), currAcc.getUser().getUserId()));
//        }
//
//        assertFalse(accountService.deleteAccountById(0, 1));
//
//    }
//
//
//
//
//    @Test
//    void getTransactionsById() {
//    }
//
//
//    @Nested
//    class TestPostAccount{
//        @BeforeEach
//        void populateDB(){
//            if (userService.getAllUsers().size() != 0) {
//                if (accountService.getAllAccounts().size() != 0){
//                    accountService.deleteAllAccounts();
//                }
//                userService.deleteAllUsers();
//                tempUser = new User("Hank", "Hill", "hankaccounthill@gmail.com", "abcd1234", "accounttest");
//                userService.postUser(tempUser);
//            }else{
//                tempUser = new User("Hank", "Hill", "hankaccounthill@gmail.com", "abcd1234", "accounttest");
//                userService.postUser(tempUser);
//            }
//            if (accountService.getAllAccounts().size() != 0){
//                accountService.deleteAllAccounts();
//            }
//
//
//        }
//
//        @AfterEach
//        void deleteDBData(){
//            accountService.deleteAllAccounts();
//            userService.deleteAllUsers();
//
//            tempUser = null;
//
//        }
//
//        @Test
//        void postAccount() {
//            System.out.println("account service test postaccount");
//
//            Account testChecking = new Account("checking");
//            Account testIncorrectType = new Account("blahblah");
//            testChecking.setUser(tempUser);
//            testIncorrectType.setUser(tempUser);
//
//
//            assertEquals(false, accountService.postAccount(testIncorrectType, tempUser.getUserId()));
//            assertEquals(true, accountService.postAccount(testChecking, tempUser.getUserId()));
//            // need to check for valid account but incorrect user id
//
//
//        }
//
//
//    }

}