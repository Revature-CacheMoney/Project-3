/**
 * Unit testing of the User class.
 * Authors: David Alvarado, Brandon Perrien,
 *          Jeremiah Smith, Alvin Frierson,
 *          Trevor Hughes, Maja Wirkijowska,
 *          Ahmad Rawashdeh, Ibrahima Diallo,
 *          Brian Gardner, Jeffrey Lor,
 *          Mark Young.
 *
 */
package com.revature.cachemoney.backend.beans.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    /**
     *
     * The following test methods test the getters and setters
     * of a User by creating a user instance and:
     *  - Altering the values to check setters
     *  - Adding new values to then retrieve them with getters
     *
     * */
    @Test
    void setUserId() {
        User user = new User("user", "model", "email@gmail.com", "password", "userModel");
        user.setUserId(1);
        assertEquals(1, user.getUserId());
    }

    @Test
    void setFirstName() {
        User user = new User("user", "model", "email@gmail.com", "password", "usrModel");
        user.setFirstName("newFirstName");
        assertNotEquals("user", user.getFirstName());
        assertEquals("newFirstName", user.getFirstName());
    }

    @Test
    void setLastName() {
        User user = new User("user", "model", "email@gmail.com", "password", "usrModel");
        user.setLastName("newLastName");
        assertNotEquals("model", user.getLastName());
        assertEquals("newLastName", user.getLastName());

    }

    @Test
    void setEmail() {
        User user = new User("user", "model", "email@gmail.com", "password", "usrModel");
        user.setEmail("newEmail@gmail.com");
        assertNotEquals("email@gmail.com", user.getEmail());
        assertEquals("newEmail@gmail.com", user.getEmail());
    }

    @Test
    void setUsername() {
        User user = new User("user", "model", "email@gmail.com", "password", "usrModel");
        user.setUsername("newUsrModel");
        assertNotEquals("usrModel", user.getUsername());
        assertEquals("newUsrModel", user.getUsername());
    }

    @Test
    void setPassword() {
        User user = new User("user", "model", "email@gmail.com", "password", "usrModel");
        user.setPassword("newPassword");
        assertNotEquals("password", user.getPassword());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void getUserId() {
        User user = new User();
        assertNull(user.getUserId());
        user.setUserId(1);
        assertNotNull(user.getUserId());
        assertEquals(1, user.getUserId());

    }

    @Test
    void getFirstName() {
        User user = new User();
        assertNull(user.getFirstName());
        user.setFirstName("firstname");
        assertNotNull(user.getFirstName());
        assertEquals("firstname", user.getFirstName());

    }

    @Test
    void getLastName() {
        User user = new User();
        assertNull(user.getLastName());
        user.setLastName("lastname");
        assertNotNull(user.getLastName());
        assertEquals("lastname", user.getLastName());
    }

    @Test
    void getEmail() {
        User user = new User();
        assertNull(user.getEmail());
        user.setEmail("email@gmail.com");
        assertNotNull(user.getEmail());
        assertEquals("email@gmail.com", user.getEmail());

    }

    @Test
    void getUsername() {
        User user = new User();
        assertNull(user.getUsername());
        user.setUsername("username");
        assertNotNull(user.getUsername());
        assertEquals("username", user.getUsername());
    }

    @Test
    void getPassword() {
        User user = new User();
        assertNull(user.getPassword());
        user.setPassword("password");
        assertNotNull(user.getPassword());
        assertEquals("password", user.getPassword());
    }


    @Test
    void testToString() {
        User user = new User();
        String expectedString = "User(userId=null, firstName=null, lastName=null, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setUserId(1);
        expectedString = "User(userId=1, firstName=null, lastName=null, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setFirstName("firstname");
        expectedString = "User(userId=1, firstName=firstname, lastName=null, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setLastName("lastname");
        expectedString = "User(userId=1, firstName=firstname, lastName=lastname, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setEmail("email@gmail.com");
        expectedString = "User(userId=1, firstName=firstname, lastName=lastname, email=email@gmail.com, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setUsername("username");
        expectedString = "User(userId=1, firstName=firstname, lastName=lastname, email=email@gmail.com, username=username, password=null)";
        assertEquals(expectedString, user.toString());
        user.setPassword("password");
        expectedString = "User(userId=1, firstName=firstname, lastName=lastname, email=email@gmail.com, username=username, password=password)";
        assertEquals(expectedString, user.toString());



    }
}