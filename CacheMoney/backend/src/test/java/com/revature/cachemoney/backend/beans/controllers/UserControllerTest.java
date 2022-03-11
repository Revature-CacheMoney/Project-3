package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.services.UsersService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UsersService usersService;
    @Autowired
    private MockMvc mockMvc;

    private List<User> validUsers;
    @BeforeEach
    void populateList(){
        validUsers = new LinkedList<>();
        User user = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "propanemoney");
        validUsers.add(user);
    }

    @AfterEach
    void depopulateList(){
        validUsers = null;
    }

    @Test
    void getAllUsers() throws Exception {
        when(usersService.getAllUsers()).thenReturn(validUsers);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Hank"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Hill"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("hank.hill@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("abcd1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("propanemoney"));


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
}