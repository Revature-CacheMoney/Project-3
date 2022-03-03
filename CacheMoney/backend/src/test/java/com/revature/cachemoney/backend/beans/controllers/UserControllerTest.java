package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepo userRepo;

    @Test
    void getAllUsers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/users/allusers");
        MvcResult result = mvc.perform(request).andReturn();
//        when(userRepo.findAll()).thenReturn(Stream.of(new User("Brandon", "Perrien", "email",
//                "password", "username"), new User("first", "last",
//                "email", "password", "uname")).collect(Collectors.toList()));
        assertEquals(2, result.getResponse().getContentLength());
    }

    @Test
    void getUserById() {
    }

    @Test
    void postUser() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getUserByUsername() {
    }
}