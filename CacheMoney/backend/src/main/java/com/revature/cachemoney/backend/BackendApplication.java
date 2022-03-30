package com.revature.cachemoney.backend;

import com.revature.cachemoney.backend.beans.utils.EmailUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.revature.cachemoney.backend.beans")
public class BackendApplication {
	public static void main(String[] args) {
		EmailUtil.getInstance().sendEmailTemplate("mika.nelson@protonmail.com",
				"test email", "test body");
		SpringApplication.run(BackendApplication.class, args);
	}
}
