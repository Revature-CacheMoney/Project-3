package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import com.revature.cachemoney.backend.beans.services.AccountsService;
import com.revature.cachemoney.backend.beans.services.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ComponentScan("com.revature.cachemoney.backend.beans.services")
class UserControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private UsersService usersService;
    @Autowired
    private AccountsService accountsService;

//    private  UserController userController;

    private User user;

    UserControllerTest() {
    }


//    @Autowired
//    UserControllerTest(UsersService usersService, AccountsService accountsService){
//        this.usersService = usersService;
//        this.accountsService = accountsService;
//    }

    @BeforeEach
    void populateDB(){
    if (usersService.getAllUsers().size() != 0){
        if(accountsService.getAllAccounts().size() != 0){
            accountsService.deleteAllAccounts();
        }
        usersService.deleteAllUsers();
    }

    user = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "propaneCache");
    usersService.postUser(user);
    }

    @AfterEach
    void unpopulateDB(){
        if (usersService.getAllUsers().size() != 0){
            if(accountsService.getAllAccounts().size() != 0){
                accountsService.deleteAllAccounts();
            }
            usersService.deleteAllUsers();
        }
        user = null;
    }
    @Test
    void getAllUsers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/users");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(user.toString(), result.getResponse().getContentAsString());

    }

    @Test
    void getUserById() {
    }

    @Test
    void postUser() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getUserByUsername() {
    }

//    @TestConfiguration
//    static class TestConfig{
//        @Bean
//        public UsersService usersService(){
//            @Autowired
//            return new UsersService()
//        }
//    }
}