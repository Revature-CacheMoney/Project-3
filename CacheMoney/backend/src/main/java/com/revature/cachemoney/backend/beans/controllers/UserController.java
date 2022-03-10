package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle requests related to Users.
 * 
 * @author Brian Gardner, Cody Gonsowski, & Jeffrey Lor
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UsersService usersService;
    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    @Autowired
    public UserController(UsersService usersService, JwtUtil jwtUtil, ObjectMapper mapper) {
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    // GET all users
    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    // GET a user by ID
    @GetMapping
    @RequireJwt
    public ResponseEntity<String> getUserById(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {

        return ResponseEntity.ok().body(mapper.writeValueAsString(usersService.getUserById(userId)));
    }

    /**
     * POST a User.
     * 
     * @param user
     * @return true/false based on registration status
     */
    @PostMapping
    public Boolean postUser(@RequestBody User user) {
        return usersService.postUser(user);
    }

    // DELETE a user by ID
    @DeleteMapping
    @RequireJwt
    public ResponseEntity<String> deleteUserById(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {

        usersService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    // login
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody User user) throws JsonProcessingException {
        // has internal checking to see if user is valid
        User tempUser = usersService.getUserByUsername(user);

        // make sure the user is valid
        if (tempUser != null) {
            // create the response headers
            HttpHeaders headers = new HttpHeaders();

            // add the JWT to the headers
            headers.set("JWT", jwtUtil.generateToken(tempUser.getUser_id()));

            // write the headers & object into the response
            return ResponseEntity.ok().headers(headers).body(mapper.writeValueAsString(tempUser));
        }

        // indicate bad request
        return ResponseEntity.badRequest().build();
    }

    /**
     * Get all accounts associated with a particular user ID.
     * 
     * @param token  for current session
     * @param userId for current user
     * @return List of Accounts associated with a particular user
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/accounts")
    @RequireJwt
    public ResponseEntity<String> getAccountsByUserId(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {

        return ResponseEntity.ok().body(mapper.writeValueAsString(usersService.getAccountsByUserId(userId)));
    }
}
