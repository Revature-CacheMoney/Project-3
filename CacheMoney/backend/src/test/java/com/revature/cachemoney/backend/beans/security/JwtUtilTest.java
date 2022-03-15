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

    @Test
    void generateToken() {
        assertNotNull(jwtUtil.generateToken(1));
    }

    @Test
    void validateToken() {
        assertFalse(jwtUtil.validateToken("",null));
    }
}