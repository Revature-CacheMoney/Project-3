package com.revature.cachemoney.backend.beans.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for Account requests.
 * 
 * @author Alvin Frierson, Cody Gonsowski, & Jeffrey Lor
 */
@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    // GET all accounts
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    // GET account by ID
    public Optional<Account> getAccountByID(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUser().getUserId() == userId) {
            return accountRepo.findById(accountId);
        }

        return Optional.empty();
    }

    // POST an account
    public Boolean postAccount(Account account, Integer userId) {
        if (account.getUser().getUserId() == userId) {
            accountRepo.save(account);

            return true;
        }

        return false;
    }

    // DELETE an account
    public Boolean deleteAccountById(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUser().getUserId() == userId) {
            accountRepo.deleteById(accountId);

            return true;
        }

        return false;
    }

    // GET transaction by ID
    public List<Transaction> getTransactionsById(Integer accountId, Integer userId) {
        if (accountRepo.getById(accountId).getUser().getUserId() == userId) {
            return (ArrayList<Transaction>) transactionRepo.findByAccount(accountRepo.getById(accountId));
        }

        return null;
    }

    /******* MONEY HANDLING *******/
    /**
     * Deposit to an amount to an Account provided the Account's ID.
     * Creates a transaction with a custom description associated with the deposit.
     * 
     * @param accountId associated with an Account
     * @param amount    to deposit
     * @param desc      description for the transaction
     * @return (true | false) based on deposit status
     */
    public Boolean depositToAccount(Integer userId, Transaction transaction) {
        // create a temporary account
        Optional<Account> optAccount = accountRepo.findById(transaction.getAccount().getAccountId());

        if (optAccount.isPresent()) {
            // placeholder account
            Account account = optAccount.get();

            // transaction amount holder
            Double amount = transaction.getTransactionAmount();

            // verify the account belongs to the user
            if (account.getUser().getUserId() == userId) {
                // force decimal to be 2 places
                amount = Math.round(amount * 100) / 100.0;

                // check if the amount is positive
                if (amount >= 0) {
                    // update the balance locally
                    account.setBalance(account.getBalance() + amount);

                    // set a default description if none is provided
                    if (transaction.getDescription() == null || transaction.getDescription().equals("")) {
                        transaction.setDescription("Deposit");
                    }

                    // set the current date
                    transaction.setTransactionDate(new Date());

                    // set new balance & store it
                    transaction.setEndingBalance(account.getBalance());
                    transactionRepo.save(transaction);

                    // store the updated balance
                    accountRepo.save(account);

                    // deposit success
                    return true;
                }
            }
        }

        // deposit fail
        return false;
    }

    /**
     * Withdraw from an amount to an Account provided the Account's ID.
     * Creates a transaction with a custom description associated with the
     * withdrawal.
     * 
     * @param accountId associated with an Account
     * @param amount    to withdraw
     * @param desc      description for the transaction
     * @return (true | false) based on withdrawal status
     */
    public Boolean withdrawFromAccount(Integer userId, Transaction transaction) {
        // create a temporary account
        Optional<Account> optAccount = accountRepo.findById(transaction.getAccount().getAccountId());

        if (optAccount.isPresent()) {
            // placeholder account
            Account account = optAccount.get();

            // transaction amount holder
            Double amount = transaction.getTransactionAmount();

            // verify the account belongs to the user
            if (account.getUser().getUserId() == userId) {
                // force decimal to be 2 places
                amount = Math.round(amount * 100) / 100.0;

                // check if the amount is positive
                if ((amount >= 0) && (amount <= account.getBalance())) {
                    // update the balance locally
                    account.setBalance(account.getBalance() - amount);

                    // set a default description if none is provided
                    if (transaction.getDescription() == null || transaction.getDescription().equals("")) {
                        transaction.setDescription("Withdrawal");
                    }

                    // set the current date
                    transaction.setTransactionDate(new Date());

                    // set new balance
                    transaction.setEndingBalance(account.getBalance());

                    // store the amount as a negative
                    transaction.setTransactionAmount(-transaction.getTransactionAmount());
                    
                    // save the transaction
                    transactionRepo.save(transaction);

                    // store the updated balance
                    accountRepo.save(account);

                    // withdraw success
                    return true;
                }
            }
        }

        // withdraw fail
        return false;
    }

    /**
     * Transfer money between two Accounts of the same User.
     * 
     * @param sourceAccountId of Account to transfer from
     * @param destAccountId   of Account to transfer to
     * @param amount          to transfer
     * @param desc            description for the transaction
     * @return (true | false) based on transfer status
     */
    public Boolean transferBetweenAccountsOfOneUser(Integer userId, Integer sourceAccountId, Integer destAccountId,
            Transaction transaction) {

        // optional accounts
        Optional<Account> source = accountRepo.findById(sourceAccountId);
        Optional<Account> dest = accountRepo.findById(destAccountId);

        // handle if an account exists
        if (source.isPresent() && dest.isPresent()) {
            // copied transaction
            Transaction destTransaction = new Transaction(dest.get(), transaction.getDescription(), null,
                    transaction.getTransactionAmount(), null);

            // original transaction has linked account
            transaction.setAccount(source.get());

            // validates that the source & destination User IDs match
            if (source.get().getUser().getUserId() == dest.get().getUser().getUserId()) {
                // attempt to withdraw & deposit
                if (withdrawFromAccount(userId, transaction) && depositToAccount(userId, destTransaction)) {
                    // transfer success
                    return true;
                }
            }
        }

        // transfer fail
        return false;
    }

    // SEND money from user's own account to another account owned by ANOTHER user.
    // user initiating transfer CANNOT take money from other user, only send to
    // other user
    // NOT MVP, just setting up stretch goal if there's time
    public void sendToAccountOfDifferentUser(User sender, User receiver, Account sendFromAccount,
            Account receiveToAccount, Double amount) {
        sendFromAccount.setBalance(sendFromAccount.getBalance() - amount);
        receiveToAccount.setBalance(receiveToAccount.getBalance() + amount);
    }
}
