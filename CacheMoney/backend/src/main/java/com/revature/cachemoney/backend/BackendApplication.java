package com.revature.cachemoney.backend;

import static com.revature.cachemoney.backend.bones.AppUtils.*;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import com.revature.cachemoney.backend.beans.utils.ApplicationContextProvider;

@SpringBootApplication(scanBasePackages = "com.revature.cachemoney.backend.beans")
public class BackendApplication {

	public static void main(String[] args) {
		Log("------------->>>");
		SpringApplication.run(BackendApplication.class, args);
		Log("<<<-------------");
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		
		UserRepo userRepository = context.getBean(UserRepo.class);
		AccountRepo acctRepository = context.getBean(AccountRepo.class);
		TransactionRepo trnsRepository = context.getBean(TransactionRepo.class);
		//User_AccountRepo userAcctRepository = context.getBean(User_AccountRepo.class);
		
		User newUser = new User("steve", "steve", "steve@steve.steve", "steve", "steve");
		Account newAccount = new Account("CHECKING");
		newAccount.setBalance(8080.21f);
		userRepository.save(newUser);
		acctRepository.save(newAccount);
		newUser.addAccount(newAccount);
		userRepository.save(newUser);
		
		ArrayList<User> L = (ArrayList<User>) userRepository.findAll();

		for (int l = 0; l < L.size(); l++) {
			User u = L.get(l);
			System.out.println(u);
			Log(u.getUser_id());
		}
		
		Log("_");
		Account a = acctRepository.getById(1);
		Log("_");
		ArrayList<Account> I = (ArrayList<Account>) acctRepository.findAll();

		for (int i = 0; i < I.size(); i++) {
			Account u = I.get(i);
			System.out.println(u);
			Log(u.getType());
			Log(u.getBalance());
		}


		
		
	}

}
