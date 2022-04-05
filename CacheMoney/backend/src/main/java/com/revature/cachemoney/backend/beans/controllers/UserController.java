package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.controllers.payload.PostUserResponse;
import com.revature.cachemoney.backend.beans.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle requests related to Users.
 * 
 * @author Version 1 (Alvin Frierson, Brian Gardner, Cody Gonsowski, & Jeffrey Lor)
 *         Version 2 (Phillip Vo, Josue Rodriguez, Prakash, Maikel Vera)
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil, ObjectMapper mapper) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

	/**
	 * GET *ALL* Users.
	 * 
	 * @return List of all Users
	 */
    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * GET the User with provided ID.
     * 
     * @param token  for current session
     * @param userId for current User
     * @return User object
     * @throws JsonProcessingException
     */
    @GetMapping
    @RequireJwt
    public ResponseEntity<String> getUserById(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {

        return ResponseEntity.ok().body(mapper.writeValueAsString(userService.getUserById(userId)));
    }

    /**
     * POST a User.
     * 
     * @param user containing the firstName, lastName, email, username, password, & mfa
     * @return PostUserResponse | badRequest() based on registration status
     *
     */
    @PostMapping
    public ResponseEntity<String> postUser(@RequestBody User user) throws JsonProcessingException {

        if(userService.postUser(user)){
            System.out.println(user.toString());
            return ResponseEntity.ok().body(mapper.writeValueAsString(
                    new PostUserResponse(user.isMfa(), user.getQrImageUri())
            ));
        }

        // indicate bad request
        return ResponseEntity.badRequest().build();
    }

    /**
     * DELETE a User with provided ID.
     * Returns a bad request if the DELETE is unsuccessful.
     * 
     * @param token  for current session
     * @param userId for current User
     * @return OK | Bad Request based on DELETE success
     * @throws JsonProcessingException
     */
    @DeleteMapping
    @RequireJwt
    public ResponseEntity<String> deleteUserById(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {

        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    /**
     * Log in to a User account.
     * 
     * @param user containing (at least) username & password
     * @return User object & its associated JWT
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody User user) throws JsonProcessingException {
        // has internal checking to see if user is valid

        User tempUser = userService.getUserByUsername(user);

        // make sure the user is valid
        if (tempUser != null) {
            if(tempUser.isMfa()){
                return ResponseEntity.ok().body(mapper.writeValueAsString(tempUser));
            }
            else {
                // write the headers & object into the response
                return ResponseEntity.ok()
                        .headers(this.generateToken(tempUser.getUserId()))
                        .body(mapper.writeValueAsString(tempUser));
            }
        }

        // indicate bad request
        return ResponseEntity.badRequest().build();
    }

    /**
     * Verify the OTP for 2FA process.
     *
     * @param userId Id of the user to authenticate
     * @param code OTP code to verify
     * @return UserID & its associated JWT
     */
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(
            @RequestParam Integer userId, @RequestParam String code) {

        if(userService.verify(userId, code)){
            // write the headers & object into the response
            return ResponseEntity.ok().headers(this.generateToken(userId)).body(userId.toString());
        }

        // indicate bad request
        return ResponseEntity.badRequest().build();
    }

    /**
     * GET all Accounts associated with a particular User ID.
     * 
     * @param token  for current session
     * @param userId for current User
     * @return List of Accounts associated with a particular user
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/accounts")
    @RequireJwt
    public ResponseEntity<String> getAccountsByUserId(
            @RequestHeader(name = "token") String token,
            @RequestHeader(name = "userId") Integer userId)
            throws JsonProcessingException {

        return ResponseEntity.ok().body(mapper.writeValueAsString(userService.getAccountsByUserId(userId)));
    }

    private HttpHeaders generateToken(Integer userId){
        HttpHeaders headers = new HttpHeaders();

        // add the JWT to the headers
        headers.set("JWT", jwtUtil.generateToken(userId));
        headers.set("Access-Control-Expose-Headers", "JWT");

        return headers;
    }
}
