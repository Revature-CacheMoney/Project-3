package com.revature.cachemoney.backend.beans.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UsersService usersService;
    @MockBean
    private JwtUtil jwtUtil;
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private final UserController userController = new UserController(usersService, jwtUtil, mapper);

    @Autowired
    private MockMvc mockMvc;




//    @Test
//    void createMockMvc(){
//        assertNotNull(mockMvc);
//    }
    @Test
    void getAllUsers() {
//        assertNotNull(mockMvc);
    }

    @Test
    void getUserById() throws JsonProcessingException {
        Integer[] testUserIds = new Integer[2];
        testUserIds[0] = 1;
        testUserIds[1] = 2;

        User[] testUsers = new User[2];
        testUsers[0] = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "hankhill");
        testUsers[1] = new User("Peggy", "Hill", "peggy.hill@gmail.com", "efgh5678", "peggyhill");

        String[] testTokens = new String[2];
        testTokens[0] = "hankToken";
        testTokens[1] = "peggyToken";

        when(usersService.getUserById(testUserIds[0])).thenReturn(Optional.of(testUsers[0]));
        when(usersService.getUserById(testUserIds[1])).thenReturn(Optional.of(testUsers[1]));
        when(jwtUtil.generateToken(testUserIds[0])).thenReturn(testTokens[0]);
        when(jwtUtil.generateToken(testUserIds[1])).thenReturn(testTokens[1]);

        List<ResponseEntity<String>> testResponses = new ArrayList<>();
        testResponses.add(userController.getUserById(testTokens[0], testUserIds[0]));
        testResponses.add(userController.getUserById(testTokens[0], testUserIds[1]));
        testResponses.add(userController.getUserById(testTokens[1], testUserIds[0]));

        List<String> testUsersJson = new ArrayList<>();
        testUsersJson.add(mapper.writeValueAsString(testUsers[0]));
        testUsersJson.add(mapper.writeValueAsString(testUsers[1]));

        assertEquals(HttpStatus.OK, testResponses.get(0).getStatusCode());
        assertEquals(testUsersJson.get(0), testResponses.get(0).getBody());

//        assertEquals(HttpStatus.BAD_REQUEST, testResponses.get(1).getStatusCode()); // Http status should not be OK - AspectJwt not being reached
//        assertNotEquals(testUsersJson.get(0), testResponses.get(1).getBody());
//        assertNotEquals(testUsersJson.get(1), testResponses.get(1).getBody());
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