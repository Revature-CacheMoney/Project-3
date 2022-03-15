package com.revature.cachemoney.backend.beans.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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