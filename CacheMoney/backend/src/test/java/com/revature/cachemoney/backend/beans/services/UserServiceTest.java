/**
 * Unit testing of the UserService class.
 * Authors: David Alvarado, Brandon Perrien,
 *          Jeremiah Smith, Alvin Frierson,
 *          Trevor Hughes, Maja Wirkijowska,
 *          Ahmad Rawashdeh, Ibrahima Diallo,
 *          Brian Gardner.
 *
 */


package com.revature.cachemoney.backend.beans.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.UserRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    private static List<User> validUserList;
    private static List<User> nullValueUserList;
    private static List<User> emptyStringUserList;

    @BeforeAll
    public static void dataInit(){
        nullValueUserList = new LinkedList<>();
        emptyStringUserList = new LinkedList<>();


        User nullFirstName = new User(null, "Simpson", "homer.simpson@gmail.com", "abcd1234", "hmmdonut");
        User nullLastName = new User("Lisa", null, "lisa.simpson@gmail.com", "abcd1234", "lisa_blues");
        User nullEmail = new User("Bart", "Simpson", null, "abcd1234", "eatmyshorts");
        User nullPassword = new User("Marge", "Simpson", "marge.simpson@gmail.com", null, "MmMmMmMm");
        User nullUsername = new User("Maggie", "Simpson", "maggie.simpson@gmail.com", "abcd1234", null);
        nullValueUserList.add(nullFirstName);
        nullValueUserList.add(nullLastName);
        nullValueUserList.add(nullEmail);
        nullValueUserList.add(nullPassword);
        nullValueUserList.add(nullUsername);

        User emptyFirstName = new User("", "Griffin", "peter.griffin@gmail.com", "abcd1234", "birdistheword");
        User emptyLastName = new User("Lois", "", "lois.griffin@gmail.com", "abcd1234", "therealest");
        User emptyEmail = new User("Stewie", "Griffin", "", "abcd1234", "worlddomination");
        User emptyPassword = new User("Chris", "Griffin", "chris.griffin@gmail.com", "", "crayon123");
        User emptyUsername = new User("Brian", "Griffin", "brian.griffin@gmail.com", "abcd1234", "");
        emptyStringUserList.add(emptyFirstName);
        emptyStringUserList.add(emptyLastName);
        emptyStringUserList.add(emptyEmail);
        emptyStringUserList.add(emptyPassword);
        emptyStringUserList.add(emptyUsername);


    }
    @BeforeEach
    void setupDB(){
        validUserList = new LinkedList<>();
        User user1 = new User("Hank", "Hill", "hank.hill@gmail.com", "abcd1234", "propanemoney");
        User user2 = new User("Peggy", "Hill", "peggy.hill@gmail.com", "abcd1234", "best_sub_teacher");
        User user3 = new User("Bobby", "Hill", "bobby.hill@gmail.com", "abcd1234", "bobbytrill");
        User user4 = new User("Kahn", "Souphanousinphone", "kahn@gmail.com", "abcd1234", "iamnumberone");
        User user5 = new User("Dale", "Gribble", "rusty.shackleford@gmail.com", "abcd1234", "rusty123");
        validUserList.add(user1);
        validUserList.add(user2);
        validUserList.add(user3);
        validUserList.add(user4);
        validUserList.add(user5);


        if (userService.getAllUsers().size() == 0){
            for (User validUser : validUserList){
                userService.postUser(validUser);
            }
        }else{
            userService.deleteAllUsers();
            for (User validUser : validUserList){
                userService.postUser(validUser);
            }
        }
    }
    @AfterEach
    void deleteDBData(){

        userService.deleteAllUsers();
        validUserList = null;

    }

    @Test
    void getAllUsers() {



        // To run this test, you must know how many users are in your db and pass that
        // as an expected value argument.

        assertEquals(5, userService.getAllUsers().size());

    }

    @Test
    void getUserById() {

        // Check to see if we are able to successfully retrieve 1 user from database
        // using user_id.

        List<User> userWithID = userService.getAllUsers();

        for (User currentUser : userWithID){
            assertTrue(userService.getUserById(currentUser.getUserId()).isPresent());
        }
        // Check to see that a user is not retrieved with an incorrect user_id value.

        assertFalse(userService.getUserById(0).isPresent());

    }


    @Test
    void deleteUserById() {


        List<User> userWithID = userService.getAllUsers();

        for (User currentUser : userWithID){
             assertEquals(true, userService.deleteUserById(currentUser.getUserId()));
        }
        assertFalse(userService.deleteUserById(-1));

    }

    @Test
    void getAccountsByUserId(){
        List<Account> account = new LinkedList<>();
        account.add(new Account("checking"));
        account.get(0).setUser(validUserList.get(0));
        accountService.postAccount(account.get(0), validUserList.get(0).getUserId());

        assertEquals(account.toString(),
                userService.getAccountsByUserId(validUserList.get(0).getUserId()).toString());

        accountService.deleteAllAccounts();

    }


    @Test
    void getUserByUsername() {

        // Tests if fields other than username or password are required for successful
        // login. (They are not required)
        User invalidUser = new User(null,null,null,null,null);
        int userID = 1;
        for (User validUser : validUserList){
            assertEquals(validUser.getUsername(), userService.getUserByUsername(validUser).getUsername());
            userID++;
        }
        // test for null username or password values.
        // maybe create a custom method which outputs a string of all objects attributes except for
        // password since the password is hashed after retrieving it from database.



    }

    @Nested
    class TestPostUser{

        @BeforeEach
        void addNewValidUserToList(){
            validUserList = new LinkedList<>();
            User newUser = new User("Louanne", "Platter", "p.platter@gmail.com", "abcd1234", "Pplatter123");
            validUserList.add(newUser);
        }
        @AfterEach
        void deleteNewValidUserToList(){
            validUserList = null;
            userService.deleteAllUsers();
        }

        @Test

        void postUser() {

            // This test checks if user is successfully persisted into database because it
            // has the right credentials
            // the correct format.
            // Make sure that the values here are not already used in the database.
            // The values that must be unique are:
            // email
            // username


            assertEquals(true, userService.postUser(validUserList.get(0)));


            for (User nullFieldUser : nullValueUserList){
                assertEquals(false, userService.postUser(nullFieldUser));
            }

            for (User emptyStringField : emptyStringUserList){
                assertEquals(false, userService.postUser(emptyStringField));
            }


        }


    }

}