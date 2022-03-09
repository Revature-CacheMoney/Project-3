package com.revature.cachemoney.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import com.revature.cachemoney.backend.beans.services.AccountsService;

@SpringBootApplication(scanBasePackages = "com.revature.cachemoney.backend.beans")
public class BackendApplication {
	
	public static ApplicationContext context;
	public static UserRepo userRepository;
	public static AccountsService acctSvc;
	public static TransactionRepo trnsRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
