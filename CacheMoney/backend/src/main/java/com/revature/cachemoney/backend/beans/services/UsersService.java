package com.revature.cachemoney.backend.beans.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import com.revature.cachemoney.backend.beans.security.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UserRepo userRepository;

    private final SecurityConfig passwordEncoder;

    private final String emailRegEx = "^[a-zA-Z0-9._-]+@{1}[a-zA-Z0-9-_]+[.]{1}[a-zA-Z0-9]+[a-zA-Z_.-]*$";
    private final String nameRegEx = "^[a-zA-Z -]+$";
    private final String usernameRegEx = "^[a-zA-Z0-9@~._-]{8,}$";
    private final String passwordRegEx = "^[a-zA-Z0-9@^%$#/\\,;|~._-]{8,50}$";

    @Autowired
    public UsersService(UserRepo userRepository, SecurityConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET a user by ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // POST a new user
    public Boolean postUser(User user) {
        if (areCredentialsValid(user)) {
            try {
                // encodes the password for database storage
                user.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));

                // save the user in the database
                userRepository.save(user);
            } catch (Exception e) {
                // inform failed result
                return false;
            }
            // inform successful result
            return true;
        } else {
            return false;
        }
    }

    // DELETE a user by ID
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
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

        if (userRepository.exists(example)) {
            Optional<User> optionalUser = userRepository.findOne(example);

            // password checking
            if (passwordEncoder.passwordEncoder().matches(user.getPassword(), optionalUser.get().getPassword())) {
                return optionalUser.get();
            }
        }

        return user;
    }

    // credential validator for user registration
    public boolean areCredentialsValid(User user) {
        if (user.getFirstName() == null || user.getLastName() == null ||
                user.getEmail() == null || user.getUsername() == null ||
                user.getPassword() == null) {
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

}
