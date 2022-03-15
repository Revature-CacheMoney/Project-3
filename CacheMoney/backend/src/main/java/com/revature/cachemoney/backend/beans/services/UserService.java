package com.revature.cachemoney.backend.beans.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import com.revature.cachemoney.backend.beans.security.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;

    private final SecurityConfig passwordEncoder;

    private final String emailRegEx = "^[a-zA-Z0-9._-]+@{1}[a-zA-Z0-9-_]+[.]{1}[a-zA-Z0-9]+[a-zA-Z_.-]*$";
    private final String nameRegEx = "^[a-zA-Z][a-zA-Z' -]+[a-zA-Z]$";
    private final String usernameRegEx = "^[a-zA-Z0-9@~._-]{8,}$";
    private final String passwordRegEx = "^[a-zA-Z0-9@^%$#/\\,;|~._-]{8,50}$";

    @Autowired
    public UserService(UserRepo userRepo, AccountRepo accountRepo, SecurityConfig passwordEncoder) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // GET all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // GET a user by ID
    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }

    // POST a new user
    public Boolean postUser(User user) {
        if (areCredentialsValid(user)) {
            try {
                // encodes the password for database storage
                user.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));

                // changing to lowercase so that two usernames that are the same with different cases won't both be accepted
                user.setEmail(user.getEmail().toLowerCase());
                user.setUsername(user.getUsername().toLowerCase());

                // save the user in the database
                userRepo.save(user);
            } catch (Exception e) {
                // inform failed result
                System.out.println("failed due to exception");
                return false;
            }
            // inform successful result
            return true;
        } else {
            System.out.println("invalid credentials");
            return false;
        }
    }

    // DELETE a user by ID
    public void deleteUserById(Integer id) {
        userRepo.deleteById(id);
    }

    // login verification
    public User getUserByUsername(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return null;
        }

        ExampleMatcher em = ExampleMatcher.matching()
                .withIgnorePaths("user_id", "first_name", "last_name", "email", "accounts", "password")
                .withMatcher("username", ignoreCase());

        Example<User> example = Example.of(user, em);

        if (userRepo.exists(example)) {
            Optional<User> optionalUser = userRepo.findOne(example);

            // password checking
            if (passwordEncoder.passwordEncoder().matches(user.getPassword(), optionalUser.get().getPassword())) {
                return optionalUser.get();
            }
        }

        return null;
    }

    /**
     * Finds all accounts associated with a user ID.
     * 
     * @param userId ID associated with a user
     * @return list of accounts associated with a particular user's ID
     */
    public List<Account> getAccountsByUserId(Integer userId) {
        return accountRepo.findByUser(userRepo.getById(userId));
    }

    // credential validator for user registration
    public boolean areCredentialsValid(User user) {
        if (user.getFirstName() == null || user.getLastName() == null ||
                user.getEmail() == null || user.getUsername() == null ||
                user.getPassword() == null) {
            return false;
        }
        if (user.getFirstName() == "" || user.getLastName() == "") {
            return false;
        }
        Pattern emailPattern = Pattern.compile(emailRegEx);
        Matcher emailMatcher = emailPattern.matcher(user.getEmail());
        boolean emailValidity = emailMatcher.matches();

        Pattern namePattern = Pattern.compile(nameRegEx);
        Matcher nameMatcher = namePattern.matcher(user.getFirstName() + " " + user.getLastName());
        boolean nameValidity = nameMatcher.matches();

        Pattern usernamePattern = Pattern.compile(usernameRegEx);
        Matcher usernameMatcher = usernamePattern.matcher(user.getUsername());
        boolean usernameValidity = usernameMatcher.matches();

        Pattern passwordPattern = Pattern.compile(passwordRegEx);
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
        boolean passwordValidity = passwordMatcher.matches();

        return emailValidity && nameValidity && usernameValidity && passwordValidity;
    }

    /**
     *
     * ******************STRICTLY FOR TESTING PURPOSES*********************
     *
     * */
    public void deleteAllUsers(){
        userRepo.deleteAll();
    }
}
