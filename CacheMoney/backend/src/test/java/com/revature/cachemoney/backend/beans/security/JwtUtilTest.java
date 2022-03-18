package com.revature.cachemoney.backend.beans.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit testing of the JwtUtl class.
 * 
 * @author David Alvarado, Brandon Perrien,
 *         Jeremiah Smith, Alvin Frierson,
 *         Trevor Hughes, Maja Wirkijowska,
 *         Ahmad Rawashdeh, Ibrahima Diallo,
 *         Brian Gardner, Jeffrey Lor,
 *         Mark Young
 */
@SpringBootTest
class JwtUtilTest {
    @Autowired
    JwtUtil jwtUtil;

    /**
     * Method test checks that a token is created when passed an integer.
     */
    @Test
    void generateToken() {
        assertNotNull(jwtUtil.generateToken(1));
    }

    /**
     * Method checks that an invalid token returns false.
     */
    @Test
    void validateToken() {
        assertFalse(jwtUtil.validateToken("", null));
    }
}
