package com.revature.cachemoney.backend.beans.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.revature.cachemoney.backend.beans.models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getAllUsers() {

        // To run this test, you must know how many users are in your db and pass that
        // as an expected value argument.
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void getUserById() {
        // Check to see if we are able to successfully retrieve 1 user from database
        // using user_id.
        assertTrue(userService.getUserById(1).isPresent());
        // Check to see that a user is not retrieved with an incorrect user_id value.
        assertFalse(userService.getUserById(-1).isPresent());
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
        User user = new User("John", "smith", "newEmail@gmail.com", "abcd1234", "jsmith123");
        assertEquals("{ \"result\": true }", userService.postUser(user));

        // This checks if a field of value null can be persisted to database.
        User userWithoutName = new User(null, "alvarado", "fake1@gmail.com", "abcd1234", "nullName");
        assertEquals("{ \"result\": false }", userService.postUser(userWithoutName));

        // This checks if a field of value empty string can be persisted to database.
        User emptyStringName = new User("bella", "alvarado", "", "abcd1234", "noname78");
        assertEquals("{ \"result\": false }", userService.postUser(emptyStringName));

    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getUserByUsername() {
        // Tests if fields other than username or password are required for successful
        // login. (They are not required)
        User inputUser1 = new User(null, null, null, "abcd1234", "dalvarado12");
        // This checks for successful login
        User outputUser1 = new User("David", "alvarado", "fake1234@gmail.com", "abcd1234", "dalvarado12");
        outputUser1.setUser_id(1);
        assertEquals(outputUser1.toString(), userService.getUserByUsername(inputUser1).toString());

        // this checks for unsuccessful login,incorrect password.
        User inputUser2 = new User(null, null, null, "abcd12345", "dalvarado12");
        User outputUser2 = new User(null, null, null, "abcd12345", "dalvarado12");
        assertEquals(outputUser2.toString(), userService.getUserByUsername(inputUser2).toString());

        // this checks for unsuccessful login, incorrect username.
        User inputUser3 = new User(null, null, null, "abcd1234", "dalvarado11");
        User outputUser3 = new User(null, null, null, "abcd1234", "dalvarado11");
        assertEquals(outputUser3.toString(), userService.getUserByUsername(inputUser3).toString());

    }
}