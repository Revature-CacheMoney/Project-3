package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles HTTP requests for users
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    // GET a user by ID
    @GetMapping(value = "/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
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
    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        usersService.deleteUserById(id);
    }

    /**
     * GET a User by email.
     * Emails are unique and should not cause conflicting results.
     * 
     * @param email
     * @return the User based on email
     */
    @GetMapping(value = "/email")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return usersService.getUserByEmail(email);
    }

    /**
     * GET a User by email.
     * Emails are unique and should not cause conflicting results.
     *
     * @param user
     * @return the User based on email
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getUserByUsername(@RequestBody User user) throws JsonProcessingException {
        // has internal checking to see if user is valid
        User tempUser = usersService.getUserByUsername(user);

        // make sure the user is valid
        if (tempUser != null) {
            // create the response headers
            HttpHeaders headers = new HttpHeaders();

            // instance of JWT
            JwtUtil jwtUtil = new JwtUtil();

            // add the JWT to the headers
            headers.set("JWT", jwtUtil.generateToken(tempUser.getUsername()));

            // mapper to write object to json
            ObjectMapper mapper = new ObjectMapper();

            // write the headers & object into the response
            return ResponseEntity.ok().headers(headers).body(mapper.writeValueAsString(tempUser));
        }

        // indicate bad request
        return ResponseEntity.badRequest().build();
    }
}
