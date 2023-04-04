package com.aca.myprojectaca.controller;

import com.aca.myprojectaca.dto.JwtResponce;
import com.aca.myprojectaca.dto.LoginRequest;
import com.aca.myprojectaca.dto.SignupRequest;
import com.aca.myprojectaca.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/signin")
    public JwtResponce loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/signup")
    public JwtResponce registerUser(@RequestBody SignupRequest signupRequest) {
        return authService.registerUser(signupRequest);

    }
}
