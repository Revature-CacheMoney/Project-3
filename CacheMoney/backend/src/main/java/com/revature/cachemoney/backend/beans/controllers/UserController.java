package com.revature.cachemoney.backend.beans.controllers;

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
 * @author Ibrahima Diallo, Brian Gardner, Cody Gonsowski, & Jeffrey Lor
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
    @GetMapping
    @RequireJwt
    public ResponseEntity<String> getAllUsers(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {
        return ResponseEntity.ok().body(mapper.writeValueAsString(usersService.getAllUsers()));
    }

    // GET a user by ID
    @GetMapping(value = "/id")
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
    @PostMapping()
    public Boolean postUser(@RequestBody User user) {
        return usersService.postUser(user);
    }

    // DELETE a user by ID
    @DeleteMapping(value = "/id")
    @RequireJwt
    public void deleteUserById(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId) {
        usersService.deleteUserById(userId);
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
}
