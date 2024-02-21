package org.example.panacheauth.controllers;

import lombok.NonNull;
import org.example.panacheauth.dtos.LoginRequestDto;
import org.example.panacheauth.dtos.LogoutRequestDto;
import org.example.panacheauth.dtos.SignUpRequestDto;
import org.example.panacheauth.dtos.UserDto;
import org.example.panacheauth.models.Token;
import org.example.panacheauth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-auth")
public class UserAuthControllersV2 {

    private UserService userService;

    public UserAuthControllersV2(UserService userService) {
        this.userService = userService;
    }
    //Here if a user logs in, we send the token, else return null in token
    //Took 15 seconds to verify with 16 rounds of bcryption
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request) {
        /** check if email and password in db
         * if yes return user
         * else throw some error
         */
        return userService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        /**
         * Here we need to know for which token we want to logout as he may have multiple devices
         * connected! Also, the token gives info about user.
         */
        userService.logout(request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto request) {
        /**
         * Here we need to know all user details, if successful it returns the user info
         * without the password so we will return UserDTO
         */
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();

        /**
         * Here we don't want to send password hence used Dto for conversion
         */
        return UserDto.from(userService.signUp(name, email, password));
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String token) {
        return UserDto.from(userService.validateToken(token));
    }

}
