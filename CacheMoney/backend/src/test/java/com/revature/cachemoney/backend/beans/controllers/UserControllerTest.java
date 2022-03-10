package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.services.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;



@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UsersService usersService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void createMockMvc(){
        assertNotNull(mockMvc);
    }
    @Test
    void getAllUsers() {
//        assertNotNull(mockMvc);
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
    void login() {
    }

    @Test
    void getAccountsByUserId() {
    }
}