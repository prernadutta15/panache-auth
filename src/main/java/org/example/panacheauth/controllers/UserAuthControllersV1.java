package org.example.panacheauth.controllers;

import org.example.panacheauth.dtos.LoginRequestDto;
import org.example.panacheauth.dtos.LogoutRequestDto;
import org.example.panacheauth.dtos.SignUpRequestDto;
import org.example.panacheauth.dtos.UserDto;
import org.example.panacheauth.models.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-auth-v1")
public class UserAuthControllersV1 {

    //Here if a user logs in, we send the token, else return null in token
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request) {
        /** check if email and password in db
         * if yes return user
         * else throw some error
         */
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        /**
         * Here we need to know for which token we want to logout as he may have multiple devices
         * connected! Also, the token gives info about user.
         */
        return null;
    }

    @PostMapping("/signin")
    public UserDto signUp(@RequestBody SignUpRequestDto request) {
        /**
         * Here we need to know all user details, if successful it returns the user info
         * without the password so we will return UserDTO
         */
        return null;
    }

}
