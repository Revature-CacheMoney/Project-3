package com.revature.cachemoney.backend.beans.services;



import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.caseSensitive;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class UsersService {
    private final UserRepo userRepository;

    @Autowired
    public UsersService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    // GET a user by ID
    public Optional<User> getUserById(Integer id){

        return userRepository.findById(id);
    }

    // POST a new user
    public String postUser(User user) {
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
    public void deleteUserById(Integer id){

        userRepository.deleteById(id);
    }

    // GET user by email address
    public Optional<User> getUserByEmail(String email) {
        System.out.println(email + "<<<<<");
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(User user) {

        if ( user.getUsername() == null || user.getPassword() == null){
            return user;
        }

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
