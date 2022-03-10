package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.services.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.ws.Response;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UsersService usersService;
    @Autowired
    private UserController userController;

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
        Integer testUserId = 1;
        User testUser = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "hankhill");
        when(usersService.getUserById(1)).thenReturn(Optional.of(testUser));

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