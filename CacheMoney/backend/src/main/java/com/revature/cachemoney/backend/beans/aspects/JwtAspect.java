package com.revature.cachemoney.backend.beans.aspects;

import com.revature.cachemoney.backend.beans.security.JwtUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Aspect to retrieve JWT information from requests.
 * Used alongside @RequireJwt.
 */
@Aspect
@Component
public class JwtAspect {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAspect(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Around("@annotation(com.revature.cachemoney.backend.beans.annotations.RequireJwt)")
    @SuppressWarnings("unchecked")
    public ResponseEntity<String> validate(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();

        if (!jwtUtil.validateToken((String) args[0], (Integer) args[1])) {
            return ResponseEntity.badRequest().build();
        }

        return (ResponseEntity<String>) jp.proceed();
    }
}
