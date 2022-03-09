package com.revature.cachemoney.backend.beans.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public AccountsService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    // GET all accounts
    public List<Account> getAllAccounts() {

        return accountRepo.findAll();
    }

    // GET account by ID
    public Optional<Account> getAccountByID(Integer id) {

        return accountRepo.findById(id);
    }

    // POST an account
    // needs to return boolean so correct http code can be sent back.
    public Boolean postAccount(Account account) {
        String checking = "checking";
        String saving = "savings";
        if (account.getType().equals(checking) || account.getType().equals(saving)) {
            accountRepo.save(account);
            return true;
        }else{
            return false;
        }
    }

    // DELETE an account
    public Boolean deleteAccountById(Integer id) {
        try {
            accountRepo.deleteById(id);
            return true;
        } catch (Exception e){
            System.out.println("exception thrown when attempting to delete an account.");
            return false;
        }
    }

    // GET transaction by ID
    public List<Transaction> getTransactionsById(Integer id) {
        ArrayList<Transaction> res = (ArrayList<Transaction>) transactionRepo.findByAccountId(id);
        return res;
    }


    /**
     *
     * ******************STRICTLY FOR TESTING PURPOSES*********************
     *
     * */
    public void deleteAllAccounts(){
       accountRepo.deleteAll();
    }
}
