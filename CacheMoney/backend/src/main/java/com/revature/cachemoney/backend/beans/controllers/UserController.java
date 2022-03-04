package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    // GET a user by ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@RequestBody User user) {
        return usersService.getUserByUsername(user);
    }
}
