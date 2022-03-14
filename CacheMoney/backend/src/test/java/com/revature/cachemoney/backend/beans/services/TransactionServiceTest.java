package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TransactionServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    private Account account;
    private List<Transaction> transactionList;
    private User user;

//    @Autowired
//    TransactionServiceTest(AccountService accountService, UserService userService, TransactionService transactionService){
//        this.accountService = accountService;
//        this.userService = userService;
//        this.transactionService = transactionService;
//    }

    @BeforeEach
    void populateLocalandDBData(){
        if (transactionService.getAllTransactions().size() != 0){
            transactionService.deleteAllTransactions();
        }
        if (accountService.getAllAccounts().size() != 0){
            accountService.deleteAllAccounts();
        }
        if (userService.getAllUsers().size() != 0){
            userService.deleteAllUsers();
        }

        // initial conditions created.
        transactionList = new LinkedList<>();
        user = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "propanemoney");

        userService.postUser(user);
        account = new Account("checking", "secret account");
        account.setUser(user);
        accountService.postAccount(account, user.getUserId());

        // First deposit made
        Transaction depositOne = new Transaction(account,
                "my first deposit", null, 1.00, null);
        accountService.depositToAccount(user.getUserId(),depositOne);
        transactionList.add(depositOne);


        // updating the status of account so the next transaction has up-to-date data.
        if (accountService.getAccountByID(account.getAccountId(), user.getUserId()).isPresent()){
            account = accountService.getAccountByID(account.getAccountId(), user.getUserId()).get();
        }

        // Second deposit made
        Transaction depositTwo = new Transaction(account,
                "my second deposit", null, 20.00, null);
        accountService.depositToAccount(user.getUserId(),depositTwo);
        transactionList.add(depositTwo);


        // updating the status of account so the next transaction has up-to-date data.
        if (accountService.getAccountByID(account.getAccountId(), user.getUserId()).isPresent()){
            account = accountService.getAccountByID(account.getAccountId(), user.getUserId()).get();
        }

        //withdrawal conducted
        Transaction withdrawal = new Transaction(account,
                "my first withdrawal", null, 1.00, null);
        accountService.withdrawFromAccount(user.getUserId(), withdrawal);
        transactionList.add(withdrawal);


        // adding all of these transactions to a list in case we need them for comparison.

//        transactionList.add(depositOne);
//        transactionList.add(depositTwo);
//        transactionList.add(withdrawal);

    }
    @AfterEach
    void depopulateLocalAndDBData(){

        if (transactionService.getAllTransactions().size() != 0){
            transactionService.deleteAllTransactions();
        }
        if (accountService.getAllAccounts().size() != 0){
            accountService.deleteAllAccounts();
        }
        if (userService.getAllUsers().size() != 0){
            userService.deleteAllUsers();
        }
        user = null;
        account = null;
        transactionList = null;
    }

    @Test
    void getAllTransactions() {
        assertEquals(3, transactionService.getAllTransactions().size());
    }

    @Test
    void getTransactionById() {
        // we check that the ending balances are the same
        for (Transaction transaction : transactionList){
            assertEquals(transaction.getEndingBalance(),
                    transactionService.getTransactionById(
                            transaction.getTransactionId(),
                            user.getUserId()).get().getEndingBalance());
        }

        assertFalse(transactionService.getTransactionById(-1, user.getUserId()).isPresent());
        assertFalse(transactionService.getTransactionById(transactionList.get(0).getTransactionId(), -1).isPresent());
        assertFalse(transactionService.getTransactionById(-1, -1).isPresent());
    }

    @Test
    void deleteTransactionById() {

        assertFalse(transactionService.deleteTransactionById(-1,-1));
        assertFalse(transactionService.deleteTransactionById(-1, user.getUserId()));
        assertFalse(transactionService.deleteTransactionById(transactionList.get(0).getTransactionId(), -1));
        assertTrue(transactionService.deleteTransactionById(transactionList.get(0).getTransactionId(), user.getUserId()));

    }

//    @Test
//    void deleteAllTransactions() {
//    }
}