package com.revature.cachemoney.backend.beans.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;

import com.revature.cachemoney.backend.beans.security.TotpManager;
import com.revature.cachemoney.backend.beans.utils.EmailUtil;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service layer for User requests.
 * 
 * @author Version 1 (Alvin Frierson)
 *         Version 2 (Phillip Vo, Josue Rodriguez, Prakash, Maikel Vera)
 */
@Service
public class UserService {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;

    private final PasswordEncoder passwordEncoder;

    private final TotpManager totpManager;

    private final String emailRegEx = "^[a-zA-Z0-9._-]+@{1}[a-zA-Z0-9-_]+[.]{1}[a-zA-Z0-9]+[a-zA-Z_.-]*$";
    private final String nameRegEx = "^[a-zA-Z][a-zA-Z' -]+[a-zA-Z]$";
    private final String usernameRegEx = "^[a-zA-Z0-9@~._-]{8,}$";
    private final String passwordRegEx = "^[a-zA-Z0-9@^%$#/\\,;|~._-]{8,50}$";

    @Autowired
    public UserService(UserRepo userRepo, AccountRepo accountRepo,
                       PasswordEncoder passwordEncoder, TotpManager totpManager) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
        this.totpManager = totpManager;
    }

    /**
     * Service method to GET *ALL* Users.
     * 
     * @return List of Users
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Service method to GET a User by ID.
     * 
     * @param userId of User to find
     * @return User
     */
    public Optional<User> getUserById(Integer userId) {
        return userRepo.findById(userId);
    }

    /**
     * Service method to POST a User.
     * 
     * @param user of User to save
     * @return (true | false) if the User is saved
     */
    public Boolean postUser(User user) throws QrGenerationException {
        // verify the User's credentials
        if (areCredentialsValid(user)) {
            // encodes the password for database storage
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // changing to lowercase so that two usernames that are the same with different cases won't both be accepted
            user.setEmail(user.getEmail().toLowerCase());
            user.setUsername(user.getUsername().toLowerCase());

            if(user.isMfa()) {
                user.setSecret(totpManager.generateSecret());
                user.setQrImageUri(totpManager.getUriForImage(user.getUsername(), user.getSecret()));
            }

            // save the user in the database
            userRepo.save(user);

            // inform successful result
            //EmailUtil.getInstance().sendEmail(user.getEmail(), "Account Created", "Welcome to CacheMoney!");
            return true;
        }

        // fail by default
        return false;
    }

    // DELETE a user by ID
    public Boolean deleteUserById(Integer id) {
        try{
            userRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * Service method to GET a User by username.
     * 
     * @param user containing at least username & password
     * @return User object with username
     */
    public User getUserByUsername(User user) throws EntityNotFoundException {

        // fail if the username or password is null
        if (user.getUsername() != null && user.getPassword() != null) {

            // verify the username matches


            ExampleMatcher em = ExampleMatcher.matching()
                    .withIgnorePaths("user_id", "first_name", "last_name", "email", "accounts", "password", "mfa", "secret")
                    .withMatcher("username", ignoreCase());

            // search for the User in the database
            Example<User> example = Example.of(user, em);

            // does the User exist?
            try {
                //if (userRepo.exists(example)) {
                    // get the actual User
                    User optionalUser = userRepo.findOne(example).orElseThrow(null);

                    // password checking
                    if (passwordEncoder.matches(user.getPassword(), optionalUser.getPassword())) {
                        optionalUser.setPassword("");
                        return optionalUser;
                    }
                //}
            } catch (RuntimeException e) {
                throw new EntityNotFoundException("User with username: "+user.getUsername()+" isn't in the DataBase.");
            }
        }

        return null;
    }

    public Boolean verify(Integer userId, String code) {

        try {

            User user = userRepo.findById(userId).orElseThrow(null);

            return totpManager.verifyCode(code, user.getSecret());


        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Service method to GET Accounts associated with a User's ID
     * 
     * @param userId of associated User
     * @return List of Accounts associated with User's ID
     */
    public List<Account> getAccountsByUserId(Integer userId) {
        return accountRepo.findByUser(userRepo.getById(userId));
    }

    /**
     * Helper function to validate login credentials.
     * 
     * @param user to check for valid credentials
     * @return (true | false) based on login status
     */
    private Boolean areCredentialsValid(User user) {
        // fail if any fields are null
        if (user.getFirstName() == null || user.getLastName() == null ||
                user.getEmail() == null || user.getUsername() == null ||
                user.getPassword() == null) {
            return false;
        }

        // fail if first name & last name are empty
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty()) {
            return false;
        }

        // validify email
        Pattern emailPattern = Pattern.compile(emailRegEx);
        Matcher emailMatcher = emailPattern.matcher(user.getEmail());
        Boolean emailValidity = emailMatcher.matches();

        // validify first name & last name
        Pattern namePattern = Pattern.compile(nameRegEx);
        Matcher nameMatcher = namePattern.matcher(user.getFirstName() + " " + user.getLastName());
        Boolean nameValidity = nameMatcher.matches();

        // validify username
        Pattern usernamePattern = Pattern.compile(usernameRegEx);
        Matcher usernameMatcher = usernamePattern.matcher(user.getUsername());
        Boolean usernameValidity = usernameMatcher.matches();

        // validify password
        Pattern passwordPattern = Pattern.compile(passwordRegEx);
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
        Boolean passwordValidity = passwordMatcher.matches();

        // succeed only if all fields are valid
        return emailValidity && nameValidity && usernameValidity && passwordValidity;
    }

    /**
     *
     * ******************STRICTLY FOR TESTING PURPOSES*********************
     *
     */
    public void deleteAllUsers() {
        userRepo.deleteAll();
    }
}
