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

	public static UserRepo userRepository;
	public static AccountRepo acctRepository;
	public static TransactionRepo trnsRepository;

	public static void main(String[] args) {
		Log("------------->>>");
		SpringApplication.run(BackendApplication.class, args);
		Log("<<<-------------");
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();

		UserRepo userRepository = context.getBean(UserRepo.class);
		AccountRepo acctRepository = context.getBean(AccountRepo.class);
		TransactionRepo trnsRepository = context.getBean(TransactionRepo.class);


		User newUser = new User("steve", "steve", "steve@steve.steve", "steve", "steve");
		Account newAccount = new Account("CHECKING");
		newAccount.setBalance(8080.21f);
		

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
