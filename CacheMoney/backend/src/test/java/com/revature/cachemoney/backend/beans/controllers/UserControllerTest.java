package com.revature.cachemoney.backend.beans.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private JwtUtil jwtUtil;
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private final UserController userController = new UserController(userService, jwtUtil, mapper);

    @Autowired
    private MockMvc mockMvc;

    private Integer[] testUserIds;
    private User[] testUsers;
    private String[] testTokens;
    private User[] loginUsers;
    private List<Account> accountList;


    @BeforeEach
    void populateData(){
        testUserIds = new Integer[2];
        testUserIds[0] = 1;
        testUserIds[1] = 2;

        testUsers = new User[2];
        testUsers[0] = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "hankhill");
        testUsers[1] = new User("Peggy", "Hill", "peggy.hill@gmail.com", "efgh5678", "peggyhill");

        testTokens = new String[2];
        testTokens[0] = "hankToken";
        testTokens[1] = "peggyToken";

        loginUsers = new User[3];
        loginUsers[0] = new User(null, null, null, "abcd1234", "hankhill");
        loginUsers[1] = new User(null, null, null, "efgh5678", "peggy123"); //invalid username (for peggy in testUsers[1]
        loginUsers[2] = new User(null, null, null, "peggy", "hankhill"); // invalid password for hank in testUsers[0]

        accountList = new LinkedList<>();
        accountList.add(new Account("checking"));
        accountList.add(new Account("savings", "Rainy day fund"));
    }
    @AfterEach
    void depopulateData(){
        testUserIds = null;
        testUsers = null;
        testTokens = null;
        accountList = null;

    }

    @Test
    void getAllUsers() {

    }

    @Test
    void getUserById() throws Exception {

//        when(userService.getUserById(testUserIds[0])).thenReturn(Optional.of(testUsers[0]));
//        when(userService.getUserById(testUserIds[1])).thenReturn(Optional.of(testUsers[1]));
//        when(jwtUtil.generateToken(testUserIds[0])).thenReturn(testTokens[0]);
//        when(jwtUtil.generateToken(testUserIds[1])).thenReturn(testTokens[1]);
//
//        List<ResponseEntity<String>> actualResponses = new ArrayList<>();
//        actualResponses.add(userController.getUserById(testTokens[0], testUserIds[0]));
//        actualResponses.add(userController.getUserById(testTokens[0], testUserIds[1]));
//        actualResponses.add(userController.getUserById(testTokens[1], testUserIds[0]));
//
//        List<String> testUsersJson = new ArrayList<>();
//        testUsersJson.add(mapper.writeValueAsString(testUsers[0]));
//        testUsersJson.add(mapper.writeValueAsString(testUsers[1]));
//
//        assertEquals(HttpStatus.OK, actualResponses.get(0).getStatusCode());
//        assertEquals(testUsersJson.get(0), actualResponses.get(0).getBody());

//        assertEquals(HttpStatus.BAD_REQUEST, testResponses.get(1).getStatusCode()); // Http status should not be OK - AspectJwt not being reached
//        assertNotEquals(testUsersJson.get(0), testResponses.get(1).getBody());
//        assertNotEquals(testUsersJson.get(1), testResponses.get(1).getBody());

        testUsers[0].setUserId(1);
        when(userService.getUserById(testUsers[0].getUserId())).thenReturn(Optional.of(testUsers[0]));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .header("token", testTokens[0])
                .header("userId", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("Hank"));


    }

//
    @Test
    void deleteUserById() {
        

    }
//
    @Test
    void login() throws JsonProcessingException {

        testUsers[0].setUserId(1);
        when(userService.getUserByUsername(loginUsers[0])).thenReturn(testUsers[0]);
        when(jwtUtil.generateToken(testUsers[0].getUserId())).thenReturn(testTokens[0]);
        ResponseEntity<String> response = userController.login(loginUsers[0]);

        assertEquals(mapper.writeValueAsString(testUsers[0]), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //to retrieve a specific value from a header, use ,getFirst(your-header-name)
        assertEquals(testTokens[0], response.getHeaders().getFirst("JWT"));

        // incorrect username
        when(userService.getUserByUsername(loginUsers[1])).thenReturn(null);
        ResponseEntity<String> response1 = userController.login(loginUsers[1]);
        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());

        // incorrect password
        when(userService.getUserByUsername(loginUsers[2])).thenReturn(null);
        ResponseEntity<String> response2 = userController.login(loginUsers[2]);
        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());



    }
//
    @Test
    void getAccountsByUserId() throws JsonProcessingException {
        //set user id to 1
        testUsers[0].setUserId(1);
        when(userService.getAccountsByUserId(testUsers[0].getUserId())).thenReturn(accountList);
        ResponseEntity<String> response = userController.getAccountsByUserId(testTokens[0], 1);
        assertEquals(mapper.writeValueAsString(accountList), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void postUser() throws Exception {
//        when(userService.postUser(testUsers[0])).thenReturn(true);
//        assertTrue(userController.postUser(testUsers[0]));
//        when(userService.postUser(loginUsers[0])).thenReturn(false);
//        assertFalse(userController.postUser(loginUsers[0]));

//        when(userService.postUser(testUsers[0])).thenReturn(true);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")).andExpect();



    }

}