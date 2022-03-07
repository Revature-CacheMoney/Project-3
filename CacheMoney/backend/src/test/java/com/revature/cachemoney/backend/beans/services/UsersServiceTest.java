/**
 * Unit testing of the UserService class.
 * Authors: David Alvarado, Brandon Perrien,
 *          Jeremiah Smith, Alvin Frierson,
 *          Trevor Hughes, Maja Wirkijowska,
 *          Ahmad Rawashdeh, Ibrahima Diallo.
 *
 */


package com.revature.cachemoney.backend.beans.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.revature.cachemoney.backend.beans.models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    private static List<User> validUserList;
    private static List<User> nullValueUserList;
    private static List<User> emptyStringUserList;

    @BeforeAll
    public static void dataInit(){
        validUserList = new LinkedList<>();
        nullValueUserList = new LinkedList<>();
        emptyStringUserList = new LinkedList<>();

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

    @Test
    @Order(3)
    void getAllUsers() {

        // To run this test, you must know how many users are in your db and pass that
        // as an expected value argument.
        assertEquals(5, usersService.getAllUsers().size());
    }

    @Test
    @Order(4)
    void getUserById() {
        // Check to see if we are able to successfully retrieve 1 user from database
        // using user_id.
        for (int i = 1; i < 6; i++) {
            assertTrue(usersService.getUserById(i).isPresent());
        }
        // Check to see that a user is not retrieved with an incorrect user_id value.
        for (int i = 6; i < 16; i++) {
            assertFalse(usersService.getUserById(i).isPresent());
        }
    }

    @Test
    @Order(1)
    void postUser() {
        // This test checks if user is successfully persisted into database because it
        // has the right credentials
        // the correct format.
        // Make sure that the values here are not already used in the database.
        // The values that must be unique are:
        // email
        // username

        for (User validUser : validUserList){
            assertEquals(true, usersService.postUser(validUser));
        }

        for (User nullFieldUser : nullValueUserList){
            assertEquals(false, usersService.postUser(nullFieldUser));
        }

        for (User emptyStringField : emptyStringUserList){
            assertEquals(false, usersService.postUser(emptyStringField));
        }

    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    @Order(2)
    void getUserByUsername() {
        // Tests if fields other than username or password are required for successful
        // login. (They are not required)
        User invalidUser = new User(null,null,null,null,null);
        int userID = 1;
        for (User validUser : validUserList){
            validUser.setUser_id(userID);
            assertEquals(validUser.toString(), usersService.getUserByUsername(validUser).toString());
            userID++;
        }

//        for (User nullFieldUser : nullValueUserList){
//            assertEquals("{ \"result\": false }", usersService.postUser(nullFieldUser));
//        }
//
//        for (User emptyStringField : emptyStringUserList){
//            assertEquals("{ \"result\": false }", usersService.postUser(emptyStringField));
//        }

    }
}