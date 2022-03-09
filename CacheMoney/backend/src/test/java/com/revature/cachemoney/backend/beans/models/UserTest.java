package com.revature.cachemoney.backend.beans.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void setUser_id() {
        User user = new User("user", "model", "email@gmail.com", "password", "userModel");
        user.setUser_id(1);
        assertEquals(1, user.getUser_id());
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
    void getUser_id() {
        User user = new User();
        assertNull(user.getUser_id());
        user.setUser_id(1);
        assertNotNull(user.getUser_id());
        assertEquals(1, user.getUser_id());

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
        String expectedString = "User(user_id=null, firstName=null, lastName=null, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setUser_id(1);
        expectedString = "User(user_id=1, firstName=null, lastName=null, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setFirstName("firstname");
        expectedString = "User(user_id=1, firstName=firstname, lastName=null, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setLastName("lastname");
        expectedString = "User(user_id=1, firstName=firstname, lastName=lastname, email=null, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setEmail("email@gmail.com");
        expectedString = "User(user_id=1, firstName=firstname, lastName=lastname, email=email@gmail.com, username=null, password=null)";
        assertEquals(expectedString, user.toString());
        user.setUsername("username");
        expectedString = "User(user_id=1, firstName=firstname, lastName=lastname, email=email@gmail.com, username=username, password=null)";
        assertEquals(expectedString, user.toString());
        user.setPassword("password");
        expectedString = "User(user_id=1, firstName=firstname, lastName=lastname, email=email@gmail.com, username=username, password=password)";
        assertEquals(expectedString, user.toString());



    }
}