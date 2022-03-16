/**
 * Unit testing of the JwtUtl class.
 * Authors: David Alvarado, Brandon Perrien,
 *          Jeremiah Smith, Alvin Frierson,
 *          Trevor Hughes, Maja Wirkijowska,
 *          Ahmad Rawashdeh, Ibrahima Diallo,
 *          Brian Gardner, Jeffrey Lor,
 *          Mark Young.
 *
 */
package com.revature.cachemoney.backend.beans.security;

import com.auth0.jwt.JWTVerifier;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    /**
     * Method test checks that a token
     * is created when passed an integer.
     *
     * */
    @Test
    void generateToken() {
        assertNotNull(jwtUtil.generateToken(1));
    }

    /**
     * Method checks that an invalid token
     * returns false
     *
     * */
    @Test
    void validateToken() {
        assertFalse(jwtUtil.validateToken("",null));
    }
}