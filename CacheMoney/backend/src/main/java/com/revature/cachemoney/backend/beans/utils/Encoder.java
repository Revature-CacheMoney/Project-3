package com.revature.cachemoney.backend.beans.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// TODO move this???
// TODO look into 401 unauthorized from Postman when performing Post through UserController
// https://www.toptal.com/spring/spring-security-tutorial#:~:text=To%20pass%20the,4bec%2Da009%2De32ca21c77ce
@Component
public class Encoder {
    /**
     * Create a new BCryptPasswordEncoder for storing passwords in the database.
     * 
     * @return new BCryptPasswordEncoder
     * @author Brian Gardner, Cody Gonsowski, & Jeffrey Lor
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
