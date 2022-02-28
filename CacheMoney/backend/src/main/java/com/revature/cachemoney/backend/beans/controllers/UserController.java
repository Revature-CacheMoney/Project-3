package com.revature.cachemoney.backend.beans.controllers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.caseSensitive;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


@RestController
@RequestMapping("/users")
public class UserController {
	private final UserRepo userRepository;

    @Autowired
    public UserController(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    // GET a user by ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable Integer id){

        return userRepository.findById(id);
    }

    /**
     * POST a User.
     * 
     * @param user
     * @return String containing information regarding success or failure.
     */
    @PostMapping()
    public String postUser(@RequestBody User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("User cannot be registered.");

            // inform failed result
            return "User cannot be registered. The email you entered ("
                    + user.getEmail() + ") may already be in use.";
        }

        // inform successful result
        return "User " + user.getFirstName() + user.getLastName() +
                " (ID = " + user.getUser_id() +
                ") registered successfully!";
    }

    // DELETE a user by ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
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
    	System.out.println(email + "<<<<<");
        return userRepository.findByEmail(email);
    }

    /**
     * GET a User by email.
     * Emails are unique and should not cause conflicting results.
     *
     * @param user
     * @return the User based on email
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@RequestBody User user) {
        ExampleMatcher em = ExampleMatcher.matching().withIgnorePaths("user_id","first_name", "last_name", "email", "accounts")
                .withMatcher("username", ignoreCase()).withMatcher("password", caseSensitive());

        Example<User> example = Example.of(user, em);

        if (userRepository.exists(example)){
            Optional<User> optionalUser = userRepository.findOne(example);
            return optionalUser.get();
        }
        return user;

    }
}
